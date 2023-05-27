<template>
  <div>
    <div id="main" style="height: 400px;"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import axios from 'axios';

export default {
  mounted() {
    this.getDataAndRenderChart();
  },
  methods: {
    getDataAndRenderChart() {
      axios.get('http://localhost:8081/answerAccepted1') // 假设后端接口为 /api/data
          .then(response => {
            const data = response.data;

            // 提取饼状图的数据
            const pieData = Object.entries(data).map(([name, value]) => ({
              name,
              value
            }));

            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            // 绘制图表
            myChart.setOption({
              title: {
                text: '展示有 accepted answer 的问题的百分比'
              },
              tooltip: {},
              series: [
                {
                  name: '销量',
                  type: 'pie',
                  radius: '50%',
                  data: pieData // 使用从后端获取的饼状图数据
                }
              ]
            });
          })
          .catch(error => {
            console.error('Failed to fetch data:', error);
          });
    }
  }
};
</script>

<style>
/* 可以添加一些样式 */
</style>
