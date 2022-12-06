<template>
  <el-row>
    <el-col :span="3"></el-col>
    <el-col :span="18">
      <el-card
        class="box-card"
        id="head"
        shadow="always"
        @click="router.push(`/`)"
      >
        <h1>{{ repository_name }}</h1>
      </el-card>
    </el-col>
    <el-col :span="3"></el-col
  ></el-row>
  <!-- 展示card -->
  <el-row>
    <el-col :span="3"></el-col>
    <el-col :span="18">
      <el-card class="box-card radius" id="main">
        <el-tabs
          v-model="activeName"
          class="demo-tabs"
          @tab-click="handleClick"
        >
          <el-tab-pane label="Developers" name="first">
            <el-row>
              <div
                style="
                  display: inline-block;
                  /* background-color: antiquewhite; */
                  height: 65vh;
                  width: 38%;
                  margin: 10px;
                "
              >
                <!-- 贡献者人数 -->
                <el-row justify="center" style="margin-bottom: 0px">
                  <h2
                    style="
                      font-size: 2.3rem;
                      margin-top: 0.5rem;
                      margin-bottom: 0.5rem;
                    "
                  >
                    Developers Count
                  </h2>
                </el-row>
                <!-- 贡献者人数 展示区域 -->
                <el-row justify="center">
                  <div
                    style="
                      width: 80%;
                      height: 100px;
                      background-color: antiquewhite;
                      opacity: 0.7;
                      display: flex;
                      flex-direction: row;
                      align-items: center;
                      justify-content: center;
                    "
                  >
                    <h1 style="font-size: 4rem; color: brown">
                      {{ json.length }}
                    </h1>
                  </div>
                </el-row>
                <!-- Top 9 贡献者头像 -->
                <el-row justify="center" style="margin-bottom: 0px">
                  <h2
                    style="
                      font-size: 2.3rem;
                      margin-top: 0.5rem;
                      margin-bottom: 0.5rem;
                    "
                  >
                    Top 9 Developers
                  </h2>
                </el-row>
                <!-- Top 9 贡献者头像 展示区域 -->
                <el-row justify="center">
                  <div
                    style="
                      width: 80%;
                      height: 300px;
                      /* background-color: brown; */
                      /* display: flex;
                      flex-direction: row;
                      align-items: center;
                      justify-content: center; */
                    "
                  >
                    <el-row :gutter="20" style="margin: 30px">
                      <el-col :span="8" :offset="0" v-for="i in 9">
                        <el-avatar
                          shape="square"
                          size="large"
                          :src="avatarUrls[i - 1]"
                          style="margin-bottom: 30px"
                        />
                      </el-col>
                    </el-row>
                  </div>
                </el-row>
              </div>
              <div
                style="
                  display: inline-block;
                  /* background-color: antiquewhite; */
                  height: 65vh;
                  width: 58%;
                  margin: 10px;
                "
              >
                <!-- Top 9 图标语 -->
                <el-row justify="center" style="margin-bottom: 0px">
                  <h2
                    style="
                      font-size: 2.3rem;
                      margin-top: 0.5rem;
                      margin-bottom: 0.5rem;
                    "
                  >
                    Top 9 Developers' Contributions
                  </h2>
                </el-row>
                <!-- Top 9 图  -->
                <el-row justify="center">
                  <div id="chart1" style="width: 90%; height: 450px"></div>
                </el-row>
              </div>
            </el-row>
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
    </el-col>
    <el-col :span="3"></el-col>
  </el-row>
</template>

<style lang="css" scoped>
.iii {
  height: 2rem;
  background-color: aqua;
}
#main {
  margin-top: 0;
  display: block;
  width: 100%;
  height: 77vh;
  /* background-color: antiquewhite; */
}
.el-input {
  width: 100%;
}
#main .el-card__body {
  padding: 5px;
  border-radius: 0.6rem;
}
.radius {
  border-radius: 0.6rem;
}
.card__body {
  border-radius: 0.6rem;
}
.dia .el-dialog__body {
  padding: 0;
  padding-right: 2px;
  color: #606266;
  font-size: 14px;
  word-break: break-all;
}
.el_dialog__title {
  font-size: 2rem;
  font-weight: 600;
  color: black;
}
.name {
  margin-left: 0.5rem;
  font-size: 1.5rem;
  font-weight: 600;
  color: black;
  margin-right: 1rem;
}
.ownner {
  font-size: 0.9rem;
  font-weight: 400;
  color: grey;
  margin-right: 1rem;
}
.box-card {
  margin-top: 0.5rem;
  border-radius: 0.6rem;
}
.el-row {
  margin-bottom: 20px;
}
.el-row:last-child {
  margin-bottom: 0;
}
.el-col {
  border-radius: 0.6rem;
  /* background-color: black; */
}
.no-display {
  display: flexbox;
  opacity: 0;
}
#head {
  margin-top: 1.5rem;
  text-align: center;
  font-size: 1.5rem;
  border-radius: 0.6rem;
}
#input {
  width: 50vw;
  margin-top: 0;
  padding-top: 0%;
}
.row {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
h1 {
  margin: 0;
}
#icon {
  padding-top: 2px;
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  /* background-color: black;  */
}
</style>

<script lang="ts" setup>
import { useRoute, useRouter } from "vue-router";
import { reactive, ref, onMounted, h } from "vue";
import axios from "axios";
import json from "../data/developers.json";
import type { TabsPaneContext } from "element-plus";
import * as echarts from "echarts";
import { fa } from "element-plus/es/locale";
const route = useRoute();
const router = useRouter();
let repository_name = router.currentRoute.value.params["repo_name"] as string;
repository_name = repository_name.replace("^", "/");

//
const activeName = ref("first");
const handleClick = (tab: TabsPaneContext, event: Event) => {
  console.log(tab, event);
};

//
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
let avatarUrls = ref<string[]>([]);

//图表
setTimeout(function () {
  type EChartsOption = echarts.EChartsOption;

  var chartDom = document.getElementById("chart1")!;
  var myChart = echarts.init(chartDom);
  var option: EChartsOption;

  const data: number[] = [];
  const names: string[] = [];
  for (let i = 0; i < 9; ++i) {
    // data.push(Math.round(Math.random() * 200));
    let url: any = "";
    if (json[i].avatar_url != undefined) {
      url = json[i].avatar_url;
    } else url = "";
    avatarUrls.value.push(url);
    data.push(json[i].contributions);
    let name: any = "";
    if (json[i].login != undefined) {
      name = json[i].login;
    } else name = json[i].name;
    names.push(name);
  }

  option = {
    grid: {
      left: "20", // 固定左边刻度宽度
      right: "10%",
      bottom: "10%",
      containLabel: true,
    },
    xAxis: {
      max: "dataMax",
    },
    yAxis: {
      type: "category",
      data: names,
      inverse: true,
      animationDuration: 300,
      animationDurationUpdate: 300,
      max: 8, // only the largest 3 bars will be displayed
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
      show: false,
    },
    animationDuration: 0,
    animationDurationUpdate: 3000,
    animationEasing: "linear",
    animationEasingUpdate: "linear",
  };

  function run() {
    for (var i = 0; i < data.length; ++i) {
      // if (Math.random() > 0.9) {
      //   data[i] += Math.round(Math.random() * 2000);
      // } else {
      //   data[i] += Math.round(Math.random() * 200);
      // }
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
}, 1);
</script>
