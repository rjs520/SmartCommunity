模板
<template>
  <!-- 页面主体容器 -->
  <div>

    <!-- 用户问候卡片 -->
    <div class="card" style="padding: 15px">
      您好，{{ user?.name }}！欢迎使用本系统
    </div>
    <div style="margin: 10px 0;display: flex;grid-gap: 10px">
      <div style="flex: 1;" class="card">
        <div id="bar" style="height: 400px"></div>
      </div>
      <div style="flex: 1" class="card">
        <div id="pie" style="height: 400px"></div>
      </div>
    </div>

    <!-- 公告列表布局容器 -->
    <div style="display: flex; margin: 10px 0">

      <!-- 公告列表区域 -->
      <div class="card" style="width: 50%;">

        <!-- 标题 -->
        <div style="margin-bottom: 30px; font-size: 20px; font-weight: bold">公告列表</div>

        <!-- 时间轴组件，用于展示公告列表 -->
        <div>
          <el-timeline slot="reference" reverse>

            <!-- 循环遍历所有公告 -->
            <el-timeline-item v-for="item in notices" :key="item.id" :timestamp="item.time">

              <!-- 为每个公告添加弹出层，鼠标悬停时显示详细内容 -->
              <el-popover
                  :content="item.content"
                  placement="right"
                  trigger="hover"
                  width="200">

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
import * as echarts from 'echarts'

// 定义柱状图选项
const barOption = {
  title: {
    text: '各类活动报名数量统计',
    left: 'center'
  },
  tooltip: {
    trigger: 'axis'
  },
  legend: {
    left: 'left'
  },
  xAxis: {
    type: 'category',
    data: [],
    axisLabel: {
      show: true, // 是否显示刻度标签
      interval: 0, // 刻度标签显示间隔
      rotate: -60, // 刻度标签旋转角度
      inside: false, // 刻度标签是否朝内
      margin: 6, // 刻度标签与轴线之间的距离
    },
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      data: [],
      type: 'bar',
      itemStyle: {
        normal: {
          color: function (params) { // 根据索引返回对应的颜色
            let colorList = ['#ffaa2e', '#32C5E9', '#fa4c4c', '#08b448', '#FFDB5C', '#ff9f7f',
              '#fb7293', '#E062AE', '#E690D1', '#e7bcf3']
            return colorList[params.dataIndex];
          }
        }
      },
    }
  ],
}
// 定义饼图选项
const pieOption = {
  title: {
    text: '各类服务预约统计',
    subtext: '比例图',
    left: 'center'
  },
  tooltip: {
    trigger: 'item'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      type: 'pie',
      center: ['50%', '60%'],
      radius: '50%',
      data: [],
      label: {
        show: true,
        formatter(param) {
          return param.name + ' (' + param.percent + '%)';
        }
      },
      emphasis: {
        itemStyle: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      }
    }
  ]
}
export default {
  name: 'Home', // 组件名称

  data() {
    // 初始化数据
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'), // 从localStorage获取用户信息
      notices: [] // 声明空的公告数组
    }
  },

  created() {
    // 创建时获取所有公告数据
    this.$request.get('/notice/selectAll').then(res => {
      this.notices = res.data || []
    })
  },
  mounted() {
    // 柱状图初始化及数据获取
    let barDom = document.getElementById('bar');
    let barChart = echarts.init(barDom);

    this.$request.get('/activitySign/selectCount').then(res => {
      barOption.xAxis.data = res.data?.map(v => v.name) // 设置x轴数据
      barOption.series[0].data = res.data?.map(v => v.value) // 设置系列数据
      barChart.setOption(barOption) // 应用选项
    })
    // 饼图初始化及数据获取
    let pieDom = document.getElementById('pie');
    let pieChart = echarts.init(pieDom);

    this.$request.get('/reserve/selectCount').then(res => {
      pieOption.series[0].data = res.data || [] // 设置系列数据
      pieChart.setOption(pieOption) // 应用选项
    })
  }
}
</script>

