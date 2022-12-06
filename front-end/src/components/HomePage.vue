<template>
  <el-row>
    <el-col :span="3"></el-col>
    <el-col :span="18">
      <el-card class="box-card" id="head" shadow="always" @click="go('')">
        <h1>GitHub Repository Visualization</h1>
      </el-card>
    </el-col>
    <el-col :span="3"></el-col
  ></el-row>
  <el-row>
    <el-col :span="3"></el-col>
    <el-col :span="18">
      <el-card class="box-card" shadow="hover">
        <el-row :gutter="20" class="row">
          <el-col :span="2" :offset="0" style="text-align: center">
            <el-button @click="go('')">
              <img src="../assets/icon.svg" alt=""
            /></el-button>
          </el-col>
          <el-col :span="14" :offset="0"
            ><!-- input 输入框 -->
            <el-input
              v-model="input"
              placeholder="Please enter Public GitHub repository name (eg. YangFanCool/OpenCourses)"
              id="input"
          /></el-col>
          <el-col :span="3" :offset="0"
            ><!-- 立即查看 -->
            <el-button :icon="Search">Query Now</el-button>
          </el-col>
          <el-col :span="3" :offset="0"
            ><!-- 添加到列表 -->
            <el-button :icon="Star" @click="addList">Add To List</el-button>
          </el-col>
        </el-row>
      </el-card>
    </el-col>
    <el-col :span="3"></el-col>
  </el-row>
  <el-row>
    <el-col :span="3"></el-col>
    <el-col :span="18">
      <el-card
        class="box-card radius"
        shadow="hover"
        v-for="repo in repository_names"
      >
        <el-row :gutter="20" class="row">
          <el-col
            :span="2"
            :offset="0"
            style="text-align: center"
            @click="query(repo)"
          >
            <el-button>{{ repository_names.indexOf(repo) + 1 }}</el-button>
          </el-col>
          <el-col :span="14" :offset="0" @click="query(repo)">
            <span class="name">{{ repo.split("/")[1] }}</span>
            <span class="ownner">Ownner: {{ repo.split("/")[0] }}</span>
          </el-col>
          <el-col :span="3" :offset="0"
            ><!-- 立即查看 -->
            <el-button :icon="Link" @click="go(repo)">Go To Hub</el-button>
          </el-col>
          <el-col :span="3" :offset="0"
            ><!-- 添加到列表 -->
            <el-button :icon="Delete" @click="delList(repo)"
              >Delete This</el-button
            >
          </el-col>
        </el-row>
      </el-card>
    </el-col>
    <el-col :span="3"></el-col>
  </el-row>

  <router-view></router-view>
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
  height: 85vh;
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

<script setup lang="ts">
import { reactive, ref } from "vue";
import { Link, Search, Star, Delete } from "@element-plus/icons-vue";
import router from "../router";
import axios from "axios";

const cur_repo = ref("");
let show = ref(false);
let input = ref("");
let choose = ref("");
let repository_names: string[] = reactive<string[]>([]);
const go = (name: string) => {
  window.location.href = "https://github.com/" + name;
};
const addList = () => {
  if (input.value != "") {
    repository_names.push(input.value);
    input.value = "";
  }
};
const delList = (name: string) => {
  repository_names.splice(repository_names.indexOf(name), 1);
};
const query = (name: string) => {
  let person = name.split("/")[0];
  let repo = name.split("/")[1];
  router.push(`/repository/${person}^${repo}`);
  choose.value = name;
  show.value = !show.value;
  cur_repo.value = name;
};

axios.get("https://final.anshang.live/api/data/repoAll").then((res) => {
  var data: any[] = res.data;
  for (var i in data) {
    repository_names.push(`${data[i].first}/${data[i].second}`);
  }
});
</script>
