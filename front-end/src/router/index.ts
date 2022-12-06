import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import HomePage from "../components/HomePage.vue";
import RepoPage from "../components/RepoPage.vue";

const routes: RouteRecordRaw[] = [
  {
    path: "/",
    name: "home",
    component: HomePage,
  },
  {
    path: "/repository/:repo_name",
    name: "repository",
    component: RepoPage,
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes: routes,
});

export default router;
