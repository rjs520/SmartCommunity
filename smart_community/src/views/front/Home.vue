<template>
  <div style="min-height: calc(100vh - 86px);">
    <div style="width: 70%; margin: 10px auto">
      <!--      上面的区域-->
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
            <div @click="loadTopNews('new')" class="top-news"
                 :class="{ 'top-active' : sort === 'new' }">最新资讯
            </div>
            <div @click="loadTopNews('hot')" class="top-news"
                 :class="{ 'top-active' : sort === 'hot' }">最热资讯
            </div>
          </div>

          <div style="padding: 0 10px">
            <div @click="$router.push('/front/newsDetail?id=' + item.id)" v-for="item in topNews"
                 :key="item.id"
                 style="display: flex; grid-gap: 10px; margin: 10px 0; cursor: pointer">
              <div style="flex: 1; width: 0;" class="line1">{{ item.title }}</div>
              <div style="color: #666; font-size: 13px">{{ item.time }}</div>
            </div>
          </div>
        </div>

      </div>

      <!--      下面的区域-->
      <div style="margin: 30px 0; display: flex; grid-gap: 20px; min-height: 400px">
        <!--        左边区域-->
        <div style="flex: 1">
          <div style="display: flex; margin-bottom: 10px;">
            <div @click="loadCategoryNews(null)" class="category-item"
                 :class="{ 'category-active' : category === null }">全部
            </div>
            <div @click="loadCategoryNews(item.name)" class="category-item"
                 :class="{ 'category-active' : item.name === category }"
                 v-for="item in categoryList" :key="item.id">{{ item.name }}
            </div>
          </div>
          <div>
            <div v-for="item in tableData" :key="item.id" class="card"
                 style="display: flex; margin-bottom: 10px">
              <img :src="item.cover" alt="" style="width: 80px; height: 80px; border-radius: 5px">
              <div style="padding-left: 20px">
                <div style="margin-bottom: 30px; font-size: 18px" class="line1">{{
                    item.title
                  }}
                </div>
                <div style="font-size: 13px; color: #666">
                  <el-tag>{{ item.category }}</el-tag>
                  <span style="margin: 0 20px">{{ item.time }}</span>
                  <span>阅读 {{ item.count }}</span>
                </div>
              </div>
            </div>
          </div>
          <div style="margin: 10px 0" v-if="total > 0">
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

        <!--        右边区域-->
        <div style="width: 260px">
          <div style="margin-bottom: 10px; display: flex; align-items: center">
            <div style="flex: 1; font-size: 24px; ">社区活动</div>
            <a href="/front/activity">更多>></a>
          </div>
          <div class="card">
            <div @click="$router.push('/front/activityDetail?id=' + item.id)"
                 style="display: flex; grid-gap: 10px; margin-bottom: 15px; cursor: pointer"
                 v-for="item in activityList" :key="item.id">
              <img :src="item.cover" alt="" style="width: 60px; height: 60px; border-radius: 5px">
              <div style="flex: 1">
                <div style="height: 40px; margin-bottom: 5px" class="line2">{{ item.name }}</div>
                <div style="color: #666; font-size: 13px">{{ item.start }}</div>
              </div>
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
      topNews: [],
      categoryList: [],
      category: null,
      pageNum: 1,
      pageSize: 4,
      total: 0,
      tableData: [],
      activityList: []
    }
  },
  mounted() {
    this.loadTopNews('new')
    this.loadCategory()
    this.loadCategoryNews(null)
    this.loadActivity()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadActivity() {
      this.$request.get('/activity/selectPage', {
        params: {
          pageNum: 1,
          pageSize: 6
        }
      }).then(res => {
        this.activityList = res.data?.list || []
      })
    },
    loadCategoryNews(category) {
      this.category = category
      this.$request.get('/news/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          category: this.category,
        }
      }).then(res => {
        this.tableData = res.data?.list
        this.total = res.data?.total
      })
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.loadCategoryNews(this.category)
    },
    loadCategory() {
      this.$request.get('/category/selectAll').then(res => {
        this.categoryList = res.data || []
      })
    },
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

.category-item {
  padding: 10px 20px;
  background-color: #add8e6;
  color: black;
  border-radius: 10px;
  margin-right: 10px;
  cursor: pointer;
  border: none;
  transition: background-color 0.3s ease;
}

.category-item:hover {
  background-color: #87cefa;
}

.category-active {
  background-color: #2A60C9;
  color: white;
}
</style>
