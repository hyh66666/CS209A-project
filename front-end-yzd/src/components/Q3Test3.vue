、<template>
  <div>
    <div id="main1" style="height: 600px;"></div>
    <div id="main2" style="height: 400px;"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import axios from 'axios';

export default {
  mounted() {
    this.getDataAndRenderChart1();
    this.getDataAndRenderChart2();
  },
  methods: {
    getDataAndRenderChart1() {
      axios
          .get('http://localhost:8081/Qsan3yi') // 假设后端接口为 /api/data
          .then(response => {
            const data = response.data;
            console.log(response.data)
            // 提取折线图的数据
            const lineData = data.map(item => Number(item.count));
            const categories = data.map(item => item.number);

            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main1'));

            // 绘制图表
            myChart.setOption({
              title: {
                text: '展示哪些 tag 的组合得到最多的 views'
              },
              tooltip: {},
              xAxis: {
                data: categories,
                axisLabel: {
                  fontSize: 6,
                  interval: 0,
                  // rotate: -45
                }
              },
              yAxis: {},
              series: [
                {
                  name: '数据',
                  type: 'line',
                  data: lineData
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
                max: Math.max(...lineData),
                min: Math.min(...lineData),
                inRange: {
                  color: ['#d94e5d', '#eac736', '#50a3ba'].reverse()
                }
              }
            });
          })
          .catch(error => {
            console.error('Failed to fetch data:', error);
          });
    },
    getDataAndRenderChart2() {
      axios
          .get('http://localhost:8081/Qsan3er') // 假设后端接口为 /api/data
          .then(response => {
            const data = response.data;
            console.log(response.data)
            // 提取折线图的数据
            const lineData = data.map(item => Number(item.count));
            const categories = data.map(item => item.number);

            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main2'));

            // 绘制图表
            myChart.setOption({
              title: {
                text: '展示哪些 tags 得到最多的 views'
              },
              tooltip: {},
              xAxis: {
                data: categories,

              },
              yAxis: {},
              series: [
                {
                  name: '数据',
                  type: 'line',
                  data: lineData
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
                max: Math.max(...lineData),
                min: Math.min(...lineData),
                inRange: {
                  color: ['#d94e5d', '#eac736', '#50a3ba'].reverse()
                }
              }
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
