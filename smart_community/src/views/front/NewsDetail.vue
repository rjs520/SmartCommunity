<template>
  <!-- 主要内容区域 -->
  <div class="main-content" style="width: 60%">
    <!-- 资讯卡片 -->
    <div class="card">
      <!-- 资讯标题与发布信息 -->
      <div style="margin-bottom: 20px;font-size: 22px;font-weight: bold;text-align: center">
        {{ news.title }}
      </div>
      <div style="text-align: center;color: #2a60c9">
        <span style="margin-right: 20px">发布时间: {{ news.time }}</span>
        <span style="color: red"> 浏览量: {{ news.count }} </span>
      </div>
      <!-- 资讯内容 -->
      <div style="margin: 30px 0;padding: 0 10px">
        <div class="w-e-text" style="line-height: 26px; text-indent: 2rem">
          <div v-html="news.content"></div>
        </div>
      </div>
    </div>
    <Comment :fid="id" module="news"/>
  </div>
</template>

<script>
import Comment from "@/components/Comment"

export default {
  name: 'NewsDetail',
  components: {Comment},
  data() {
    return {
      id: this.$route.query.id, // 资讯ID
      news: {}, // 存储资讯详情
    }
  },
  created() {
    // 页面创建时更新资讯浏览量并加载资讯详情和评论
    this.$request.put('/news/updateCount/' + this.id).then(() => {
      this.load()
    })
  },
  methods: {

    /**
     * 加载资讯详情
     */
    load() {
      this.$request.get('/news/selectById/' + this.id).then(res => {
        this.news = res.data || {}
      })
    }
  }
}

</script>

<style scoped>


</style>
