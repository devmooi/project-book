import Vue from "vue";
import VueRouter from "vue-router";
import Main from '../views/Main.vue';
import BookList from '../views/BookList.vue';
import IdeaNote from '../views/IdeaNote.vue';

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
    component: BookList,
    props: true
  },
  {
    path: "/ideaNote",
    name: "IdeaNote",
    component: IdeaNote,
    props: true
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;