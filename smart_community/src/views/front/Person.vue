<template>
  <!-- 主要内容区域 -->
  <div class="main-content">
    <!-- 用户信息卡片组件 -->
    <el-card style="width: 50%; margin: 30px auto">
      <!-- 右侧操作区，包含“修改密码”按钮 -->
      <div style="text-align: right; margin-bottom: 20px">
        <el-button type="primary" @click="updatePassword">修改密码</el-button>
      </div>

      <!-- 用户信息表单 -->
      <el-form :model="user" label-width="80px" style="padding-right: 20px">

        <!-- 头像上传区域 -->
        <div style="margin: 15px; text-align: center">
          <!-- 使用el-upload组件实现头像上传功能 -->
          <el-upload
              :action="$baseUrl + '/files/upload'"
              :on-success="handleAvatarSuccess"
              :show-file-list="false"
              class="avatar-uploader"
          >
            <!-- 显示已上传的头像 -->
            <img v-if="user.avatar" :src="user.avatar" class="avatar" alt=""/>
            <!-- 未上传头像时显示加号图标 -->
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </div>

        <!-- 用户名输入项（只读） -->
        <el-form-item label="用户名" prop="username">
          <el-input v-model="user.username" disabled placeholder="用户名"></el-input>
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

        <!-- 底部操作区，包含“保存”按钮 -->
        <div style="text-align: center; margin-bottom: 20px">
          <el-button type="primary" @click="update">保 存</el-button>
        </div>
      </el-form>
    </el-card>

    <!-- 修改密码弹窗 -->
    <el-dialog :close-on-click-modal="false" :visible.sync="dialogVisible" destroy-on-close
               title="修改密码" width="30%">

      <!-- 密码修改表单 -->
      <el-form ref="formRef" :model="user" :rules="rules" label-width="80px"
               style="padding-right: 20px">

        <!-- 原始密码输入项 -->
        <el-form-item label="原始密码" prop="password">
          <el-input v-model="user.password" placeholder="原始密码" show-password></el-input>
        </el-form-item>

        <!-- 新密码输入项 -->
        <el-form-item label="新密码" prop="newPassword">
          <el-input v-model="user.newPassword" placeholder="新密码" show-password></el-input>
        </el-form-item>

        <!-- 确认新密码输入项 -->
        <el-form-item label="确认密码" prop="confirm">
          <el-input v-model="user.confirm" placeholder="确认密码" show-password></el-input>
        </el-form-item>
      </el-form>

      <!-- 弹窗底部操作区，包含“取消”和“确定”按钮 -->
      <div slot="footer" class="dialog-footer">
        <el-button @click="fromVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    // 自定义验证规则：确认密码与新密码是否一致
    const validatePassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请确认密码'))
      } else if (value !== this.user.newPassword) {
        callback(new Error('确认密码错误'))
      } else {
        callback()
      }
    }

    // 初始化数据
    return {
      // 从localStorage中获取用户信息
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),

      // 修改密码弹窗是否可见
      dialogVisible: false,

      // 表单验证规则
      rules: {
        password: [
          {required: true, message: '请输入原始密码', trigger: 'blur'},
        ],
        newPassword: [
          {required: true, message: '请输入新密码', trigger: 'blur'},
        ],
        confirm: [
          {validator: validatePassword, required: true, trigger: 'blur'},
        ],
      }
    }
  },

  created() {
    // 组件创建时执行的逻辑（此处为空）
  },

  methods: {
    // 保存用户信息至数据库
    update() {
      this.$request.put('/user/update', this.user).then(res => {
        if (res.code === '200') {
          // 提示保存成功，更新浏览器缓存中的用户信息，并触发父组件的用户信息更新
          this.$message.success('保存成功')
          localStorage.setItem('xm-user', JSON.stringify(this.user))
          this.$emit('update:user')
        } else {
          this.$message.error(res.msg)
        }
      })
    },

    // 处理头像上传成功事件，更新用户头像属性
    handleAvatarSuccess(response, file, fileList) {
      this.$set(this.user, 'avatar', response.data)
    },

    // 打开修改密码弹窗
    updatePassword() {
      // 原始密码设置为空
      this.user.password = '';
      this.dialogVisible = true
    },

    // 提交密码修改表单
    save() {
      // 验证表单数据，验证通过后提交请求
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.$request.put('/updatePassword', this.user).then(res => {
            if (res.code === '200') {
              // 提示密码修改成功，跳转至登录页面
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
/* 覆盖样式，设置表单标签字体加粗 */
/deep/ .el-form-item__label {
  font-weight: bold;
}

/* 设置上传组件为圆形 */
/deep/ .el-upload {
  border-radius: 50%;
}

/* 设置头像上传区域样式 */
/deep/ .avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  border-radius: 50%;
}

/* 鼠标悬停时，上传区域边框颜色变为浅蓝色 */
/deep/ .avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

/* 上传按钮的默认图标样式 */
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
  border-radius: 50%;
}

/* 已上传头像样式 */
.avatar {
  width: 120px;
  height: 120px;
  display: block;
  border-radius: 50%;
}
</style>
