import axios from 'axios'
import router from "@/router";

// 创建一个基于默认配置的新axios实例
const request = axios.create({
  // 设置基础URL，即后端API服务器的地址（含端口）
  baseURL: process.env.VUE_APP_BASEURL,
  // 设置请求超时时间（毫秒）
  timeout: 30000
})

// 请求拦截器：在请求发送到服务器之前对其进行处理
request.interceptors.request.use(
    config => {
      // 设置请求头中的Content-Type为JSON格式
      config.headers['Content-Type'] = 'application/json;charset=utf-8';

      // 从localStorage中获取缓存的用户信息
      const user = JSON.parse(localStorage.getItem("xm-user") || '{}');

      // 将用户token添加到请求头中
      config.headers['token'] = user.token;

      return config;
    },
    error => {
      // 记录请求错误信息（调试用）
      console.error('request error: ' + error);

      // 将错误向上抛出，中断后续请求处理流程
      return Promise.reject(error);
    }
);

// 响应拦截器：在接收到服务器响应后对其进行处理
request.interceptors.response.use(
    response => {
      // 解析响应数据为对象
      let res = response.data;

      // 若服务端返回的是字符串形式的数据，则尝试将其解析为JSON对象
      if (typeof res === 'string') {
        res = res ? JSON.parse(res) : res;
      }

      // 根据服务端返回的状态码进行相应处理
      if (res.code === '401') {
        // 若返回401（未授权），则重定向到登录页面
        router.push('/login');
      }

      // 返回处理后的响应数据
      return res;
    },
    error => {
      // 记录响应错误信息（调试用）
      console.error('response error: ' + error);

      // 将错误向上抛出，中断后续响应处理流程
      return Promise.reject(error);
    }
);

// 导出定制化的axios实例供全局使用
export default request;
