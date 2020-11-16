<template>
  <v-container fluid grid-list-md >
    <v-layout row wrap>
      <v-flex xs10 md6 >
        <v-card>
          <v-card-text class="px2" style="background-color: #F3F9F1">
            <div ref="sale" style="width: 150%;height:350px"></div>
          </v-card-text>
        </v-card>
      </v-flex>

      <v-flex xs10 md6>
        <v-card> <!--width="500"-->
          <v-card-text class="px2" style="background-color: #F3F9F1">
            <div ref="pie" style="width: 100%;height:350px"></div>
          </v-card-text>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  // 引入 ECharts 主模块
  var echarts = require('echarts/lib/echarts');
  require('echarts/lib/chart/bar');
  require('echarts/lib/chart/pie');

  export default {
    name: "dashboard",
    data(){
      return {

      }
    },
    mounted(){
      this.$nextTick(() => {
        var sale = echarts.init(this.$refs.sale);

        // 指定图表的配置项和数据
        var option = {
          title: {
            text: 'Estadística de venta'
          },
          tooltip: {},
          legend: {
            data:['Venta']
          },
          xAxis: {
            data: ["TV","Moda","Móvil","Joyas","Mascotas","Cine"]
          },
          yAxis: {},
          series: [{
            name: 'Venta',
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20]
          }]
        };

        // 使用刚指定的配置项和数据显示图表。
        sale.setOption(option);

        const pie = echarts.init(this.$refs.pie);

        pie.setOption({
          roseType: 'angle',
          title: {
            text: '访问来源'
          },
          series : [
            {
              name: '访问来源',
              type: 'pie',
              radius: '55%',
              data:[
                {value:235, name:'Propaganda'},
                {value:274, name:'UV'},
                {value:310, name:'PV'},
                {value:335, name:'Ventas'},
                {value:400, name:'Buscador'}
              ]
            }
          ],
          itemStyle: {
            emphasis: {
              // 阴影的大小
              shadowBlur: 200,
              // 阴影水平方向上的偏移
              shadowOffsetX: 0,
              // 阴影垂直方向上的偏移
              shadowOffsetY: 0,
              // 阴影颜色
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        })
      })
    }
  }
</script>

<style scoped>

</style>
