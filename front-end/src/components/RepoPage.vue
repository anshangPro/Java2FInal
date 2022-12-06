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
                      {{ dev_cnt }}
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
                      <el-col
                        :span="8"
                        :offset="0"
                        v-for="i in dev_names.length"
                      >
                        <el-avatar
                          shape="square"
                          size="large"
                          :src="avatarUrls[i - 1]"
                          style="margin-bottom: 30px"
                          @click="go(dev_home[i - 1])"
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
                  <div id="chart1" style="width: 95%; height: 520px"></div>
                </el-row>
              </div>
            </el-row>
          </el-tab-pane>
          <el-tab-pane label="Issues Overview" name="second"
            ><el-row>
              <!-- 饼图 -->
              <div
                style="
                  display: inline-block;
                  /* background-color: antiquewhite; */
                  height: 65vh;
                  width: 38%;
                  margin: 10px;
                "
              >
                <!-- issue 数目 -->
                <el-row justify="center" style="margin-bottom: 0px">
                  <h2
                    style="
                      font-size: 2.3rem;
                      margin-top: 0.5rem;
                      margin-bottom: 0.5rem;
                    "
                  >
                    Issue Count
                  </h2>
                </el-row>
                <!-- issue count 展示区域 -->
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
                      {{ issue_cnt }}
                    </h1>
                  </div>
                </el-row>
                <!-- issue 数目 展示区域 -->
                <el-row justify="center">
                  <div
                    style="
                      width: 90%;
                      height: 40vh;
                      /* background-color: antiquewhite;   */
                      opacity: 1;
                      display: flex;
                      flex-direction: row;
                      align-items: center;
                      justify-content: center;
                    "
                  >
                    <div
                      id="chart2"
                      style="height: 350px; width: 350px; background-color: "
                    ></div>
                  </div>
                </el-row>
              </div>
              <!-- 柱状图 -->
              <div
                style="
                  display: inline-block;
                  /* background-color: antiquewhite; */
                  height: 65vh;
                  width: 58%;
                  margin: 10px;
                "
              >
                <!-- 典型解决事件 -->
                <el-row justify="center" style="margin-bottom: 0px">
                  <h2
                    style="
                      font-size: 2.3rem;
                      margin-top: 0.5rem;
                      margin-bottom: 0.5rem;
                    "
                  >
                    Typical Resolution Time
                  </h2>
                </el-row>
                <!-- 典型解决时间 柱状图  -->
                <el-row justify="center">
                  <div id="chart3" style="width: 500px; height: 500px"></div>
                </el-row>
              </div> </el-row
          ></el-tab-pane>
          <el-tab-pane label="Issues Page" name="third"
            ><el-row justify="center">
              <!-- open issue -->
              <div
                style="
                  display: inline-block;
                  background-color: black;
                  height: 65vh;
                  width: 45%;
                  margin: 10px;
                "
              >
                <!-- issue 详情 -->
                <el-row justify="center" style="margin-bottom: 0px">
                  <h2
                    style="
                      font-size: 2.3rem;
                      margin-top: 0.5rem;
                      margin-bottom: 0.5rem;
                    "
                  >
                    Opening Issues
                  </h2>
                </el-row>
                <!-- issue详情展示 -->
                <el-row justify="center">
                  <div id="chart3" style="width: 500px; height: 500px"></div>
                </el-row>
              </div>
              <!-- close issue -->
              <div
                style="
                  display: inline-block;
                  background-color: black;
                  height: 65vh;
                  width: 45%;
                  margin: 10px;
                "
              >
                <!-- issue 详情 -->
                <el-row justify="center" style="margin-bottom: 0px">
                  <h2
                    style="
                      font-size: 2.3rem;
                      margin-top: 0.5rem;
                      margin-bottom: 0.5rem;
                    "
                  >
                    Closed Issues
                  </h2>
                </el-row>
                <!-- issue详情展示 -->
                <el-row justify="center">
                  <div id="chart3" style="width: 500px; height: 500px"></div>
                </el-row>
              </div> </el-row
          ></el-tab-pane>
          <el-tab-pane label="Releases and Commits" name="fourth">
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
import { reactive, ref } from "vue";
import axios from "axios";
import type { TabsPaneContext } from "element-plus";
import * as echarts from "echarts";

