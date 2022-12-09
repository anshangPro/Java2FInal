<template>
  <el-row>
    <el-col :span="2"></el-col>
    <el-col :span="20">
      <el-card
        class="box-card"
        id="head"
        shadow="always"
        @click="router.push(`/`)"
      >
        <h1>{{ repository_name }}</h1>
      </el-card>
    </el-col>
    <el-col :span="2"></el-col
  ></el-row>
  <!-- 展示card -->
  <el-row>
    <el-col :span="2"></el-col>
    <el-col :span="20">
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
                      width: 75%;
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
                  width: 27%;
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
                    Issues Count{{}}
                  </h2>
                </el-row>
                <!-- issue count 展示区域 -->
                <el-row justify="center">
                  <div
                    style="
                      width: 75%;
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
                  width: 44%;
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
                    Resolution Time
                  </h2>
                </el-row>
                <!-- 典型解决时间 散点图  -->
                <el-row justify="center">
                  <div id="chart3" style="width: 500px; height: 500px"></div>
                </el-row>
              </div>
              <!-- 表格 -->
              <div
                style="
                  display: inline-block;
                  /* background-color: antiquewhite; */
                  height: 65vh;
                  width: 24%;
                  margin: 10px;
                "
              >
                <!-- Typical Time -->
                <el-row justify="center" style="margin-bottom: 0px">
                  <h2
                    style="
                      font-size: 2.3rem;
                      margin-top: 0.5rem;
                      margin-bottom: 0.5rem;
                    "
                  >
                    Typical Time
                  </h2>
                </el-row>
                <!-- 典型解决时间 柱状图  -->
                <el-row justify="center">
                  <el-table
                    :data="issue_time_infos"
                    border
                    style="width: 100%; margin-top: 5px"
                    :height="455"
                    :row-style="{ height: '81px' }"
                  >
                    <el-table-column
                      prop="type"
                      label="Type"
                      width="100"
                      align="center"
                    />
                    <el-table-column
                      prop="value"
                      label="Value in days"
                      align="center"
                    />
                  </el-table>
                </el-row>
              </div> </el-row
          ></el-tab-pane>
          <el-tab-pane label="Issues Page" name="third"
            ><el-row justify="center">
              <!-- open issue -->
              <div
                style="
                  display: inline-block;
                  /* background-color: antiquewhite; */
                  height: 65vh;
                  width: 44%;
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
                    Opening Issues:{{ open_cnt }}
                  </h2>
                </el-row>
                <!-- issue详情展示 -->
                <el-row justify="center">
                  <div
                    id=""
                    style="width: 100%; height: 500px; background-color: #fff"
                  >
                    <el-scrollbar>
                      <el-card
                        v-for="i in open_issues"
                        :key="i"
                        style="border-radius: 15px; margin-bottom: 10px"
                        shadow="hover"
                      >
                        <h4>
                          <span>#{{ i.id }}</span>
                          <span> Start at {{ i.createAt.split("T")[0] }}</span>
                        </h4>
                        <h3>
                          <span class="open">{{ i.title }}</span>
                        </h3>
                      </el-card>
                    </el-scrollbar>
                  </div>
                </el-row>
              </div>
              <!-- close issue -->
              <div
                style="
                  display: inline-block;
                  /* background-color: antiquewhite; */
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
                    Closed Issues: {{ close_cnt }}
                  </h2>
                </el-row>
                <!-- issue详情展示 -->
                <el-row justify="center">
                  <div
                    id=""
                    style="width: 100%; height: 500px; background-color: #fff"
                  >
                    <el-scrollbar>
                      <el-card
                        v-for="i in close_issues"
                        :key="i"
                        style="border-radius: 15px; margin-bottom: 10px"
                        shadow="hover"
                      >
                        <h4>
                          <span>#{{ i.id }}</span>
                          <span> . {{ i.createAt.split("T")[0] }}</span>
                          <span> to {{ i.closeAt.split("T")[0] }}</span>
                        </h4>
                        <h3>
                          <span class="close">{{ i.title }}</span>
                        </h3>
                      </el-card>
                    </el-scrollbar>
                  </div>
                </el-row>
              </div>
            </el-row></el-tab-pane
          >
          <el-tab-pane label="Releases and Commits" name="fourth">
            <el-row>
              <!-- 左边 -->
              <div
                style="
                  display: inline-block;
                  /* background-color: antiquewhite; */
                  height: 65vh;
                  width: 30%;
                  margin: 10px;
                "
              >
                <!-- commit数 -->
                <el-row justify="center" style="margin-bottom: 0px">
                  <h2
                    style="
                      font-size: 2.3rem;
                      margin-top: 0.5rem;
                      margin-bottom: 0.5rem;
                    "
                  >
                    Commits Count
                  </h2>
                </el-row>
                <!-- commit数 展示区域 -->
                <el-row justify="center">
                  <div
                    style="
                      width: 75%;
                      height: 150px;
                      background-color: antiquewhite;
                      opacity: 0.7;
                      display: flex;
                      flex-direction: row;
                      align-items: center;
                      justify-content: center;
                    "
                  >
                    <h1 style="font-size: 4rem; color: brown">
                      {{ commits_cnt }}
                    </h1>
                  </div>
                </el-row>

                <!-- release数 -->
                <el-row justify="center" style="margin-bottom: 0px">
                  <h2
                    style="
                      font-size: 2.3rem;
                      margin-top: 0.5rem;
                      margin-bottom: 0.5rem;
                    "
                  >
                    Releases Count
                  </h2>
                </el-row>
                <!-- release数 展示区域 -->
                <el-row justify="center">
                  <div
                    style="
                      width: 75%;
                      height: 150px;
                      background-color: antiquewhite;
                      opacity: 0.7;
                      display: flex;
                      flex-direction: row;
                      align-items: center;
                      justify-content: center;
                    "
                  >
                    <h1 style="font-size: 4rem; color: brown">
                      {{ releases_cnt }}
                    </h1>
                  </div>
                </el-row>
              </div>
              <!-- 右边 -->
              <div
                style="
                  display: inline-block;
                  /* background-color: antiquewhite; */
                  height: 65vh;
                  width: 65%;
                  margin: 10px;
                "
              >
                <!-- commit分布 -->
                <el-row justify="center" style="margin-bottom: 0px">
                  <h2
                    style="
                      font-size: 2rem;
                      margin-top: 0.5rem;
                      margin-bottom: 0.5rem;
                    "
                  >
                    Commits Distribution
                  </h2>
                </el-row>
                <!-- Commits 分布图  -->
                <el-row
                  justify="center"
                  style="margin-bottom: 0px; margin-top: 0px"
                  ><el-button v-for="i in Object.keys(year_date_cnt)">
                    <div @click="chooseYear(i)">{{ i }}</div>
                  </el-button></el-row
                >
                <el-row justify="center">
                  <div
                    id="chart4"
                    style="
                      width: 95%;
                      height: 170px;
                      /* background-color: antiquewhite; */
                    "
                  ></div>
                </el-row>
                <!--  -->
                <!-- Commits Between Releases图 -->
                <el-row justify="center" style="margin-bottom: 0px">
                  <h2
                    style="
                      font-size: 2rem;
                      margin-top: 0.5rem;
                      margin-bottom: 0.5rem;
                    "
                  >
                    Commits Between Releases
                  </h2>
                </el-row>
                <!-- Commits Between Releases图  -->
                <el-row justify="center">
                  <div
                    id="chart5"
                    style="
                      width: 95%;
                      height: 200px;
                      /* background-color: antiquewhite; */
                    "
                  ></div>
                </el-row>
              </div>
            </el-row>
          </el-tab-pane>
        </el-tabs>
      </el-card>
    </el-col>
    <el-col :span="2"></el-col>
  </el-row>
