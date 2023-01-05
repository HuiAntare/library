package com.example.springboot.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.entity.Book;
import com.example.springboot.entity.Borrow;
import com.example.springboot.entity.Retur;
import com.example.springboot.entity.User;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.BookMapper;
import com.example.springboot.mapper.BorrowMapper;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.mapper.po.BorrowReturCountPO;
import com.example.springboot.service.IBorrowService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@Slf4j
public class BorrowService implements IBorrowService {

    @Resource
    BorrowMapper borrowMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Borrow> list() {
        return borrowMapper.list();
    }

    @Override
    public PageInfo<Borrow> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Borrow> borrows = borrowMapper.listByCondition(baseRequest);
        for (Borrow borrow : borrows) {
            LocalDate returnDate = borrow.getReturnDate();
            LocalDate now = LocalDate.now();
            if (now.plusDays(1).isEqual(returnDate)) {  // 当前日期比归还的日期小一天
                borrow.setNote("即将到期");
            } else if (now.isEqual(returnDate)) {
                borrow.setNote("已到期");
            } else if (now.isAfter(returnDate)) {
                borrow.setNote("已过期");
            } else {
                borrow.setNote("正常");
            }
        }
        return new PageInfo<>(borrows);
    }

    @Override
    @Transactional
    public void save(Borrow borrow) {
        // 1. 校验用户是否存在
        String userNo = borrow.getUserNo();
        User user = userMapper.getByUuid(userNo);
        if (Objects.isNull(user)) {
            throw new ServiceException("用户不存在");
        }
        // 2. 校验图书是否存在
        Book book = bookMapper.getByNo(borrow.getBookNo());
        if (Objects.isNull(book)) {
            throw new ServiceException("所借图书不存在");
        }
        // 3. 校验图书数量
        if (book.getNums() < 1) {
            throw new ServiceException("图书数量不足");
        }
        Integer account = user.getAccount();
        Integer score = book.getScore() * borrow.getDays();  // score = 借1本的积分 * 天数
        // 4. 校验用户账户余额
        if (score > account) {
            throw new ServiceException("用户积分不足");
        }
        // 5. 更新用户余额
        user.setAccount(user.getAccount() - score);
        userMapper.updateById(user);
        // 6. 更新图书的数量
        book.setNums(book.getNums() - 1);
        bookMapper.updateById(book);

        borrow.setReturnDate(LocalDate.now().plus(borrow.getDays(), ChronoUnit.DAYS));  // 当前的日期加 days 得到归还的日期
        borrow.setScore(score);
        // 7. 新增借书记录
        borrowMapper.save(borrow);
    }

    @Override
    public PageInfo<Retur> pageRetur(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        return new PageInfo<>(borrowMapper.listReturByCondition(baseRequest));
    }

    // 还书逻辑
    @Transactional
    @Override
    public void saveRetur(Retur borrow) {
        // 改状态
        borrow.setStatus("已归还");
        borrowMapper.updateStatus("已归还", borrow.getId());  // borrow.getId() 是前端传来的借书id
        //borrow.setId(null);  // 新数据
        borrow.setRealDate(LocalDate.now());
        borrowMapper.saveRetur(borrow);

        // 图书数量增加
        bookMapper.updateNumByNo(borrow.getBookNo());

        // 返还和扣除用户积分
        Book book = bookMapper.getByNo(borrow.getBookNo());
        if (book != null) {
            long until = 0;
            if (borrow.getRealDate().isBefore(borrow.getReturnDate())) {
                until = borrow.getRealDate().until(borrow.getReturnDate(), ChronoUnit.DAYS);
            } else if (borrow.getRealDate().isAfter(borrow.getReturnDate())) {  // 逾期归还，要扣额外的积分
                until = -borrow.getReturnDate().until(borrow.getRealDate(), ChronoUnit.DAYS);
            }
            int score = (int) until * book.getScore();  // 获取待归还的积分
            User user = userMapper.getByUuid(borrow.getUserNo());
            int account = user.getAccount() + score;
            user.setAccount(account);
            if (account < 0) {
                // 锁定账号
                user.setStatus(false);
            }
            userMapper.updateById(user);
        }
    }

    @Override
    public Borrow getById(Integer id) {
        return borrowMapper.getById(id);
    }

    @Override
    public void update(Borrow borrow) {
        borrow.setUpdatetime(LocalDate.now());
        borrowMapper.updateById(borrow);
    }

    @Override
    public void deleteById(Integer id) {
        borrowMapper.deleteById(id);
    }

    @Override
    public void deleteReturById(Integer id) {
        borrowMapper.deleteReturById(id);
    }

    @Override
    public Map<String, Object> getCountByTimeRange(String timeRange) {
        Map<String, Object> map = new HashMap<>();
        Date today = new Date();
        List<DateTime> dateRange;
        switch (timeRange) {
            case "week":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today, -6), today, DateField.DAY_OF_WEEK);
                break;
            case "month":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today, -29), today, DateField.DAY_OF_MONTH);
                break;
            case "month2":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today, -59), today, DateField.DAY_OF_MONTH);
                break;
            case "month3":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today, -89), today, DateField.DAY_OF_MONTH);
                break;
            default:
                dateRange = new ArrayList<>();
        }
        List<String> dateStrRange = datetimeToDateStr(dateRange);
        map.put("date", dateStrRange);
        List<BorrowReturCountPO> borrowCount = borrowMapper.getCountByTimeRange(timeRange, 1);
        List<BorrowReturCountPO> returnCount = borrowMapper.getCountByTimeRange(timeRange, 2);
        map.put("borrow", countList(borrowCount, dateStrRange));
        map.put("retur", countList(returnCount, dateStrRange));
        return map;
    }

    private List<String> datetimeToDateStr(List<DateTime> dateTimeList) {
        List<String> list = CollUtil.newArrayList();
        if (CollUtil.isEmpty(dateTimeList)) {
            return list;
        }
        for (DateTime dateTime : dateTimeList) {
            String date = DateUtil.formatDate(dateTime);
            list.add(date);
        }
        return list;
    }

    private List<Integer> countList(List<BorrowReturCountPO> countPOList, List<String> dateRange) {
        List<Integer> list = CollUtil.newArrayList();
        if (CollUtil.isEmpty(countPOList)) {
            return list;
        }
        for (String date : dateRange) {
            Integer count = countPOList.stream().filter(countPO -> date.equals(countPO.getDate()))
                    .map(BorrowReturCountPO::getCount).findFirst().orElse(0);
            list.add(count);
        }
        return list;
    }

}
