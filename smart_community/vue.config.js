// 导入@vue/cli-service中的defineConfig函数，用于创建项目配置对象
const {defineConfig} = require('@vue/cli-service')

// 定义并导出Vue CLI项目配置
module.exports = defineConfig({
  // 配置转译依赖项。当依赖项包含不兼容ES6+语法的代码时，将其转译为浏览器可理解的代码
  transpileDependencies: true,

  // 配置开发服务器（devServer）
  devServer: {
    host: '0.0.0.0',
    // 设置开发服务器端口为8080
    port: 8080
  },

  // 配置chainWebpack钩子，用于对webpack配置进行细粒度调整
  chainWebpack: config => {
    // 获取HTMLWebpackPlugin插件实例
    config.plugin('html')

    // 调整插件配置
    .tap(args => {
      // 修改HTML模板标题为"智慧社区系统"
      args[0].title = "智慧社区系统";

      // 返回更新后的配置数组
      return args;
    })
  }
})
