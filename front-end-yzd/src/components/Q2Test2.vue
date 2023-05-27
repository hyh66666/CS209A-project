<template>
  <div>
    <div id="main" style="height: 400px;"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import axios from 'axios';

export default {
  data(){
    return{
      duration_value:''
    }
  },
  mounted() {
    this.getdu();
  },
  methods: {
    getDataAndRenderChart() {
      axios.get('http://localhost:8081/answerAccepted2') // 假设后端接口为 /api/data
          .then(response => {
            const data = response.data;
            console.log(response.data)
            // 提取柱状图的数据
            const barData = Object.values(data);
            const categories = Object.keys(data);

            // 获取最大值和最小值
            const max = Math.max(...barData);
            const min = Math.min(...barData);

            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            // 绘制图表
            myChart.setOption({
              title: {
                text: '展示问题从提出到解决的时间间隔分布,时间间隔分布为'+this.duration_value +'min'
              },
              tooltip: {},
              xAxis: {
                data: categories,
                axisLabel: {
                  interval: 0
                }
              },
              yAxis: {},
              series: [
                {
                  name: '数据',
                  type: 'bar',
                  data: barData
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
                max,
                min,
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
    getdu () {
      axios.get('http://localhost:8081/answerAccepted2ok', {
      }).then(res => {
        this.duration_value = res.data
        console.log(this.duration_value)
        this.getDataAndRenderChart()
      })
    }

  }
};
</script>

<style>
/* 可以添加一些样式 */
</style>
