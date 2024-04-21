<template>
  <!-- 页面主容器 -->
  <div>
    <!-- 公告栏，展示滚动公告 -->
    <div class="front-notice"><i class="el-icon-bell" style="margin-right: 2px"></i>公告：{{ top }}
    </div>

    <!-- 头部区域 -->
    <div class="front-header">
      <!-- 左侧Logo与标题 -->
      <div class="front-header-left">
        <img src="@/assets/imgs/logo.png" alt=""> <!-- Logo 图片 -->
        <div class="title">项目前台</div> <!-- 页面标题 -->
      </div>

      <!-- 中间导航栏 -->
      <div class="front-header-center">
        <div class="front-header-nav">
          <el-menu :default-active="$route.path" mode="horizontal" router>
            <el-menu-item index="/front/home">首页</el-menu-item> <!-- 首页链接 -->
            <el-menu-item index="/front/person">个人中心</el-menu-item> <!-- 个人中心链接 -->
          </el-menu>
        </div>
      </div>

      <!-- 右侧用户操作区 -->
      <div class="front-header-right">
        <!-- 未登录状态 -->
        <div v-if="!user.username">
          <el-button @click="$router.push('/login')">登录</el-button> <!-- 登录按钮 -->
          <el-button @click="$router.push('/register')">注册</el-button> <!-- 注册按钮 -->
        </div>

        <!-- 已登录状态 -->
        <div v-else>
          <!-- 下拉菜单，显示用户头像、姓名与退出选项 -->
          <el-dropdown>
            <div class="front-header-dropdown">
              <img :src="user.avatar" alt=""> <!-- 用户头像 -->
              <div style="margin-left: 10px">
                <span>{{ user.name }}</span><i class="el-icon-arrow-down"
                                               style="margin-left: 5px"></i>
              </div>
            </div>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>
                <div style="text-decoration: none" @click="logout">退出</div> <!-- 退出登录链接 -->
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>

    <!-- 主体内容区域 -->
    <div class="main-body">
      <!-- 动态加载路由对应的子组件，并传递 `update:user` 事件 -->
      <router-view ref="child" @update:user="updateUser"/>
    </div>
  </div>
</template>

<script>

export default {
  name: "FrontLayout", // 组件名称

  data() {
    return {
      top: '', // 当前显示的滚动公告文本
      notice: [], // 公告列表
      user: JSON.parse(localStorage.getItem("xm-user") || '{}'), // 从localStorage获取用户信息
    }
  },

  mounted() {
    // 页面挂载后加载公告数据
    this.loadNotice()
  },

  methods: {
    // 加载所有公告
    loadNotice() {
      this.$request.get('/notice/selectAll').then(res => {
        this.notice = res.data

        let i = 0
        if (this.notice && this.notice.length) {
          this.top = this.notice[0].content
          setInterval(() => {
            this.top = this.notice[i].content
            i++
            if (i === this.notice.length) {
              i = 0
            }
          }, 2500) // 每隔2.5秒切换公告
        }
      })
    },

    // 更新用户信息（接收子组件传递的事件）
    updateUser() {
      this.user = JSON.parse(localStorage.getItem('xm-user') || '{}') // 重新获取用户信息
    },

    // 退出登录方法
    logout() {
      localStorage.removeItem("xm-user"); // 删除本地存储的用户信息
      this.$router.push("/login"); // 跳转到登录页面
    },
  }
}
</script>

<style scoped>
/* 引入外部CSS文件 */
@import "@/assets/css/front.css";
</style>
