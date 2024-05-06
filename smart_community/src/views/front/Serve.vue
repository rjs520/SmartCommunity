<template>
  <div class="main-content">
    <div>
      <el-row :gutter="10">
        <el-col :span="6" v-for="item in serveList" :key="item.id">
          <div class="card">
            <img :src="item.cover" alt="" style="width: 100%; height: 140px">
            <div style="margin: 5px 0">{{ item.name }}</div>
            <div style="margin-bottom: 15px; color: #666; font-size: 12px">{{ item.descr }}</div>
            <div style="text-align: right">
              <el-button type="primary" @click="addReserve(item.id)">预约服务</el-button>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "FrontServe",
  data() {
    return {
      serveList: [],
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.$request.get('/serve/selectAll').then(res => {
        this.serveList = res.data || []
      })
    },
    addReserve(serveId) {
      this.$request.post('/reserve/add', {serveId: serveId, userId: this.user.id}).then(res => {
        if (res.code === '200') {
          this.$message.success('预约成功')
        } else {
          this.$message.error(res.msg)
        }
      })
    },
  }
}
</script>

<style scoped>

</style>