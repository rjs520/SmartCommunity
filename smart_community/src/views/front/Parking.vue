<template>
  <div class="main-content">
    <div>
      <el-row :gutter="10">
        <el-col :span="6" v-for="item in parkingList" :key="item.id">
          <div style="margin-bottom: 15px;cursor: pointer"
               @click="$router.push('/front/parkingDetail?id='+ item.id)">
            <img :src="item.img" alt=""
                 style="width: 100%;height: 160px;border-radius: 5px 5px 0 0;display: block ">
            <div
                style="padding: 10px; background-color: #fff;box-shadow: -3px 3px 3px -2px rgba(0,0,0,0.1), 3px 3px 3px -2px rgba(0,0,0,0.1); ">
              <div class="line1" style="height: 40px;margin-bottom: 5px;font-size: 20px">{{ item.address }}</div>
              <div style="font-size: 13px;color: #666">{{ item.descr }}</div>
              <div style="font-size: 12px;color: #666c">{{ item.time}}</div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
    <div style="margin: 10px 0" v-if="total">
      <el-pagination
          background
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-size="pageSize"
          layout="total, prev, pager, next"
          :total="total">
      </el-pagination>
    </div>
  </div>


</template>
<script>
export default {
  name: 'FrontParking',
  data() {
    return {
      parkingList: [],
      pageSize: 8,
      pageNum: 1,
      total: 0,
    }
  },
  created() {
    this.load(1)
  },

  methods: {
    load(pageNum) {  // 分页查询
      if (pageNum) {
        this.pageNum = pageNum
      }
      this.$request.get('/parking/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
        }
      }).then(res => {
        this.parkingList = res.data?.list
        this.total = res.data?.total
      })
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
  }
}

</script>

<style scoped>

</style>