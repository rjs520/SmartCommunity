<template>
  <!-- 页面主体 -->
  <div>
    <!-- 用户信息卡片组件 -->
    <el-card style="width: 50%">

      <!-- 用户信息表单 -->
      <el-form :model="user" label-width="100px" style="padding-right: 50px">

        <!-- 头像上传区域 -->
        <div style="margin: 15px; text-align: center">
          <!-- 使用el-upload组件实现头像上传功能 -->
          <el-upload
              class="avatar-uploader"
              :action="$baseUrl + '/files/upload'"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
          >
            <!-- 显示已上传的头像 -->
            <img v-if="user.avatar" :src="user.avatar" class="avatar"/>
            <!-- 未上传头像时显示加号图标 -->
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </div>

        <!-- 用户名输入项（只读） -->
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

        <!-- 底部操作区，包含“保存”按钮 -->
        <div style="text-align: center; margin-bottom: 20px">
          <el-button type="primary" @click="update">保 存</el-button>
        </div>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: "AdminPerson", // 组件名称

  data() {
    // 初始化数据：从localStorage中获取用户信息
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}')
    }
  },

  created() {
    // 组件创建时执行的逻辑（此处为空）
  },

  methods: {
    // 保存用户信息至数据库
    update() {
      this.$request.put('/admin/update', this.user).then(res => {
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
