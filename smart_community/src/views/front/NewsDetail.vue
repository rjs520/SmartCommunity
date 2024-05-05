<template>
  <!-- 主要内容区域 -->
  <div class="main-content">
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
    <!-- 评论区域 -->
    <div class="card" style="margin: 10px 0">
      <!-- 评论数量与输入框 -->
      <div style="font-size: 20px;font-weight: bold;margin-bottom: 15px">评论：{{ commentCount }}
      </div>
      <div style="margin-bottom: 20px">
        <el-input type="textarea" placeholder="请输入评论内容" v-model="form.content"></el-input>
        <div style="margin-top: 10px;text-align: right">
          <el-button type="primary" @click="addComment(null)">评论</el-button>
        </div>
      </div>
      <!-- 评论列表 -->
      <div>
        <div v-for="item in commentList" :key="item.id"
             style="display: flex; grid-gap: 15px;margin-bottom: 20px">
          <img :src="item.avatar" alt="" style="width: 50px;height: 50px; border-radius: 50%"/>
          <div style="flex: 1">
            <!-- 评论用户信息与操作 -->
            <div style="margin-bottom: 5px;color: #666">{{ item.userName }}
            </div>
            <div style="margin-bottom: 5px;">{{ item.content }}
            </div>
            <div style="font-size: 13px;color: #666;margin-bottom: 10px"><span>{{
                item.time
              }}</span>
              <span style="margin: 0 20px; cursor: pointer" @click="handleReplyContent(item)"
                    :class="{ 'highLight' : item.showReplyContent }">
                <i class="el-icon-s-comment"></i>回复
              </span>
              <span style="cursor: pointer" @click="del(item.id)" v-if="user.id === item.userId">
              <i class="el-icon-delete"></i>删除
            </span>
            </div>
            <!-- 回复区域 -->
            <div style="margin-bottom: 10px" v-if="item.showReplyContent">
              <el-input type="textarea" placeholder="请输入回复内容"
                        v-model="item.replyContent"></el-input>
              <div style="margin-top: 5px;text-align: right">
                <el-button type="primary" size="mini" @click="addComment(item)">回复</el-button>
              </div>
            </div>
            <!-- 子评论列表 -->
            <div style="margin-top: 10px" v-if="item.children.length">
              <div v-for="sub in item.children" :key="sub.id"
                   style="display: flex; grid-gap: 15px;margin-bottom: 10px">
                <img :src="sub.avatar" alt=""
                     style="width: 50px;height: 50px; border-radius: 50%"/>
                <div style="flex: 1">
                  <!-- 子评论用户信息与操作 -->
                  <div style="margin-bottom: 5px;color: #666">{{ sub.userName }}
                    <span v-if="sub.replyUser && sub.replyUser !== item.userName">回复 {{
                        sub.replyUser || '默认用户名'
                      }}</span>
                  </div>
                  <div style="margin-bottom: 5px;">{{ sub.content }}</div>
                  <div style="font-size: 13px;color: #666;margin-bottom: 10px"><span>{{
                      sub.time
                    }}</span>
                    <span style="margin: 0 20px; cursor: pointer" @click="handleReplyContent(sub)"
                          :class="{ 'highLight' : sub.showReplyContent }">
                <i class="el-icon-s-comment"></i>回复
              </span>
                    <span style="cursor: pointer" @click="del(sub.id)"
                          v-if="user.id === sub.userId">
              <i class="el-icon-delete"></i>删除
            </span>
                  </div>
                  <!-- 子评论的回复区域 -->
                  <div style="margin-bottom: 10px" v-if="sub.showReplyContent">
                    <el-input type="textarea" placeholder="请输入回复内容"
                              v-model="sub.replyContent"></el-input>
                    <div style="margin-top: 5px;text-align: right">
                      <el-button type="primary" size="mini" @click="addComment(sub)">回复
                      </el-button>
                    </div>
                  </div>
                </div>
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
  name: 'NewsDetail',
  data() {
    return {
      id: this.$route.query.id, // 资讯ID
      news: {}, // 存储资讯详情
      commentCount: 0, // 评论数量
      form: {}, // 用于提交评论的表单数据
      commentList: [], // 存储评论列表
      user: JSON.parse(localStorage.getItem('xm-user') || '{}') // 当前登录的用户信息
    }
  },
  created() {
    // 页面创建时更新资讯浏览量并加载资讯详情和评论
    this.$request.put('/news/updateCount/' + this.id).then(() => {
      this.load()
    })
    this.loadComment()
  },
  methods: {
    /**
     * 添加评论
     * @param {Object} commentPid - 父评论对象，用于回复评论
     */
    addComment(commentPid) {
      this.form.module = 'news'
      this.form.fid = this.id
      this.form.userId = this.user.id
      if (commentPid) {
        // 如果存在父评论，则回复该评论
        this.form.pid = commentPid.id
        this.form.content = commentPid.replyContent
      }
      this.$request.post('/comment/add', this.form).then(res => {
        if (res.code === '200') {
          this.$message.success('评论成功')
          this.loadComment()
          this.form = {}
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    /**
     * 加载评论
     */
    loadComment() {
      // 加载评论列表和评论数量
      this.$request.get('/comment/selectTree/' + this.id + '/news').then(res => {
        this.commentList = res.data || []
      })
      this.$request.get('/comment/selectCount/' + this.id + '/news').then(res => {
        this.commentCount = res.data || 0
      })
    },
    /**
     * 处理回复评论的显示与隐藏
     * @param {Object} comment - 评论对象
     */
    handleReplyContent(comment) {
      this.$set(comment, 'showReplyContent', !comment.showReplyContent)
    },
    /**
     * 加载资讯详情
     */
    load() {
      this.$request.get('/news/selectById/' + this.id).then(res => {
        this.news = res.data || {}
      })
    },
    /**
     * 删除评论
     * @param {String} id - 评论ID
     */
    del(id) {
      // 弹窗确认后删除评论
      this.$confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(() => {
        this.$request.delete('/comment/deepDelete/' + id).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.loadComment()
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
  }
}

</script>

<style scoped>
.highLight {
  color: #2a60c9;
}

</style>
