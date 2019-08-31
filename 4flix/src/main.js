import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

Vue.config.productionTip = false;

// element-ui config
// configure language
import lang from "element-ui/lib/locale/lang/ko";
import locale from "element-ui/lib/locale";
locale.use(lang);
// components
import { Button, Input } from "element-ui";
import "element-ui/lib/theme-chalk/index.css";

Vue.component(Button.name, Button);
Vue.component(Input.name, Input);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
