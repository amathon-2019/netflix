import Vue from "vue";
import Vuex from "vuex";
import axios from "./utill/axios";
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    userData: false,
    token: false
  },
  getters: {
    getUser(state) {
      return state.userData;
    },
    getToken(state) {
      return state.token;
    },
    getPayed(state) {
      return state.userData.payed;
    }
  },
  mutations: {
    setUser(state, payload) {
      state.userData = payload;
    },
    setToken(state, payload) {
      state.token = payload;
    }
  },
  actions: {
    login({ commit }, payload) {
      axios.post("/login", payload).then(res => {
        commit("setUser", res.data.user);
        commit("setToken", res.data.token);
      });
    },
    logout({ commit }) {
      commit("setUser", false);
    }
  }
});
