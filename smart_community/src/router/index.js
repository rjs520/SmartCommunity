import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

/**
 * 解决vue-router 3.0以上版本中，频繁点击菜单报错的问题
 * 通过修改VueRouter的push方法来实现错误捕获
 */
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  {
    // 管理员页面相关路由配置
    path: '/',
    name: 'Manager',
    component: () => import('../views/Manager.vue'),
    redirect: '/home',  // 重定向到主页
    children: [
      // 管理员权限内的各个子页面
      // 包括403无权限页面、系统首页、管理员信息、用户信息等
      {
        path: '403',
        name: 'NoAuth',
        meta: {name: '无权限'},
        component: () => import('../views/manager/403')
      },
      {
        path: 'home',
        name: 'Home',
        meta: {name: '系统首页'},
        component: () => import('../views/manager/Home')
      },
      {
        path: 'admin',
        name: 'Admin',
        meta: {name: '管理员信息'},
        component: () => import('../views/manager/Admin')
      },
      {
        path: 'user',
        name: 'User',
        meta: {name: '用户信息'},
        component: () => import('../views/manager/User')
      },
      {
        path: 'adminPerson',
        name: 'AdminPerson',
        meta: {name: '个人信息'},
        component: () => import('../views/manager/AdminPerson')
      },
      {
        path: 'password',
        name: 'Password',
        meta: {name: '修改密码'},
        component: () => import('../views/manager/Password')
      },
      {
        path: 'notice',
        name: 'Notice',
        meta: {name: '公告信息'},
        component: () => import('../views/manager/Notice')
      },
      {
        path: 'category',
        name: 'Category',
        meta: {name: '资讯分类'},
        component: () => import('../views/manager/Category')
      },
      {
        path: 'news',
        name: 'News',
        meta: {name: '资讯信息'},
        component: () => import('../views/manager/News')
      },
      {
        path: 'activity',
        name: 'Activity',
        meta: {name: '社区活动'},
        component: () => import('../views/manager/Activity')
      },
      {
        path: 'serve',
        name: 'Serve',
        meta: {name: '社区服务'},
        component: () => import('../views/manager/Serve')
      },
      {
        path: 'nurseHouse',
        name: 'NurseHouse',
        meta: {name: '养老院信息'},
        component: () => import('../views/manager/NurseHouse')
      }, {
        path: 'parking',
        name: 'Parking',
        meta: {name: '车位管理'},
        component: () => import('../views/manager/Parking')
      },
      {
        path: 'comment',
        name: 'Comment',
        meta: {name: '评论管理'},
        component: () => import('../views/manager/Comment')
      },
      {
        path: 'activitySign',
        name: 'ActivitySign',
        meta: {name: '活动报名管理'},
        component: () => import('../views/manager/ActivitySign')
      },
      {
        path: 'reserve',
        name: 'Reserve',
        meta: {name: '活动预约管理'},
        component: () => import('../views/manager/Reserve')
      },
      {
        path: 'parkingSign',
        name: 'ParkingSign',
        meta: {name: '车位预约管理'},
        component: () => import('../views/manager/ParkingSign')
      },
      {
        path: 'complaintSuggestion',
        name: 'ComplaintSuggestion',
        meta: {name: '投诉建议信息'},
        component: () => import('../views/manager/ComplaintSuggestion')
      },
      {
        path: 'building',
        name: 'Building',
        meta: {name: '楼栋信息'},
        component: () => import('../views/manager/Building')
      },
      {
        path: 'recs',
        name: 'Recs',
        meta: {name: '反馈与建议'},
        component: () => import('../views/manager/Recs')
      }
    ]
  },
  // 前台页面相关路由配置
  {
    path: '/front',
    name: 'Front',
    component: () => import('../views/Front.vue'),
    children: [
      // 前台用户可见的各个子页面
      // 包括系统首页、个人信息、资讯详情等
      {
        path: 'home',
        name: 'Home',
        meta: {name: '系统首页'},
        component: () => import('../views/front/Home')
      },
      {
        path: 'person',
        name: 'Person',
        meta: {name: '个人信息'},
        component: () => import('../views/front/Person')
      },
      {
        path: 'newsDetail',
        name: 'NewsDetail',
        component: () => import('../views/front/NewsDetail')
      },
      {
        path: 'activityDetail',
        name: 'ActivityDetail',
        component: () => import('../views/front/ActivityDetail')
      },
      {
        path: 'houseDetail',
        name: 'HouseDetail',
        component: () => import('../views/front/HouseDetail')
      },
      {
        path: 'activity',
        name: 'Activity',
        component: () => import('../views/front/Activity')
      },
      {
        path: 'activitySign',
        name: 'ActivitySign',
        component: () => import('../views/front/ActivitySign')
      },
      {
        path: 'serve',
        name: 'Serve',
        component: () => import('../views/front/Serve')
      },
      {
        path: 'reserve',
        name: 'Reserve',
        component: () => import('../views/front/Reserve')
      },
      {
        path: 'nurseHouse',
        name: 'NurseHouse',
        component: () => import('../views/front/NurseHouse')
      },
      {
        path: 'parking',
        name: 'Parking',
        component: () => import('../views/front/Parking')
      },
      {
        path: 'parkingDetail',
        name: 'ParkingDetail',
        component: () => import('../views/front/ParkingDetail')
      },
      {
        path: 'parkingSign',
        name: 'ParkingSign',
        component: () => import('../views/front/ParkingSign')
      },
      {
        path: 'complaintSuggestion',
        name: 'ComplaintSuggestion',
        component: () => import('../views/front/ComplaintSuggestion')
      },
      {
        path: 'complaintSuggestionDetail',
        name: 'ComplaintSuggestionDetail',
        component: () => import('../views/front/ComplaintSuggestionDetail')
      },
      {
        path: 'recs',
        name: 'Recs',
        component: () => import('../views/front/Recs.vue')
      }
    ]
  },
  // 登录和注册页面的路由配置
  {
    path: '/login',
    name: 'Login',
    meta: {name: '登录'},
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    meta: {name: '注册'},
    component: () => import('../views/Register.vue')
  },
  // 404页面的路由配置
  {
    path: '*',
    name: 'NotFound',
    meta: {name: '无法访问'},
    component: () => import('../views/404.vue')
  },
]
/**
 * 创建并配置VueRouter实例
 */
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
