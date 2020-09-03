import Vue from "vue";
import VueRouter from "vue-router";
import Register from '../components/Register.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/register",
    name: "Register",
    component: Register
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
