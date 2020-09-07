import Vue from "vue";
import VueRouter from "vue-router";
import Main from '../views/Main.vue';
import BookList from '../views/BookList.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Main",
    component: Main
  },
  {
    path: "/bookList",
    name: "BookList",
    component: BookList
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
