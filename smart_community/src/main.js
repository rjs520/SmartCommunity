import Vue from 'vue' // 导入Vue核心库
import App from './App.vue' // 导入根组件App.vue
import router from './router' // 导入路由配置
import ElementUI from 'element-ui' // 导入Element UI库
import 'element-ui/lib/theme-chalk/index.css' // 导入Element UI的默认主题样式
import '@/assets/css/global.css' // 导入全局CSS样式
import '@/assets/css/theme/index.css' // 导入自定义主题样式
import request from "@/utils/request"; // 导入封装的HTTP请求模块

// 禁止在控制台输出Vue生产提示信息
Vue.config.productionTip = false

// 将request模块挂载到Vue原型上，便于全局访问
Vue.prototype.$request = request

// 将环境变量VUE_APP_BASEURL挂载到Vue原型上，作为全局基础URL
Vue.prototype.$baseUrl = process.env.VUE_APP_BASEURL

// 使用Element UI库，并设置组件默认尺寸为"small"
Vue.use(ElementUI, {size: "small"})

// 创建新的Vue实例，挂载根组件和路由配置
new Vue({
  router, // 注入路由对象
  render: h => h(App) // 使用渲染函数渲染根组件App.vue
}).$mount('#app') // 将Vue实例挂载到ID为'app'的DOM元素上
