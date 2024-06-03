<template>
  <div style="background-color: #f6f6f6; min-height: 100vh">

    <!--头部-->
    <div class="front-header">
      <div class="front-header-left">
        <img src="@/assets/imgs/logo.png" alt="">
        <div class="title">智慧社区</div>
      </div>
      <div class="front-header-center">
        <div class="front-header-nav">
          <el-menu :default-active="$route.path" mode="horizontal" router>
            <el-menu-item index="/front/home">首页</el-menu-item>
            <el-menu-item index="/front/activity">社区活动</el-menu-item>
            <el-menu-item index="/front/serve">社区服务</el-menu-item>
            <el-menu-item index="/front/nurseHouse">养老院</el-menu-item>
            <el-menu-item index="/front/parking">小区车位</el-menu-item>
            <el-menu-item index="/front/complaintSuggestion">投诉建议</el-menu-item>
            <el-menu-item index="/front/person">个人中心</el-menu-item>
          </el-menu>
        </div>
      </div>
      <div class="front-header-right">
        <div v-if="!user.username">
          <el-button @click="$router.push('/login')">登录</el-button>
          <el-button @click="$router.push('/register')">注册</el-button>
        </div>
        <div v-else>
          <el-dropdown>
            <div class="front-header-dropdown">
              <img :src="user.avatar" alt="">
              <div style="margin-left: 10px">
                <span>{{ user.name }}</span><i class="el-icon-arrow-down"
                                               style="margin-left: 5px"></i>
              </div>
            </div>
            <el-dropdown-menu>
              <el-dropdown-item>
                <div @click="$router.push('/front/activitySign')">我报名的活动</div>
              </el-dropdown-item>
              <el-dropdown-item>
                <div @click="$router.push('/front/reserve')">我预约的服务</div>
              </el-dropdown-item>
              <el-dropdown-item>
                <div @click="$router.push('/front/parkingSign')">我预约的车位</div>
              </el-dropdown-item>
              <el-dropdown-item>
                <div @click="$router.push('/front/recs')">我的建议和反馈</div>
              </el-dropdown-item>
              <el-dropdown-item>
                <div @click="$router.push('/front/person')">个人中心</div>
              </el-dropdown-item>
              <el-dropdown-item>
                <div @click="logout">退出登录</div>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>
      </div>
    </div>
    <!--主体-->
    <div class="main-body">
      <transition name="fade">
      <router-view ref="child" @update:user="updateUser"/>
      </transition>
    </div>
    <Footer/>
  </div>


</template>

<script>

import Footer from "@/components/Footer.vue";

export default {
  name: "FrontLayout",
  components: {Footer},

  data() {
    return {
      user: JSON.parse(localStorage.getItem("xm-user") || '{}'),
    }
  },

  mounted() {
  },
  methods: {
    updateUser() {
      this.user = JSON.parse(localStorage.getItem('xm-user') || '{}')   // 重新获取下用户的最新信息
    },
    // 退出登录
    logout() {
      localStorage.removeItem("xm-user");
      this.$router.push("/login");
    },
  }

}
</script>

<style scoped>
@import "@/assets/css/front.css";

.fade-enter-active, .fade-leave-active {
  transition: opacity .1s;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}
</style>