</template>

<style lang="css" scoped>
h4 {
  color: grey;
  font-weight: 500;
  font-size: 1rem;
  margin: 0%;
}
.open {
  color: #95d475;
}
.close {
  color: #73767a;
}
h3 {
  font-weight: 600;
  font-size: 1.3rem;
  margin: 0.3rem;
}
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
import { da, el, en, pa, tr } from "element-plus/es/locale";

const route = useRoute();
const router = useRouter();
let repository_name = router.currentRoute.value.params["repo_name"] as string;
let name = repository_name.split("^")[1];
repository_name = repository_name.replace("^", "/");
let developers: repo_developer[] = reactive<repo_developer[]>([]);
let issue_time_infos: any[] = reactive<any[]>([]);
let issue_start_end: any[] = reactive<any[]>([]);
let issue_list: any[] = reactive<any[]>([]);
let dev_names = ref<string[]>([]);
let dev_commits = ref<number[]>([]);
let open_issues = reactive<any[]>([]);
let close_issues = reactive<any[]>([]);

let avatarUrls = ref<string[]>([]);
let dev_home = ref<string[]>([]);
let dev_cnt = ref(0);
let issue_cnt = ref(0);
let open_cnt = ref(0);
let close_cnt = ref(0);
let commits_cnt = ref(0);
let releases_cnt = ref(0);

