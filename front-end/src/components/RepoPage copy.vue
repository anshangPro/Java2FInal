<style scoped>
.el-dialog__header {
  opacity: 0;
}
</style>
<template>
  <el-card class="box-card radius" id="main">
    <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
      <el-tab-pane label="Developers" name="first">
        <Developers />
      </el-tab-pane>
      <el-tab-pane label="Issues" name="second"
        ><div
          style="
            height: 72vh;
            width: 100%;
            background-color: black;
            display: block;
          "
        ></div>
        ></el-tab-pane
      >
      <el-tab-pane label="Releases and Commits" name="third">
        <div
          style="
            height: 72vh;
            width: 100%;
            background-color: black;
            display: block;
          "
        ></div>
        ></el-tab-pane
      >
    </el-tabs>
  </el-card>
</template>

<script lang="ts" setup>
import { reactive, ref, onMounted, h } from "vue";
import axios from "axios";
import type { TabsPaneContext } from "element-plus";

import * as echarts from "echarts";

import json from "../data/developers.json";
import { SelectProps } from "element-plus/es/components/select-v2/src/defaults";
const Timer = ref("");
let now = ref<string>("");
let date = ref<string>("");
let time = ref<string>("");
let year = ref<string>("");
let month = ref<string>("");
let day = ref<string>("");
onMounted(() => {
  setInterval(() => {
    now.value = new Date().toLocaleString();
    date.value = now.value.split(" ")[0];
    time.value = now.value.split(" ")[1];
    year.value = date.value.split("/")[0];
    month.value = date.value.split("/")[1];
    day.value = date.value.split("/")[2];
    Timer.value = `${year.value}年${month.value}月${day.value}日  北京时间${time.value}`;
  }, 500);
});

let Developers_cnt = ref(0);
Developers_cnt.value = json.length;
console.log(json);
let avatarUrls = ref<string[]>([]);
avatarUrls.value.push("https://avatars.githubusercontent.com/u/490484?v=4");
avatarUrls.value.push("https://avatars.githubusercontent.com/u/84830979?v=4");
avatarUrls.value.push("https://avatars.githubusercontent.com/u/490484?v=4");
avatarUrls.value.push("https://avatars.githubusercontent.com/u/84830979?v=4");
avatarUrls.value.push("https://avatars.githubusercontent.com/u/490484?v=4");
avatarUrls.value.push("https://avatars.githubusercontent.com/u/84830979?v=4");
avatarUrls.value.push("https://avatars.githubusercontent.com/u/490484?v=4");
avatarUrls.value.push("https://avatars.githubusercontent.com/u/84830979?v=4");
avatarUrls.value.push("https://avatars.githubusercontent.com/u/490484?v=4");
avatarUrls.value.push("https://avatars.githubusercontent.com/u/84830979?v=4");

//
const activeName = ref("first");
const handleClick = (tab: TabsPaneContext, event: Event) => {
  console.log(tab, event);
};

//echarts
type EChartsOption = echarts.EChartsOption;
var chartDom = document.getElementById("chart")!;
console.log(document.getElementById("chart"));
var myChart = echarts.init(chartDom);
var option: EChartsOption;

const data: number[] = [];
for (let i = 0; i < 5; ++i) {
  data.push(Math.round(Math.random() * 200));
}

option = {
  xAxis: {
    max: "dataMax",
  },
  yAxis: {
    type: "category",
    data: ["A", "B", "C", "D", "E"],
    inverse: true,
    animationDuration: 300,
    animationDurationUpdate: 300,
    max: 2, // only the largest 3 bars will be displayed
  },
  series: [
    {
      realtimeSort: true,
      name: "X",
      type: "bar",
      data: data,
      label: {
        show: true,
        position: "right",
        valueAnimation: true,
      },
    },
  ],
  legend: {
    show: true,
  },
  animationDuration: 0,
  animationDurationUpdate: 3000,
  animationEasing: "linear",
  animationEasingUpdate: "linear",
};

function run() {
  for (var i = 0; i < data.length; ++i) {
    if (Math.random() > 0.9) {
      data[i] += Math.round(Math.random() * 2000);
    } else {
      data[i] += Math.round(Math.random() * 200);
    }
  }
  myChart.setOption<echarts.EChartsOption>({
    series: [
      {
        type: "bar",
        data,
      },
    ],
  });
}

setTimeout(function () {
  run();
}, 0);
setInterval(function () {
  run();
}, 3000);

option && myChart.setOption(option);
</script>

<script lang="ts"></script>
