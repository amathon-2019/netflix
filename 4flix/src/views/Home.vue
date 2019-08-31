<template>
  <div class="home">
    <vue-glide class="home-glide" v-bind="glideOption">
      <vue-glide-slide
        class="home-glide-slide"
        v-for="(content, index) in slideOption"
        :key="index"
      >
        <img height="250" width="100%" :src="slideImg" />
        <div class="slide-text">{{ content }}</div>
      </vue-glide-slide>
    </vue-glide>
    <div class="home-form">
      <div class="home-form-container" v-if="!getUser">
        <el-input placeholder="이메일" v-model="email"></el-input>
        <el-input placeholder="비밀번호" v-model="password" show-password></el-input>
        <el-button @click="login" v-text="isLoading?'loading...':'로그인'"></el-button>
        <el-button @click="goSignUp" class="gray" v-text="'회원가입'"></el-button>
      </div>
      <div class="home-form-container" v-else>
        <el-button @click="goMyPage" v-text="'마이페이지'"></el-button>
        <el-button class="gray" @click="logout" v-text="'로그아웃'"></el-button>
      </div>
    </div>
  </div>
</template>

<script>
import { Glide, GlideSlide } from "vue-glide-js";
import "vue-glide-js/dist/vue-glide.css";
const slideImg = require(`@/assets/image/slide.png`);
import { mapGetters } from "vuex";
export default {
  name: "home",
  data() {
    return {
      slideImg,
      glideOption: { perView: 1, autoplay: 3000 },
      email: "",
      password: "",
      isLoading: false,
      slideOption: [
        "4FLIX 커뮤니티는 한국 넷플릭스 사용자 커뮤니티입니다.",
        "4FLIX는 한 달마다 새로운 사용자들과 매칭시켜드립니다.",
        "기존의 서비스에 비해 프라이버시가 보장됩니다.",
        "4FLIX 커뮤니티는 한국 넷플릭스 사용자 커뮤니티입니다.",
        "4FLIX는 한 달마다 새로운 사용자들과 매칭시켜드립니다.",
        "기존의 서비스에 비해 프라이버시가 보장됩니다."
      ]
    };
  },
  watch: {
    getUser(val, oldVal) {
      console.log("hi", val);
      return val;
    }
  },
  computed: {
    mainButtonText() {
      if (this.getUser) {
        return "마이페이지";
      } else {
        return "로그인";
      }
    },
    ...mapGetters(["getUser"])
  },
  components: {
    [Glide.name]: Glide,
    [GlideSlide.name]: GlideSlide
  },
  methods: {
    goMyPage() {
      this.$router.push({ name: "mypage" });
    },
    goSignUp() {
      this.$router.push({ name: "signup" });
    },
    login() {
      this.isLoading = true;
      setTimeout(this.signup, 1000);
    },
    logout() {
      this.$store.dispatch("logout");
    },
    signup() {
      this.$store
        .dispatch("login", {
          email: this.email,
          password: this.password
        })
        .then(res => {
          this.isLoading = false;
        })
        .catch(err => {
          console.log(err);
        });
    }
  }
};
</script>

<style lang='scss' scoped>
@import "@/assets/css/index.scss";
.home {
  position: relative;
  .slide-text {
    width: 100%;
    font-size: 20px;
    padding: 0px 20px;
    position: absolute;
    top: 55%;
  }
  &-glide {
    &-slide {
      color: #ffffff;
      width: 100%;
      position: relative;
      background-color: $background-color;
    }
  }
  &-form {
    &-container {
      display: flex;
      margin: auto;
      flex-direction: column;
      width: 70%;
      max-width: 800px;
    }
    .el-button {
      margin-bottom: 10px;
      font-size: 17px;
    }
  }
}
</style>