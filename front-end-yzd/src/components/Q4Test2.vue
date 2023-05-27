<template>
  <div>
    <div id="main1" style="height: 400px;"></div>
    <div id="main2" style="height: 400px;"></div>
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
      axios.get('http://localhost:8081/Qsi2yi')
          .then(response => {
            const data = response.data;

            // 提取第一个柱状图的数据
            const barData1 = data.map(item => item.count);
            const categories1 = data.map(item => item.number);

            // 获取第一个柱状图的最大值和最小值
            const max1 = Math.max(...barData1);
            const min1 = Math.min(...barData1);

            // 基于准备好的dom，初始化第一个echarts实例
            var myChart1 = echarts.init(document.getElementById('main1'));

            // 绘制第一个柱状图
            myChart1.setOption({
              title: {
                text: '从问题回答者角度进行统计（图1）'
              },
              tooltip: {},
              xAxis: {
                data: categories1
              },
              yAxis: {},
              series: [
                {
                  name: '数据',
                  type: 'bar',
                  data: barData1
                }
              ],
              dataZoom: [
                {
                  type: 'inside',
                  start: 0,
                  end: 100
                },
                {
                  start: 0,
                  end: 100,
                  handleIcon:
                      'M10.7,11.6V2.1c0-0.4-0.3-0.7-0.7-0.7H9.1c-0.4,0-0.7,0.3-0.7,0.7v9.5c0,0.4,0.3,0.7,0.7,0.7h0.9C10.4,12.3,10.7,12,10.7,11.6z M7.8,12.3V1.4C7.8,1,7.5,0.7,7.1,0.7H6.2C5.8,0.7,5.5,1,5.5,1.4v10.9c0,0.4,0.3,0.7,0.7,0.7h0.9C7.5,13,7.8,12.7,7.8,12.3z',
                  handleSize: '80%',
                  handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                  }
                }
              ],
              visualMap: {
                type: 'continuous',
                dimension: 1,
                max: max1,
                min: min1,
                inRange: {
                  color: ['#d94e5d', '#eac736', '#50a3ba'].reverse()
                }
              }
            });

          });

      axios.get('http://localhost:8081/Qsi2er')
          .then(response => {
            const data = response.data;
            // 提取第二个柱状图的数据
            const barData2 = data.map(item => item.count); // 假设第二个柱状图的数据为原数据的两倍
            const categories2 = data.map(item => item.number);

            // 获取第二个柱状图的最大值和最小值
            const max2 = Math.max(...barData2);
            const min2 = Math.min(...barData2);

            // 基于准备好的dom，初始化第二个echarts实例
            var myChart2 = echarts.init(document.getElementById('main2'));

            // 绘制第二个柱状图
            myChart2.setOption({
              title: {
                text: '从问题评论者角度进行统计（图2）'
              },
              tooltip: {},
              xAxis: {
                data: categories2
              },
              yAxis: {},
              series: [
                {
                  name: '数据',
                  type: 'bar',
                  data: barData2
                }
              ],
              dataZoom: [
                {
                  type: 'inside',
                  start: 0,
                  end: 100
                },
                {
                  start: 0,
                  end: 100,
                  handleIcon:
                      'M10.7,11.6V2.1c0-0.4-0.3-0.7-0.7-0.7H9.1c-0.4,0-0.7,0.3-0.7,0.7v9.5c0,0.4,0.3,0.7,0.7,0.7h0.9C10.4,12.3,10.7,12,10.7,11.6z M7.8,12.3V1.4C7.8,1,7.5,0.7,7.1,0.7H6.2C5.8,0.7,5.5,1,5.5,1.4v10.9c0,0.4,0.3,0.7,0.7,0.7h0.9C7.5,13,7.8,12.7,7.8,12.3z',
                  handleSize: '80%',
                  handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                  }
                }
              ],
              visualMap: {
                type: 'continuous',
                dimension: 1,
                max: max2,
                min: min2,
                inRange: {
                  color: ['#d94e5d', '#eac736', '#50a3ba'].reverse()
                }
              }
            });
          });
    }
  }
};
</script>

<style>
/* 可以添加一些样式 */
</style>
