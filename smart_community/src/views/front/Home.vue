<template>
  <div style="min-height: calc(100vh - 86px);">
    <div style="width: 70%; margin: 10px auto">

      <div style="display: flex; grid-gap: 10px">
        <div style="flex: 1">
          <el-carousel height="300px">
            <el-carousel-item v-for="item in imgs" :key="item">
              <img :src="item" alt="" style="width: 100%">
            </el-carousel-item>
          </el-carousel>
        </div>

        <div style="flex: 1">
          <div style="display: flex; border-bottom: 1px solid #ddd; margin-bottom: 20px">
            <div @click="loadTopNews('new')" class="top-news" :class="{ 'top-active' : sort === 'new' }">最新资讯</div>
            <div @click="loadTopNews('hot')" class="top-news" :class="{ 'top-active' : sort === 'hot' }">最热资讯</div>
          </div>

          <div style="padding: 0 10px">
            <div @click="$router.push('/front/newsDetail?id=' + item.id)" v-for="item in topNews" :key="item.id" style="display: flex; grid-gap: 10px; margin: 10px 0; cursor: pointer">
              <div style="flex: 1; width: 0;" class="line1">{{ item.title }}</div>
              <div style="color: #666; font-size: 13px">{{ item.time }}</div>
            </div>
          </div>
        </div>

      </div>


    </div>
  </div>
</template>

<script>

export default {

  data() {
    return {
      imgs: [
        require('@/assets/imgs/carousel/1.png'),
        require('@/assets/imgs/carousel/2.png'),
      ],
      sort: 'new',  // hot
      topNews: []
    }
  },
  mounted() {
    this.loadTopNews('new')
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadTopNews(sort) {
      this.sort = sort
      this.$request.get('/news/selectTopNews?sort=' + this.sort).then(res => {
        this.topNews = res.data || []
      })
    }
  }
}
</script>

<style scoped>
.top-news {
  padding: 10px;
  cursor: pointer;
}

.top-active {
  border-bottom: 2px solid #2A60C9
}
</style>
