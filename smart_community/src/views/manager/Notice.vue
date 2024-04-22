<template>
  <!-- 页面主体容器 -->
  <div>

    <!-- 查询区域 -->
    <div class="search">
      <!-- 输入框，用于输入标题进行查询 -->
      <el-input v-model="title" placeholder="请输入标题查询" style="width: 200px"></el-input>
      <!-- 查询按钮，点击后执行查询操作 -->
      <el-button plain style="margin-left: 10px" type="info" @click="load(1)">查询</el-button>
      <!-- 重置按钮，点击后清除查询条件并重新加载数据 -->
      <el-button plain style="margin-left: 10px" type="warning" @click="reset">重置</el-button>
    </div>

    <!-- 操作区域 -->
    <div class="operation">
      <!-- 新增按钮，点击后打开新增弹窗 -->
      <el-button plain type="primary" @click="handleAdd">新增</el-button>
      <!-- 批量删除按钮，点击后执行批量删除操作 -->
      <el-button plain type="danger" @click="delBatch">批量删除</el-button>
    </div>

    <!-- 数据表格区域 -->
    <div class="table">
      <!-- 数据表格组件，展示公告列表 -->
      <el-table :data="tableData" stripe @selection-change="handleSelectionChange">
        <!-- 复选框列 -->
        <el-table-column align="center" type="selection" width="55"></el-table-column>
        <!-- 序号列 -->
        <el-table-column align="center" label="序号" prop="id" sortable
                         width="80"></el-table-column>
        <!-- 标题列 -->
        <el-table-column label="标题" prop="title" show-overflow-tooltip></el-table-column>
        <!-- 内容列 -->
        <el-table-column label="内容" prop="content" show-overflow-tooltip></el-table-column>
        <!-- 创建时间列 -->
        <el-table-column label="创建时间" prop="time"></el-table-column>
        <!-- 创建人列 -->
        <el-table-column label="创建人" prop="user"></el-table-column>

        <!-- 操作列，包含编辑和删除按钮 -->
        <el-table-column align="center" label="操作" width="180">
          <template v-slot="scope">
            <!-- 编辑按钮，点击后打开编辑弹窗 -->
            <el-button plain size="mini" type="primary" @click="handleEdit(scope.row)">编辑
            </el-button>
            <!-- 删除按钮，点击后执行单条数据删除操作 -->
            <el-button plain size="mini" type="danger" @click=del(scope.row.id)>删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页器 -->
      <div class="pagination">
        <el-pagination
            :current-page="pageNum"
            :page-size="pageSize"
            :page-sizes="[5, 10, 20]"
            :total="total"
            background
            layout="total, prev, pager, next"
            @current-change="handleCurrentChange">
        </el-pagination>
      </div>
    </div>

    <!-- 新增/编辑弹窗 -->
    <el-dialog :close-on-click-modal="false" :visible.sync="fromVisible" destroy-on-close
               title="信息"
               width="40%">
      <!-- 表单组件，用于新增或编辑公告信息 -->
      <el-form ref="formRef" :model="form" :rules="rules" label-width="100px"
               style="padding-right: 50px">
        <!-- 标题输入项 -->
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" autocomplete="off"></el-input>
        </el-form-item>
        <!-- 内容输入项（文本域） -->
        <el-form-item label="内容" prop="content">
          <el-input v-model="form.content" :rows="5" autocomplete="off" type="textarea"></el-input>
        </el-form-item>
      </el-form>
      <!-- 弹窗底部操作区 -->
      <div slot="footer" class="dialog-footer">
        <!-- 取消按钮，关闭弹窗 -->
        <el-button @click="fromVisible = false">取 消</el-button>
        <!-- 确定按钮，提交表单并关闭弹窗 -->
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Notice", // 组件名称

  data() {
    return {
      // 公告列表数据
      tableData: [],
      // 当前页码
      pageNum: 1,
      // 每页显示数量
      pageSize: 10,
      // 总记录数
      total: 0,
      // 查询标题
      title: null,
      // 新增/编辑弹窗可见性
      fromVisible: false,
      // 新增/编辑表单数据
      form: {},
      // 当前用户信息
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      // 表单验证规则
      rules: {
        title: [
          {required: true, message: '请输入标题', trigger: 'blur'},
        ],
        content: [
          {required: true, message: '请输入内容', trigger: 'blur'},
        ]
      },
      // 选中的公告ID列表（用于批量删除）
      ids: []
    }
  },

  created() {
    // 组件创建时加载第一页数据
    this.load(1)
  },

  methods: {
    // 打开新增弹窗
    handleAdd() {
      this.form = {}  // 清空表单数据
      this.fromVisible = true   // 打开弹窗
    },

    // 打开编辑弹窗
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))  // 深拷贝选中行数据到表单
      this.fromVisible = true   // 打开弹窗
    },

    // 提交表单（新增或更新公告）
    save() {
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          const url = this.form.id ? '/notice/update' : '/notice/add'
          const method = this.form.id ? 'PUT' : 'POST'

          this.$request({url, method, data: this.form}).then(res => {
            if (res.code === '200') {
              this.$message.success('保存成功')
              this.load(1) // 保存成功后刷新数据
              this.fromVisible = false // 关闭弹窗
            } else {
              this.$message.error(res.msg)
            }
          })
        }
      })
    },

    // 删除单条公告
    del(id) {
      this.$confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(() => {
        this.$request.delete(`/notice/delete/${id}`).then(res => {
          if (res.code === '200') {
            this.$message.success('操作成功')
            this.load(1) // 删除成功后刷新数据
          } else {
            this.$message.error(res.msg)
          }
        })
      }).catch(() => {
      })
    },

    // 监听表格选中项变化，更新ids数组
    handleSelectionChange(rows) {
      this.ids = rows.map(v => v.id)
    },

    // 批量删除公告
    delBatch() {
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }

      this.$confirm('您确定批量删除这些数据吗？', '确认删除', {type: "warning"}).then(() => {
        this.$request.delete('/notice/delete/batch', {data: this.ids}).then(res => {
          if (res.code === '200') {
            this.$message.success('操作成功')
            this.load(1) // 批量删除成功后刷新数据
          } else {
            this.$message.error(res.msg)
          }
        })
      }).catch(() => {
      })
    },

    // 分页查询公告数据
    load(pageNum) {
      if (pageNum) {
        this.pageNum = pageNum
      }

      const params = {
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        title: this.title,
      }

      this.$request.get('/notice/selectPage', {params}).then(res => {
        this.tableData = res.data?.list
        this.total = res.data?.total
      })
    },

    // 重置查询条件并重新加载数据
    reset() {
      this.title = null
      this.load(1)
    },

    // 分页器页码变化时触发的方法
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
  }
}
</script>

<style scoped>
/* 在这里添加 scoped 样式 */
</style>
