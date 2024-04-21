<template>
  <!-- 页面主体容器 -->
  <div>

    <!-- 用户问候卡片 -->
    <div class="card" style="padding: 15px">
      您好，{{ user?.name }}！欢迎使用本系统
    </div>

    <!-- 公告列表布局容器 -->
    <div style="display: flex; margin: 10px 0">

      <!-- 公告列表区域 -->
      <div style="width: 50%;" class="card">

        <!-- 标题 -->
        <div style="margin-bottom: 30px; font-size: 20px; font-weight: bold">公告列表</div>

        <!-- 时间轴组件，用于展示公告列表 -->
        <div>
          <el-timeline reverse slot="reference">

            <!-- 循环遍历所有公告 -->
            <el-timeline-item v-for="item in notices" :key="item.id" :timestamp="item.time">

              <!-- 为每个公告添加弹出层，鼠标悬停时显示详细内容 -->
              <el-popover
                  placement="right"
                  width="200"
                  trigger="hover"
                  :content="item.content">

                <!-- 弹出层触发元素，显示公告标题 -->
                <span slot="reference">{{ item.title }}</span>
              </el-popover>
            </el-timeline-item>
          </el-timeline>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Home', // 组件名称

  data() {
    // 初始化数据：从localStorage中获取用户信息，声明空的公告数组
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      notices: []
    }
  },

  created() {
    // 组件创建时异步获取所有公告数据，并更新到notices数组
    this.$request.get('/notice/selectAll').then(res => {
      this.notices = res.data || []
    })
  }
}
</script>
