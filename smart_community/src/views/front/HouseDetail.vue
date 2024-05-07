<template>
  <div style="width: 60%; margin: 10px auto">
    <div style="display: flex; grid-gap: 20px">
      <div style="flex: 1">
        <img :src="nurseHouse.img" alt="" style="width: 100%">
      </div>
      <div style="flex: 1">

        <div style=" margin: 20px 0; display: flex; align-items: center">
          <div style="font-size: 22px; font-weight: bold; flex: 1">{{ nurseHouse.name }}</div>
          <div style="color: #666; font-size: 13px">{{ nurseHouse.count }}浏览</div>
        </div>

        <div style="margin: 10px 0">
          <el-tag style="margin-right: 10px" v-for="item in JSON.parse(nurseHouse.tags || '[]')" :key="item">{{ item }}</el-tag>
        </div>
        <div style="margin: 10px 0; color: #ffa400; font-size: 18px; font-weight: bold">￥{{ nurseHouse.price }}</div>
        <div style="margin: 20px 0; color: #666">{{ nurseHouse.address }}</div>

        <div style="display: flex; margin: 10px 0;">
          <div style="width: 200px"><span style="color: #666">机构性质：</span>{{ nurseHouse.nature }}</div>
          <div style="flex: 1"><span style="color: #666">机构类型：</span>{{ nurseHouse.type }}</div>
        </div>
        <div style="display: flex; margin: 10px 0;">
          <div style="width: 200px"><span style="color: #666">成立时间：</span>{{ nurseHouse.date }}</div>
          <div style="flex: 1"><span style="color: #666">床位数量：</span>{{ nurseHouse.beds }}</div>
        </div>
        <div style="display: flex; margin: 10px 0;">
          <div style="width: 200px"><span style="color: #666">机构面积：</span>{{ nurseHouse.area }}</div>
        </div>

        <div style="margin: 20px 0">
          电话：<span style="font-size: 20px; font-weight: bold; color: orangered">{{ nurseHouse.phone }}</span>
        </div>


      </div>
    </div>

    <div style="margin: 30px 0">
      <div style="font-weight: bold; font-size: 22px; text-align: center; margin-bottom: 30px">详细信息</div>

      <div style="margin: 20px 0">
        <div id="container" style="height: 400px; margin-bottom: 30px"></div>
      </div>

      <div style="line-height: 26px" class="w-e-text">
        <div v-html="nurseHouse.content"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "HouseDetail",
  data() {
    return {
      id: this.$route.query.id,
      nurseHouse: {},
    }
  },
  created() {
    this.$request.put('/nurseHouse/updateCount/' + this.id).then(res => {
      this.load()
    })
  },
  methods: {
    load() {
      this.$request.get('/nurseHouse/selectById/' + this.id).then(res => {
        this.nurseHouse = res.data || {}

        const map = new BMapGL.Map('container'); // 创建Map实例
        map.centerAndZoom(new BMapGL.Point(this.nurseHouse.longitude, this.nurseHouse.latitude),
            16); // 初始化地图,设置中心点坐标和地图级别
        map.enableScrollWheelZoom(true); // 开启鼠标滚轮缩放
        let point = new BMapGL.Point(this.nurseHouse.longitude, this.nurseHouse.latitude)
        let marker = new BMapGL.Marker(point);  // 创建标注
        map.addOverlay(marker);
        let opts = {
          width: 300,     // 信息窗口宽度
          height: 100,     // 信息窗口高度
          title: this.nurseHouse.name, // 信息窗口标题
        }
        let infoWindow = new BMapGL.InfoWindow(
            "<b>地址</b>：" + this.nurseHouse.address + "<br/>",
            opts);  // 创建信息窗口对象

        marker.addEventListener("click", function () {
          map.openInfoWindow(infoWindow, point); //开启信息窗口
        });
      })
    }
  }
}
</script>

<style scoped>

</style>