<template>
  <!-- 页面主容器 -->
  <div>
    <!-- 嵌入卡片组件，用于封装密码修改表单 -->
    <el-card style="width: 50%">

      <!-- 表单组件，用于收集用户输入的密码信息 -->
      <el-form ref="formRef" :model="user" :rules="rules" label-width="100px"
               style="padding-right: 50px">

        <!-- 原始密码输入项 -->
        <el-form-item label="原始密码" prop="password">
          <el-input show-password v-model="user.password" placeholder="原始密码"></el-input>
        </el-form-item>

        <!-- 新密码输入项 -->
        <el-form-item label="新密码" prop="newPassword">
          <el-input show-password v-model="user.newPassword" placeholder="新密码"></el-input>
        </el-form-item>

        <!-- 确认新密码输入项 -->
        <el-form-item label="确认新密码" prop="confirmPassword">
          <el-input show-password v-model="user.confirmPassword" placeholder="确认密码"></el-input>
        </el-form-item>

        <!-- 确认修改按钮 -->
        <div style="text-align: center; margin-bottom: 20px">
          <el-button type="primary" @click="update">确认修改</el-button>
        </div>

      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "Password", // 组件名称

  data() {
    // 自定义密码验证规则
    const validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请确认密码'))
      } else if (value !== this.user.newPassword) {
        callback(new Error('确认密码错误'))
      } else {
        callback()
      }
    }

    return {
      // 用户信息（从localStorage获取）
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),

      // 表单验证规则
      rules: {
        password: [
          {required: true, message: '请输入原始密码', trigger: 'blur'},
        ],
        newPassword: [
          {required: true, message: '请输入新密码', trigger: 'blur'},
        ],
        confirmPassword: [
          {validator: validatePassword, required: true, trigger: 'blur'},
        ],
      }
    }
  },

  created() {
    // 组件创建时无需额外操作
  },

  methods: {
    // 提交表单，执行密码更新操作
    update() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.$request.put('/updatePassword', this.user).then(res => {
            if (res.code === '200') {
              // 更新成功
              localStorage.removeItem('xm-user') // 清除缓存的用户信息
              this.$message.success('修改密码成功')
              this.$router.push('/login') // 跳转至登录页面
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    },
  }
}
</script>

<style scoped>
/* 通过 /deep/ 解决 scoped 样式穿透问题，为表单项标签添加粗体样式 */
/deep/ .el-form-item__label {
  font-weight: bold;
}
</style>
