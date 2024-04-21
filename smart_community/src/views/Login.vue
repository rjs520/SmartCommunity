<template>
  <!-- 容器组件，设置全屏背景和垂直居中布局 -->
  <div class="container">
    <!-- 登录表单容器，设置固定宽度、内边距、背景色和圆角 -->
    <div style="width: 400px; padding: 30px; background-color: white; border-radius: 5px;">
      <!-- 欢迎文字，居中显示 -->
      <div style="text-align: center; font-size: 20px; margin-bottom: 20px; color: #333">欢迎使用
      </div>

      <!-- 表单组件，绑定数据模型和验证规则 -->
      <el-form :model="form" :rules="rules" ref="formRef">

        <!-- 账号输入项，带用户图标前缀 -->
        <el-form-item prop="username">
          <el-input prefix-icon="el-icon-user" placeholder="请输入账号"
                    v-model="form.username"></el-input>
        </el-form-item>

        <!-- 密码输入项，带锁图标前缀和密码可见开关 -->
        <el-form-item prop="password">
          <el-input prefix-icon="el-icon-lock" placeholder="请输入密码" show-password
                    v-model="form.password"></el-input>
        </el-form-item>

        <!-- 登录按钮，全宽、深色背景、白色文字 -->
        <el-form-item>
          <el-button style="width: 100%; background-color: #333; border-color: #333; color: white"
                     @click="login">登 录
          </el-button>
        </el-form-item>

        <!-- 注册提示，已注释（可选） -->
        <!--        <div style="display: flex; align-items: center">-->
        <!--          <div style="flex: 1"></div>-->
        <!--          <div style="flex: 1; text-align: right">-->
        <!--            还没有账号？请 <a href="/register">注册</a>-->
        <!--          </div>-->
        <!--        </div>-->
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login", // 组件名称

  data() {
    return {
      // 表单数据模型，初始化角色为 'ADMIN'
      form: {role: 'ADMIN'},

      // 表单验证规则
      rules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
        ]
      }
    }
  },

  created() {
    // 组件创建时无需额外操作
  },

  methods: {
    // 登录方法，验证表单并发送登录请求
    login() {
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          // 验证通过，发起登录请求
          this.$request.post('/login', this.form).then(res => {
            if (res.code === '200') {
              // 登录成功，存储用户数据到localStorage，跳转主页，显示成功消息
              localStorage.setItem("xm-user", JSON.stringify(res.data))
              this.$router.push('/')
              this.$message.success('登录成功')
            } else {
              // 登录失败，显示错误消息
              this.$message.error(res.msg)
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>
/* 容器样式，设置全屏高度、隐藏溢出内容、背景图片、全屏居中对齐、字体颜色 */
.container {
  height: 100vh;
  overflow: hidden;
  background-image: url("@/assets/imgs/bg.jpg");
  background-size: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #666;
}

/* 链接样式，设置链接颜色 */
a {
  color: #2a60c9;
}
</style>