let choose = ref("2022");

interface repo_developer {
  id: number;
  name: string;
  avatar: string;
  home: string;
  commit: number;
}

//

const renderPage = (name: any) => {
  if (name == "first") setFirst();
  else if (name == "second") setSecond();
  else if (name == "third") setThird();
  else if (name == "fourth") setFourth();
};

const setInfo = () => {
  axios
    .get(`https://final.anshang.live/api/data/${repository_name}`)
    .then((res) => {
      dev_cnt.value = res.data.developers;
      issue_cnt.value = res.data.issues;
      commits_cnt.value = res.data.commits;
      releases_cnt.value = res.data.releases;
    });
};
setInfo();
const setFirst = () => {
  //developer信息
  axios
    .get(`https://final.anshang.live/api/data/${repository_name}/developer`)
    .then((res) => {
      dev_names.value = [];
      avatarUrls.value = [];
      dev_commits.value = [];
      dev_home.value = [];
      for (let i in res.data) {
        let a = res.data[i];
        dev_names.value.push(a.name);
        avatarUrls.value.push(a.avatarUrl);
        dev_commits.value.push(a.commits);
        dev_home.value.push(a.url);
      }

      setTimeout(() => {
        type EChartsOption = echarts.EChartsOption;
        let chartDom1 = document.getElementById("chart1")!;
        let myChart1 = echarts.init(chartDom1);
        let option1: EChartsOption;
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
              itemStyle: {
                color: " #95d475",
              },
            },
          ],
        };
        // @ts-ignore
        option1 && myChart1.setOption(option1);
      }, 1);
    });
};
setFirst();
const setSecond = () => {
  //issue信息
  axios
    .get(`https://final.anshang.live/api/data/${repository_name}/issue`)
    .then((res) => {
      let a = res.data;
      open_cnt.value = a.opening;
      close_cnt.value = a.closed;
      issue_time_infos.splice(0, issue_time_infos.length);

      issue_time_infos.push({
        type: "max",
        value: msToDay(a.max),
      });
      issue_time_infos.push({
        type: "min",
        value: (a.min / 864).toFixed(2) + " seconds",
      });
      issue_time_infos.push({
        type: "avg",
        value: msToDay(a.avg),
      });
      issue_time_infos.push({
        type: "diff",
        value: msToDay(a.diff),
      });
      issue_time_infos.push({
        type: "variance",
        value: msToDay(a.variance),
      });
      issue_start_end = [];
      issue_list = a.issueList;

      for (let i in a.issueList) {
        let time = a.issueList[i];
        if (time.status == "closed") {
          let start = Date.parse(time.createAt);
          let end = Date.parse(time.closeAt);
          let dif = (end - start) / 86400 / 1000;
          issue_start_end.push([time.createAt, dif]);
        }
      }

      //饼图issue数量
      type EChartsOption = echarts.EChartsOption;
      let chartDom2 = document.getElementById("chart2")!;
      let myChart2 = echarts.init(chartDom2);
      let option2: EChartsOption;
      option2 = {
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
              {
                value: open_cnt.value,
                name: `Opening:${open_cnt.value}`,
              },
              {
                value: close_cnt.value,
                name: `Closed:${close_cnt.value}`,
              },
            ],
            center: ["50%", "50%"],
          },
        ],
      };
      // @ts-ignore
      option2 && myChart2.setOption(option2);
      //散点图
      let chartDom3 = document.getElementById("chart3")!;
      let myChart3 = echarts.init(chartDom3);
      let option3: EChartsOption;
      option3 = {
        grid: {
          left: "1%", // 固定左边刻度宽度
          right: "2%",
          bottom: "13%",
          top: "2%",
          containLabel: true,
        },
        tooltip: {
          formatter: function (params) {
            // @ts-ignore
            let start = params.data[0].split("T")[0];
            // @ts-ignore
            let dif = params.data[1].toFixed(2);
            if (dif > 1) {
              return `Start from ${start}</br>Last for ${dif} days`;
            } else {
              // @ts-ignore
              dif = (params.data[1] * 24).toFixed(2);
              return `Start from ${start}</br>Last for ${dif} hours`;
            }
          },
        },
        xAxis: {
          type: "time", // 设置类型为time
          name: "创建日期", //x轴的标签
          interval: 3600 * 24 * 1000 * 30 * 2, //3600 * 24 * 1000为一天，我这边是设置90天为间隔的，小伙伴们可以自己调整
          nameLocation: "middle",
        },
        yAxis: {
          type: "log", // 设置类型为time
          name: "持续时常(对数)", //x轴的标签
          interval: 1000,
          //3600 * 24 * middle
          nameLocation: "middle",
        },
        series: [
          {
            symbolSize: 6,
            data: issue_start_end,
            type: "scatter",
          },
        ],
      };
      // @ts-ignore
      option3 && myChart3.setOption(option3);

      //第三页的信息
      open_issues = [];
      close_issues = [];

      issue_list = a.issueList;
      for (const key in issue_list) {
        if (issue_list[key].status == "open") {
          open_issues.push(issue_list[key]);
        } else {
          close_issues.push(issue_list[key]);
        }
      }
    });
};
const setThird = () => {
  setSecond();
};
function formatDate(date: Date) {
  var date = new Date(date);
  var YY = date.getFullYear() + "-";
  var MM =
    (date.getMonth() + 1 < 10
      ? "0" + (date.getMonth() + 1)
      : date.getMonth() + 1) + "-";
  var DD = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
  return YY + MM + DD;
}
let year_date_cnt = reactive<any>({});
let commits = reactive<any>([]);
let interval_cnt = reactive<any>([]);
const chooseYear = (year: string) => {
  type EChartsOption = echarts.EChartsOption;
  let chartDom4 = document.getElementById("chart4")!;
  let myChart4 = echarts.init(chartDom4);
  let option4: EChartsOption;
  function getVirtualData(year: string) {
    const date = +echarts.time.parse(year + "-01-01");
    const end = +echarts.time.parse(+year + 1 + "-01-01");
    const dayTime = 3600 * 24 * 1000;
    let data: [string, number][] = [];
    for (let time = date; time < end; time += dayTime) {
      var day = echarts.time.format(time, "{yyyy}-{MM}-{dd}", false);
      if (year_date_cnt[year][day] == undefined) {
        data.push([day, 0]);
      } else {
        var cnt = year_date_cnt[year][day];
        data.push([day, cnt]);
      }
    }
    return data;
  }
  option4 = {
    tooltip: {
      formatter: function (params) {
        // @ts-ignore
        return `${params.data[0]}</br>${params.data[1]} commits`;
      },
    },
    visualMap: {
      min: 1,
      max: 50,
      pieces: [
        { min: 16 },
        { min: 8, max: 15 },
        { min: 4, max: 7 },
        { min: 1, max: 3 },
      ],
      calculable: true,
      type: "piecewise",
      orient: "horizontal",
      left: "center",
      top: 10,
      inRange: {
        color: ["rgb(220,233,200)", "rgb(123,201,111)", "rgb(25,98,35)"],
      },
    },
    calendar: {
      top: 50,
      left: 30,
      right: 30,
      cellSize: ["auto", 13],
      range: year,
      itemStyle: {
        borderWidth: 0.5,
      },
      yearLabel: { show: false },
    },
    series: {
      type: "heatmap",
      coordinateSystem: "calendar",
      data: getVirtualData(year),
    },
  };
  // @ts-ignore
  option4 && myChart4.setOption(option4);
};
const setFourth = () => {
  //commit信息
  axios
    .get(`https://final.anshang.live/api/restful/${repository_name}/commit`)
    .then((res) => {
      let list = res.data;
      for (let i in list) {
        let commit_date = new Date(list[i].date);
        commits.push(commit_date);
        let year = commit_date.getFullYear().toString();
        let date = formatDate(commit_date);
        if (!Object.keys(year_date_cnt).includes(year)) {
          year_date_cnt[year] = {};
        } else {
          let year_data = year_date_cnt[year];
          if (!Object.keys(year_data).includes(date)) year_data[date] = 1;
          else year_data[date]++;
        }
      }
      chooseYear("2022");
      //release信息
      axios
        .get(
          `https://final.anshang.live/api/restful/${repository_name}/release`
        )
        .then((res) => {
          let releases = res.data;
          for (let i = releases.length - 1; i > 0; i--) {
            let start = new Date(releases[i].createAt);
            let end = new Date(releases[i - 1].createAt);
            let count = 0;
            for (const key in commits) {
              if (start < commits[key] && commits[key] < end) {
                count++;
              }
            }
            interval_cnt.push([
              releases[i].name,
              releases[i - 1].name,
              formatDate(end),
              count,
            ]);
          }
          type EChartsOption = echarts.EChartsOption;
          var chartDom5 = document.getElementById("chart5")!;
          var myChart5 = echarts.init(chartDom5);
          var option5: EChartsOption;

          // prettier-ignore
          const data = interval_cnt;

          const labelList = data.map(function (item: any) {
            return item[1];
          });
          const dateList = data.map(function (item: any) {
            return item[2];
          });
          const valueList = data.map(function (item: any) {
            return item[3];
          });

          option5 = {
            // Make gradient line here
            visualMap: [
              {
                show: false,
                type: "continuous",
                seriesIndex: 1,
                dimension: 0,
                min: 0,
                max: dateList.length - 1,
              },
            ],

            title: [],
            tooltip: {
              trigger: "axis",
              formatter: function (params) {
                // @ts-ignore
                return `Update release at ${params[0].axisValue}</br>after ${params[0].data} commits`;
              },
            },
            xAxis: [
              {
                data: dateList,
                axisPointer: {},
              },
            ],
            yAxis: [{}],
            grid: {
              left: "20", // 固定左边刻度宽度
              right: "20",
              bottom: "10%",
              top: "5",
              containLabel: true,
            },
            series: [
              {
                name: "value",
                type: "line",
                showSymbol: true,
                data: valueList,
              },
            ],
          };
          // @ts-ignore
          option5 && myChart5.setOption(option5);
        });
    });
};

//
const activeName = ref("first");
const handleClick = (tab: TabsPaneContext, event: Event) => {
  renderPage(tab.props.name);
};

const msToDay = (num: number) => {
  return (num / 86400000).toFixed(2) + " days";
};

const go = (url: string) => {
  axios.get(url).then((res) => {
    window.location.href = res.data.html_url;
  });
};
</script>
