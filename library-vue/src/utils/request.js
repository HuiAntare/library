//工具类
import axios from "axios";

const request = axios.create({
    baseURL:'http://localhost:9090',    //后台地址,会直接加上
    timeout:5000
})



// request 拦截器
// 可以自请求发送对请求做一些处理
// 比如统一加token,对请求参数统一加密
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    //config.headers['token'] = user.token;   //设置请求头
    return config
},error => {
    return Promise.reject(error)
});




// response 拦截器
// 可以在接口相应后统一处理结果
request.interceptors.response.use(
    response => {
        let res = response.data;         //res包含的是code,data,msg的属性
        // 兼容服务器返回的字符串数据
        if(typeof res === 'string') {      //对返回的数据类型判断
            res = res ? JSON.parse(res) :res   //若是String,则进行json解析,返回
        }
        return res;
    },
    error => {
        console.log('err' + error)
        return Promise.reject(error)
    }
)

export default request
