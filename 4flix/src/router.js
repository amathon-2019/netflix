import Vue from "vue";
import Router from "vue-router";
import Home from "./views/Home.vue";
import Signup from "./views/Signup.vue";
import store from "./store";

Vue.use(Router);

const requireAuth = () => (to, from, next) => {
  console.log(store.state.token)
  if (store.state.token) {
    next();
  } else {
    next({ path: '/' })
  }
}
export default new Router({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    {
      path: "/",
      name: "home",
      component: Home,
    },
    {
      path: "/signup",
      name: "signup",
      component: Signup,
    },
    {
      path: "/mypage",
      name: "mypage",
      component: () => import("./views/MyPage"),
      beforeEnter: requireAuth()
    }
  ]
});
