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
    },
    setPayed(state,payload){
      state.userData.payed = payload
    }
  },
  actions: {
    login({ commit }, payload) {
      axios.post("/login", payload).then(res => {
        commit("setUser", res.data.user);
        commit("setToken", res.data.token);
      }).catch(e => {
        alert('이메일 또는 비밀번호가 틀렸습니다.')
      })
    },
    logout({ commit }) {
      commit("setUser", false);
    }
  }
});
