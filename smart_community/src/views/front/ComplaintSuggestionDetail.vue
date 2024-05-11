<template>
  <div class="main-content complaintSuggestion-detail-content">
    <div class="card">
      <div style="display: flex; grid-gap: 20px">
        <img :src="complaintSuggestion.cover" alt="" style="width: 40%;height: 40%">
        <div style="flex: 1; display: flex; flex-direction: column; justify-content: flex-start;
        ;">
          <div class="complaintSuggestion-address">{{ complaintSuggestion.name }}</div>
          <div class="complaintSuggestion-item">
            <span style="color: black">简介：</span>
            {{ complaintSuggestion.descr }}
          </div>
          <div class="complaintSuggestion-item"><span style="color: black">日期：</span>{{
              complaintSuggestion.date
            }}
          </div>
          <div>
            <el-button @click="handleSign" size="medium" type="primary">
              提交
            </el-button>
          </div>
        </div>
      </div>
    </div>
    <Comment :fid="id" module="complaintSuggestion"/>
    <el-dialog userName="预约信息" :visible.sync="fromVisible" width="40%"
               :close-on-click-modal="false" destroy-on-close>
      <el-form label-width="100px" style="padding-right: 50px" :model="form"
               ref="formRef">
        <el-form-item prop="content" label="内容">
          <el-input v-model="form.content" autocomplete="off"></el-input>
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
  name: "ComplaintSuggestionDetail",
  components: {Comment},
  data() {
    return {
      id: this.$route.query.id,
      complaintSuggestion: {},
      fromVisible: false,
      form: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.$request.get('/complaintSuggestion/selectById/' + this.id).then(res => {
        this.complaintSuggestion = res.data || {}
      })
    },
    handleSign() {
      this.form = {}
      this.form.userId = this.user.id
      this.fromVisible = true
      this.form.csId = this.id
    },
    sign() {
      this.$request.post('/recs/add', this.form).then(res => {
        if (res.code === '200') {
          this.$message.success('提交成功')
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
.complaintSuggestion-address {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 40px;
}

.complaintSuggestion-detail-content {
  width: 60%;
  /* 添加更多样式以优化页面外观 */
}

.complaintSuggestion-item {
  color: #666;
  margin-bottom: 40px;
}
</style>/style>