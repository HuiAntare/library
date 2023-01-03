package com.example.springboot.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.springboot.entity.Admin;
import com.example.springboot.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Slf4j
public class TokenUtils {

    private static IAdminService staticAdminService;

    @Resource
    private IAdminService adminService;    //从springboot中获取adminService

    @PostConstruct
    public void setUserService() {
        staticAdminService = adminService;
        //把获取到的赋予静态变量 这样可以在静态方法中使用springboot中的类
    }

    // 生成token字符串
    public static String genToken(String adminId, String sign) {
        return JWT.create().withAudience(adminId) // 将 admin的id 保存到 token 里面,作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 2)) // 2小时后token过期
                .sign(Algorithm.HMAC256(sign)); // 以 password 作为 token 的密钥(标签)
    }

    /**
     * 获取当前登录的用户信息
     * @return admin对象
     */
    public static Admin getCurrentAdmin() {
        String token = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = request.getHeader("token");   //可以从request获取token(第一步获取)
            if (StrUtil.isNotBlank(token)) {
                token = request.getParameter("token");
                //或者可以从url参数里面获取( 如/admin?token=xxxx ; 第二次获取)
            }
            //两次获取不到,报错
            if (StrUtil.isBlank(token)) {
                log.error("获取当前登录的token失败， token: {}", token);
                return null;
            }
            //首先把token解开然后获取Audience里面的adminid
            String adminId = JWT.decode(token).getAudience().get(0);

            //拿到adminid所对应的admin的信息
            return staticAdminService.getById(Integer.valueOf(adminId));
        } catch (Exception e) {
            //发生异常
            log.error("获取当前登录的管理员信息失败, token={}", token,  e);
            return null;
        }
    }
}