const route = useRoute();
const router = useRouter();
let repository_name = router.currentRoute.value.params["repo_name"] as string;
let name = repository_name.split("^")[1];
repository_name = repository_name.replace("^", "/");
let developers: repo_developer[] = reactive<repo_developer[]>([]);
let dev_names = ref<string[]>([]);
let dev_commits = ref<number[]>([]);
let avatarUrls = ref<string[]>([]);
let dev_home = ref<string[]>([]);
let dev_cnt = ref(0);
let issue_cnt = ref(0);
let open_cnt = ref(0);
let close_cnt = ref(0);

interface repo_developer {
  id: number;
  name: string;
  avatar: string;
  home: string;
  commit: number;
}

//
const activeName = ref("first");
const handleClick = (tab: TabsPaneContext, event: Event) => {
  // console.log(tab, event);
};

//
axios.get(`https://final.anshang.live/api/data/repo/${name}`).then((res) => {
  dev_cnt.value = res.data.developers;
  issue_cnt = res.data.issues;
  // console.log(res.data);
});
//请求
axios
  .get(`https://final.anshang.live/api/data/${name}/developer`)
  .then((res) => {
    for (var i in res.data) {
      let a = res.data[i];
      developers.push({
        id: a.id,
        name: a.name,
        avatar: a.avatarUrl,
        home: a.url,
        commit: a.commits,
      });
      dev_names.value.push(a.name);
      avatarUrls.value.push(a.avatarUrl);
      dev_commits.value.push(a.commits);
      dev_home.value.push(a.url);
    }

    //图表
    setTimeout(function () {
      type EChartsOption = echarts.EChartsOption;

      var chartDom1 = document.getElementById("chart1")!;
      var myChart1 = echarts.init(chartDom1);
      var option1: EChartsOption;

      option1 = {
        grid: {
          left: "20", // 固定左边刻度宽度
          right: "20",
          bottom: "10%",
          top: "5",
          containLabel: true,
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow",
          },
        },
        xAxis: {
          max: "dataMax",
        },
        yAxis: {
          inverse: true,
          type: "category",
          data: dev_names.value,
        },
        series: [
          {
            realtimeSort: true,
            name: "total commits",
            type: "bar",
            data: dev_commits.value,
          },
        ],
      };

      option1 && myChart1.setOption(option1);

      //饼图issue数量
      var chartDom2 = document.getElementById("chart2")!;
      var myChart2 = echarts.init(chartDom2);
      var option2: EChartsOption;

      option2 = {
        tooltip: {
          trigger: "item",
        },
        legend: {
          top: "5%",
          left: "center",
        },
        series: [
          {
            name: "Issue count",
            type: "pie",
            radius: ["37%", "70%"],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: "#fff",
              borderWidth: 2,
            },
            label: {
              show: false,
              position: "center",
            },
            emphasis: {
              label: {
                show: true,
                fontSize: "40",
                fontWeight: "bold",
              },
            },
            labelLine: {
              show: false,
            },
            data: [
              { value: 20, name: "Opening Issue" },
              { value: 35, name: "Closed Issue" },
            ],
            center: ["50%", "50%"],
          },
        ],
      };

      option2 && myChart2.setOption(option2);

      //柱状图展示各种时间
      var chartDom3 = document.getElementById("chart3")!;
      var myChart3 = echarts.init(chartDom3);
      var option3: EChartsOption;

      option3 = {
        grid: {
          top: "10px",
          left: "10px",
          right: "10px",
          bottom: "10%",
          containLabel: true,
        },
        xAxis: {
          type: "category",
          data: ["Average", "Extreme", "Difference", "Variance"],
        },
        yAxis: {
          type: "value",
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow",
          },
        },
        series: [
          {
            showBackground: true,
            backgroundStyle: {
              color: "rgba(180, 180, 180, 0.2)",
            },
            data: [
              {
                value: 100,
                itemStyle: {
                  color: "#95d475",
                },
              },
              {
                value: 150,
                itemStyle: {
                  color: " #fab6b6",
                },
              },
              {
                value: 130,
                itemStyle: {
                  color: " #f3d19e",
                },
              },
              {
                value: 110,
                itemStyle: {
                  color: " #b1b3b8",
                },
              },
            ],
            type: "bar",
          },
        ],
      };

      option3 && myChart3.setOption(option3);
    }, 1);
  });

const go = (url: string) => {
  axios.get(url).then((res) => {
    window.location.href = res.data.html_url;
  });
};
</script>
