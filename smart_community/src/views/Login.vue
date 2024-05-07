模板
<template>
  <div class="container">
    <!-- 页面头部，包含logo和标题 -->
    <header class="header">
      <div class="logo-container">
        <img class="logo" src="@/assets/imgs/logo.png" alt="">
        <span class="title">智慧社区系统</span>
      </div>
    </header>
    <!-- 主要内容区域，包含登录图像和登录表单 -->
    <main class="main">
      <div class="image-container">
        <img class="login-image" src="@/assets/imgs/login.jpg" alt="">
      </div>
      <div class="form-container">
        <div class="welcome-message">欢 迎 使 用</div>
        <!-- 登录表单，使用Element UI的el-form组件 -->
        <el-form :model="form" :rules="rules" ref="formRef">
          <el-form-item prop="username">
            <el-input prefix-icon="el-icon-user" placeholder="请输入账号"
                      v-model="form.username"></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input prefix-icon="el-icon-lock" placeholder="请输入密码" show-password
                      v-model="form.password"></el-input>
          </el-form-item>
          <el-form-item prop="role">
            <!-- 角色选择下拉菜单 -->
            <el-select style="width: 100%" v-model="form.role">
              <el-option value="ADMIN" label="管理员"></el-option>
              <el-option value="USER" label="普通用户"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <!-- 登录按钮 -->
            <el-button class="login-button" @click="login">登 录</el-button>
          </el-form-item>
          <div class="register-container">
            <div style="flex: 1"></div>
            <!-- 注册链接 -->
            <div class="register-link">
              还没有账号？请 <a href="/register">注册</a>
            </div>
          </div>
        </el-form>
      </div>
    </main>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      form: {role: 'ADMIN'}, // 默认选择管理员角色
      rules: {
        // 表单验证规则
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
    // 组件创建时的逻辑（此处为空）
  },
  methods: {
    login() {
      // 登录方法：验证表单，发送登录请求
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          // 验证通过后的逻辑
          this.$request.post('/login', this.form).then(res => {
            if (res.code === '200') {
              // 登录成功，存储用户数据并跳转
              localStorage.setItem("xm-user", JSON.stringify(res.data))
              this.$message.success('登录成功')
              setTimeout(() => {
                if (res.data.role === 'ADMIN') {
                  location.href = '/home'
                } else {
                  location.href = '/front/home'
                }
              }, 500)
            } else {
              // 登录失败，显示错误信息
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
/* 页面样式 */
.container {
  height: 100vh;
  overflow: hidden;
  background-color: #f8f8f8;
}

.header {
  height: 60px;
  background-color: white;
}

.logo-container {
  width: 60%;
  height: 100%;
  display: flex;
  align-items: center;
  margin: 0 auto;
}

.logo {
  width: 40px;
}

.title {
  color: #2A60C9;
  font-size: 24px;
  font-weight: bold;
  margin-left: 10px;
}

.main {
  height: calc(100vh - 60px);
  width: 60%;
  margin: 0 auto;
  display: flex;
  align-items: center;
}

.image-container {
  flex: 1;
}

.login-image {
  width: 90%;
  height: 380px;
  border-radius: 5px;
}

.form-container {
  width: 350px;
  padding: 50px 30px;
  box-shadow: 0 0 10px rgba(0, 0, 0, .1);
  background-color: white;
  border-radius: 5px;
}

.welcome-message {
  text-align: center;
  font-size: 22px;
  margin-bottom: 30px;
  font-weight: bold;
  color: #2A60C9;
}

.login-button {
  width: 100%;
  background-color: #2A60C9;
  border-color: #2A60C9;
  color: white;
}

.register-container {
  display: flex;
  align-items: center;
}

.register-link {
  flex: 1;
  text-align: right;
}

a {
  color: #2a60c9;
}
</style>
