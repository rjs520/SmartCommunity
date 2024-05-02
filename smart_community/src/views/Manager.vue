<template>
  <!-- 管理员界面容器 -->
  <div class="manager-container">
    <!-- 头部区域 -->
    <div class="manager-header">
      <!-- 左侧Logo与标题 -->
      <div class="manager-header-left">
        <img src="@/assets/imgs/logo.png" alt=""/> <!-- Logo 图片 -->
        <div class="title">智慧社区系统</div> <!-- 页面标题 -->
      </div>

      <!-- 中间面包屑导航 -->
      <div class="manager-header-center">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item> <!-- 首页链接 -->
          <el-breadcrumb-item :to="{ path: $route.path }">{{
              $route.meta.name
            }}
          </el-breadcrumb-item> <!-- 当前页面名称 -->
        </el-breadcrumb>
      </div>

      <!-- 右侧用户操作区 -->
      <div class="manager-header-right">
        <!-- 下拉菜单，显示用户头像、姓名与操作选项 -->
        <el-dropdown placement="bottom">
          <div class="avatar">
            <img
                :src="user.avatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'"
                alt=""/>
            <!-- 用户头像 -->
            <div>{{ user.name || '管理员' }}</div> <!-- 用户姓名或默认显示“管理员” -->
          </div>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item @click.native="goToPerson">个人信息</el-dropdown-item> <!-- 个人信息链接 -->
            <el-dropdown-item @click.native="$router.push('/password')">修改密码</el-dropdown-item>
            <!-- 修改密码链接 -->
            <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item> <!-- 退出登录链接 -->
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>

    <!-- 主体内容区域 -->
    <div class="manager-main">
      <!-- 侧边栏 -->
      <div class="manager-main-left">
        <el-menu :default-active="$route.path" :default-openeds="['info', 'user']" router
                 style="border: none">
          <!-- 首页菜单项 -->
          <el-menu-item index="/home">
            <i class="el-icon-s-home"></i>
            <span slot="title">系统首页</span>
          </el-menu-item>

          <!-- 信息管理子菜单 -->
          <el-submenu index="info">
            <template slot="title">
              <i class="el-icon-menu"></i><span>信息管理</span>
            </template>
            <el-menu-item index="/notice">公告信息</el-menu-item>
            <el-menu-item index="/category">资讯分类</el-menu-item>
            <el-menu-item index="/news">资讯信息</el-menu-item>
            <el-menu-item index="/activity">社区活动</el-menu-item>
            <el-menu-item index="/serve">社区服务</el-menu-item>
            <el-menu-item index="/nurseHouse">养老院信息</el-menu-item>
            <el-menu-item index="/parking">车位信息</el-menu-item>
          </el-submenu>

          <!-- 用户管理子菜单 -->
          <el-submenu index="user">
            <template slot="title">
              <i class="el-icon-menu"></i><span>用户管理</span>
            </template>
            <el-menu-item index="/admin">管理员信息</el-menu-item>
            <el-menu-item index="/user">用户信息</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>

      <!-- 数据表格区域 -->
      <div class="manager-main-right">
        <!-- 动态加载路由对应的子组件，并传递 `update:user` 事件 -->
        <router-view @update:user="updateUser"/>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Manager", // 组件名称

  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'), // 从localStorage获取用户信息
    }
  },

  created() {
    // 如果用户信息中无ID，则重定向至登录页,并且只允许管理员登录
    if (!this.user.id || this.user.role !== 'ADMIN') {
      this.$router.push('/login')
    }
  },

  methods: {
    // 更新用户信息（接收子组件传递的事件）
    updateUser() {
      this.user = JSON.parse(localStorage.getItem('xm-user') || '{}') // 重新获取用户信息
    },

    // 跳转至个人信息页面，根据用户角色决定跳转路径
    goToPerson() {
      if (this.user.role === 'ADMIN') {
        this.$router.push('/adminPerson')
      }
    },

    // 退出登录方法
    logout() {
      localStorage.removeItem('xm-user') // 删除本地存储的用户信息
      this.$router.push('/login') // 跳转到登录页面
    },
  }
}
</script>

<style scoped>
/* 引入外部CSS文件 */
@import "@/assets/css/manager.css";
</style>
