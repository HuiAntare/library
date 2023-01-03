//工具类
import axios from "axios";
import router from "@/router";
import Cookies from "js-cookie";

const request = axios.create({
    baseURL:'http://localhost:9090/api',    //后台地址,会直接加上
    timeout:5000
})



// request 拦截器
// 可以自请求发送对请求做一些处理
// 比如统一加token,对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    const adminJson = Cookies.get('admin')
    if(adminJson){
        //设置请求头
        config.headers['token'] = JSON.parse(adminJson).token
    }
    return config
},error => {
    return Promise.reject(error)
});


// response 拦截器
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;         //res包含的是code,data,msg的属性
        // 兼容服务器返回的字符串数据
        if(typeof res === 'string') {      //对返回的数据类型判断
            res = res ? JSON.parse(res) :res   //若是String,则进行json解析,返回
        }
        if(res.code === '401'){
            router.push('login')
        }
        return res;
    },
    error => {
        console.log('err' + error)
        return Promise.reject(error)
    }
)

export default request
