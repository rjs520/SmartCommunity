<template>

  <div class="main-content">
    <div class="card">
      <div style="margin-bottom: 20px;font-size: 22px;font-weight: bold;text-align: center">
        {{ news.title }}
      </div>
      <div style="text-align: center;color: #2a60c9">
        <span style="margin-right: 20px">发布时间: {{ news.time }}</span>
        <span style="color: red"> 浏览量: {{ news.count }} </span>
      </div>
      <div style="margin: 30px 0;padding: 0 10px">
        <div class="w-e-text" style="line-height: 26px; text-indent: 2rem">
          <div v-html="news.content"></div>
        </div>
      </div>

    </div>

  </div>

</template>
<script>
export default {
  name: 'NewsDetail',
  data() {
    return {
      id: this.$route.query.id,
      news: {}
    }
  },
  created() {
    this.$request.put('/news/updateCount/' + this.id).then(res => {
      this.load()
    })
  },
  methods: {
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