<template>
  <div class="main-content parking-detail-content">
    <div class="card">
      <div style="display: flex; grid-gap: 20px">
        <img :src="parking.img" alt="" style="width: 50%;height: 50%">
        <div style="flex: 1; display: flex; flex-direction: column; justify-content: flex-start;
        ;">
          <div class="parking-address">车位地址：{{ parking.address }}</div>
          <div class="parking-item">
            <span style="color: black">停车位简介：</span>
            {{ parking.descr }}
          </div>
          <div class="parking-item"><span style="color: black">车位更新日期：</span>{{
              parking.time
            }}
          </div>
          <div>
            <el-button @click="handleSign" size="medium" type="primary"
                       v-if="parking.status === '未预约'">
              开始预约
            </el-button>
            <el-button size="medium" type="success" v-if="parking.status === '已预约' " disabled
                       key="appoint">
              已预约
            </el-button>
            <el-button size="medium" type="danger" v-if="parking.status === '已被使用' " disabled
                       key="isappoint">
              车位已被使用
            </el-button>
          </div>
        </div>
      </div>
    </div>
    <Comment :fid="id" module="parking"/>
    <el-dialog userName="预约信息" :visible.sync="fromVisible" width="40%"
               :close-on-click-modal="false" destroy-on-close>
      <el-form label-width="100px" style="padding-right: 50px" :model="form" :rules="rules"
               ref="formRef">
        <el-form-item prop="phone" label="手机号">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="fromVisible = false">取 消</el-button>
        <el-button type="primary" @click="sign">预约</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import Comment from "@/components/Comment"

export default {
  name: "ParkingDetail",
  components: {Comment},
  data() {
    return {
      id: this.$route.query.id,
      parking: {},
      fromVisible: false,
      form: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      rules: {
        phone: [
          {required: true, message: '请输入手机号', trigger: 'blur'}
        ]
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.$request.get('/parking/selectById/' + this.id).then(res => {
        this.parking = res.data || {}
        if (this.parking.status === '已预约') {
          this.parking.status = '已被使用';
        }
      })
    },
    handleSign() {
      this.form = {}
      this.form.userId = this.user.id
      this.fromVisible = true
      this.form.parkingId = this.id
    },
    sign() {
      this.$request.post('/parkingSign/add', this.form).then(res => {
        if (res.code === '200') {
          this.$message.success('预约成功')
          this.fromVisible = false
          this.load()
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>
.parking-address {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 40px;
}

.parking-detail-content {
  width: 60%;
  /* 添加更多样式以优化页面外观 */
}

.parking-item {
  color: #666;
  margin-bottom: 40px;
}
</style>/style>