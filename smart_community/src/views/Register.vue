<template>
  <!-- 容器组件，设置全屏背景和垂直居中布局 -->
  <div class="container">
    <!-- 注册表单容器，设置固定宽度、内边距、背景色和圆角 -->
    <div style="width: 400px; padding: 30px; background-color: white; border-radius: 5px;">
      <!-- 注册欢迎文字，居中显示 -->
      <div style="text-align: center; font-size: 20px; margin-bottom: 20px; color: #333">欢迎注册
      </div>

      <!-- 表单组件，绑定数据模型和验证规则 -->
      <el-form ref="formRef" :model="form" :rules="rules">

        <!-- 账号输入项，带用户图标前缀 -->
        <el-form-item prop="username">
          <el-input v-model="form.username" placeholder="请输入账号"
                    prefix-icon="el-icon-user"></el-input>
        </el-form-item>

        <!-- 密码输入项，带锁图标前缀和密码可见开关 -->
        <el-form-item prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" prefix-icon="el-icon-lock"
                    show-password></el-input>
        </el-form-item>

        <!-- 确认密码输入项，带锁图标前缀和密码可见开关 -->
        <el-form-item prop="confirmPass">
          <el-input v-model="form.confirmPass" placeholder="请确认密码" prefix-icon="el-icon-lock"
                    show-password></el-input>
        </el-form-item>

        <!-- 注册按钮，全宽、深色背景、白色文字 -->
        <el-form-item>
          <el-button style="width: 100%; background-color: #333; border-color: #333; color: white"
                     @click="register">注 册
          </el-button>
        </el-form-item>

        <!-- 登录提示，已注册用户可直接登录 -->
        <div style="display: flex; align-items: center">
          <div style="flex: 1"></div>
          <div style="flex: 1; text-align: right">
            已有账号？请 <a href="/login">登录</a>
          </div>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register", // 组件名称

  data() {
    // 定义密码验证规则
    const validatePassword = (rule, confirmPass, callback) => {
      if (confirmPass === '') {
        callback(new Error('请确认密码')) // 提示用户输入确认密码
      } else if (confirmPass !== this.form.password) {
        callback(new Error('两次输入的密码不一致')) // 提示用户两次输入的密码不一致
      } else {
        callback() // 密码验证通过
      }
    }

    return {
      form: {}, // 初始化表单数据模型为空对象

      rules: {
        username: [
          {required: true, message: '请输入账号', trigger: 'blur'}, // 账号必填验证规则
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'}, // 密码必填验证规则
        ],
        confirmPass: [ // 确认密码验证规则
          {validator: validatePassword, trigger: 'blur'}
        ]
      }
    }
  },

  created() {
    // 组件创建时无需额外操作
  },

  methods: {
    // 注册方法，验证表单并发送注册请求
    register() {
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          // 验证通过，发起注册请求
          this.$request.post('/register', this.form).then(res => {
            if (res.code === '200') {
              // 注册成功，跳转登录页面，显示成功消息
              this.$router.push('/')
              this.$message.success('注册成功')
            } else {
              // 注册失败，显示错误消息
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
  background-image: url("@/assets/imgs/bg1.jpg");
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
