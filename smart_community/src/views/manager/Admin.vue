模板
<template>
  <!-- 主内容区域 -->
  <div class="main-content">
    <!-- 用户信息卡片组件 -->
    <el-card style="width: 50%; margin: 30px auto">
      <!-- 右侧操作区 -->
      <div style="text-align: right; margin-bottom: 20px">
        <!-- 修改密码按钮 -->
        <el-button type="primary" @click="updatePassword">修改密码</el-button>
      </div>
      <!-- 用户信息表单 -->
      <el-form :model="user" label-width="80px" style="padding-right: 20px">
        <!-- 头像上传区 -->
        <div style="margin: 15px; text-align: center">
          <!-- 头像上传组件 -->
          <el-upload
              class="avatar-uploader"
              :action="$baseUrl + '/files/upload'"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
          >
            <!-- 已上传头像展示 -->
            <img v-if="user.avatar" :src="user.avatar" class="avatar" alt=""/>
            <!-- 未上传头像时的上传图标 -->
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </div>
        <!-- 用户名输入项 -->
        <el-form-item label="用户名" prop="username">
          <el-input v-model="user.username" placeholder="用户名" disabled></el-input>
        </el-form-item>
        <!-- 姓名输入项 -->
        <el-form-item label="姓名" prop="name">
          <el-input v-model="user.name" placeholder="姓名"></el-input>
        </el-form-item>
        <!-- 电话输入项 -->
        <el-form-item label="电话" prop="phone">
          <el-input v-model="user.phone" placeholder="电话"></el-input>
        </el-form-item>
        <!-- 邮箱输入项 -->
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="user.email" placeholder="邮箱"></el-input>
        </el-form-item>
        <!-- 保存按钮 -->
        <div style="text-align: center; margin-bottom: 20px">
          <el-button type="primary" @click="update">保 存</el-button>
        </div>
      </el-form>
    </el-card>

    <!-- 修改密码对话框 -->
    <el-dialog title="修改密码" :visible.sync="dialogVisible" width="30%"
               :close-on-click-modal="false" destroy-on-close>
      <!-- 密码修改表单 -->
      <el-form :model="user" label-width="80px" style="padding-right: 20px" :rules="rules"
               ref="formRef">
        <!-- 原始密码输入项 -->
        <el-form-item label="原始密码" prop="password">
          <el-input show-password v-model="user.password" placeholder="原始密码"></el-input>
        </el-form-item>
        <!-- 新密码输入项 -->
        <el-form-item label="新密码" prop="newPassword">
          <el-input show-password v-model="user.newPassword" placeholder="新密码"></el-input>
        </el-form-item>
        <!-- 确认密码输入项 -->
        <el-form-item label="确认密码" prop="confirmPassword">
          <el-input show-password v-model="user.confirmPassword" placeholder="确认密码"></el-input>
        </el-form-item>
      </el-form>
      <!-- 对话框底部操作区 -->
      <div slot="footer" class="dialog-footer">
        <!-- 取消按钮 -->
        <el-button @click="fromVisible = false">取 消</el-button>
        <!-- 确定按钮 -->
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    /**
     * 验证确认密码规则
     * @param rule 表单验证规则对象
     * @param value 输入的确认密码值
     * @param callback 回调函数，用于处理验证结果
     */
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
      // 当前登录用户的详细信息，从localStorage中获取并解析
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),

      // 修改密码对话框是否可见
      dialogVisible: false,

      // 表单验证规则
      rules: {
        // 原始密码验证规则
        password: [
          {required: true, message: '请输入原始密码', trigger: 'blur'},
        ],
        // 新密码验证规则
        newPassword: [
          {required: true, message: '请输入新密码', trigger: 'blur'},
        ],
        // 确认密码验证规则
        confirmPassword: [
          {validator: validatePassword, required: true, trigger: 'blur'},
        ],
      }
    }
  },

  created() {
    // 页面初始化逻辑（目前为空）
  },

  methods: {
    /**
     * 保存用户信息到数据库
     */
    update() {
      this.$request.put('/admin/update', this.user).then(res => {
        if (res.code === '200') {
          // 成功更新
          this.$message.success('保存成功')

          // 更新浏览器缓存里的用户信息
          localStorage.setItem('xm-user', JSON.stringify(this.user))

          // 触发父级的数据更新
          this.$emit('update:user')
        } else {
          this.$message.error(res.msg)
        }
      })
    },

    /**
     * 处理头像上传成功的回调
     * @param response 服务器响应数据
     * @param file 上传的文件对象
     * @param fileList 文件列表
     */
    handleAvatarSuccess(response, file, fileList) {
      // 把user的头像属性换成上传的图片的链接
      this.$set(this.user, 'avatar', response.data)
    },

    /**
     * 打开修改密码对话框
     */
    updatePassword() {
      this.dialogVisible = true
    },

    /**
     * 提交密码修改表单并处理结果
     */
    save() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.$request.put('/updatePassword', this.user).then(res => {
            if (res.code === '200') {
              // 成功更新
              this.$message.success('修改密码成功')
              this.$router.push('/login')
            } else {
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
/* 重写表单标签样式，加粗字体 */
/deep/ .el-form-item__label {
  font-weight: bold;
}

/* 重写头像上传组件样式，使其圆形显示 */
/deep/ .el-upload {
  border-radius: 50%;
}

/* 为头像上传组件添加边框和悬停效果 */
/deep/ .avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  border-radius: 50%;
}

/* 悬停时改变边框颜色 */
/deep/ .avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

/* 设置上传图标大小和样式 */
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
  border-radius: 50%;
}

/* 设置已上传头像的大小和圆角 */
.avatar {
  width: 120px;
  height: 120px;
  display: block;
  border-radius: 50%;
}
</style>
