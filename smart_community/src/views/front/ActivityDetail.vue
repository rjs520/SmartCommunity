<template>
  <div class="main-content" style="width: 60%">
    <div class="card">
      <div style="display: flex; grid-gap: 20px">
        <img :src="activity.cover" alt="" style="width: 50%">
        <div style="flex: 1; display: flex; flex-direction: column; justify-content: flex-start;
        ;">
          <div class="activity-name">{{ activity.name }}</div>
          <div class="activity-item">
            <span style="color: black">活动简介：</span>
            {{ activity.descr }}
          </div>
          <div class="activity-item"><span style="color: black">活动日期：</span>{{ activity.start }}
            ~
            {{ activity.end }}
          </div>
          <div class="activity-item"><span style="color: black">活动地址：</span>
            {{ activity.address }}
          </div>
          <div>
            <el-button size="medium" type="primary" v-if="activity.status === '未报名'"
            >开始报名
            </el-button>
            <el-button size="medium" type="success" v-if="activity.status === '已报名' "
                       disabled>
              已报名
            </el-button>
            <el-button size="medium" type="info" v-if="activity.status === '未开始' "
                       sisabled>
              活动未开始
            </el-button>
            <el-button size="medium" type="danger" v-if="activity.status === '已结束' "
                       sisabled>
              活动已结束
            </el-button>
          </div>
        </div>
      </div>
      <div style="margin: 30px 0">
        <div style="margin-bottom: 50px;font-size: 22px;font-weight: bold;text-align: center">
          - 活动详情 -
        </div>
        <div class="w-e-text" style="line-height: 26px">
          <div v-html="activity.content"></div>
        </div>
      </div>
    </div>
    <Comment :fid="id" module="activity"/>
  </div>
</template>

<script>
import Comment from "@/components/Comment"

export default {
  name: "ActivityDetail",
  components: {Comment},
  data() {
    return {
      id: this.$route.query.id,
      activity: {}
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.$request.get('/activity/selectById/' + this.id).then(res => {
        this.activity = res.data || {}
        this.activity.status = '未报名'
      })
    }
  }
}
</script>

<style scoped>
.activity-name {
  font-size: 20px;
  font-weight: bold;
  margin-bottom: 40px;
}

.activity-item {
  color: #666;
  margin-bottom: 40px;
}
</style>/style>