<template>
  <div class="main-content">

    <div style="font-size: 20px; margin-bottom: 20px">我的建议和反馈</div>

    <div class="table">
      <el-table size="medium" :data="tableData" strip @selection-change="handleSelectionChange">
        <el-table-column label="标题" width="140" show-overflow-tooltip>
          <template v-slot="scope">
            <a :href="'/front/ComplaintSuggestionDetail?id=' + scope.row.csId"
               target="_blank">{{ scope.row.csName }}</a>
          </template>
        </el-table-column>
        <el-table-column prop="content" label="内容">
          <template v-slot="scope">
            {{ scope.row.content | stripHTML }}
          </template>
        </el-table-column>
        <el-table-column prop="time" width="200" label="日期"></el-table-column>
        <el-table-column prop="status" label="回复状态">
          <template v-slot="scope">
            <el-tag type="primary" v-if="scope.row.status === '待回复'">待回复</el-tag>
            <el-tag type="success" v-if="scope.row.status === '已回复'">已回复</el-tag>
            <el-tag type="danger" v-if="scope.row.status === '已拒绝'">已拒绝</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="reason" label="回复意见"></el-table-column>
        <el-table-column label="操作" align="center" width="180">
          <template v-slot="scope">
            <el-button v-if="scope.row.status !== '已回复'" size="mini" type="danger" plain
                       @click="del(scope.row.id)">取消
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <div style="margin: 10px 0">
        <el-pagination
            background
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[5, 10, 20]"
            :page-size="pageSize"
            layout="total, prev, pager, next"
            :total="total">
        </el-pagination>
      </div>
    </div>


  </div>
</template>

<script>
export default {
  name: "FrontRecs",
  data() {
    return {
      tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      userName: null,
      fromVisible: false,
      form: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      ids: []
    }
  },
  created() {
    this.load(1)
  },
  methods: {
    del(id) {   // 单个删除
      this.$confirm('您确定取消吗？', '确认取消', {type: "warning"}).then(() => {
        this.$request.delete('/recs/delete/' + id).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    handleSelectionChange(rows) {   // 当前选中的所有的行数据
      this.ids = rows.map(v => v.id)   //  [1,2]
    },
    delBatch() {   // 批量删除
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('您确定批量删除这些数据吗？', '确认删除', {type: "warning"}).then(() => {
        this.$request.delete('/recs/delete/batch', {data: this.ids}).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    load(pageNum) {  // 分页查询
      if (pageNum) {
        this.pageNum = pageNum
      }
      this.$request.get('/recs/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          userName: this.userName,
        }
      }).then(res => {
        this.tableData = res.data?.list
        this.total = res.data?.total
      })
    },
    reset() {
      this.userName = null
      this.load(1)
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
  },
  filters: {
    stripHTML: function (value) {
      // 使用正则表达式移除除 img 外的 HTML 标签
      let strippedValue = value.replace(/<(?!img)[^>]+>/g, '')
      // 限制字符数量为 20
      return strippedValue.length > 20 ? strippedValue.substring(0, 20) + '...' : strippedValue
    }
  },
}
</script>

<style scoped>

</style>
