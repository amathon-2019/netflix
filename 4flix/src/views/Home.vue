<template>
  <div class="home">
    <vue-glide class="home-glide" v-bind="glideOption">
      <vue-glide-slide class="home-glide-slide" v-for="(content, index) in slideOption" :key="index">
        <img height="250" width="100%" :src="slideImg" />
        <div class="slide-text">{{ content }}</div>
      </vue-glide-slide>
    </vue-glide>
    <div class="home-form">
      <el-button>로그인</el-button>
      <el-button class="gray">회원가입</el-button>
    </div>
  </div>
</template>

<script>
import { Glide, GlideSlide } from "vue-glide-js";
import "vue-glide-js/dist/vue-glide.css";
const slideImg = require(`@/assets/image/slide.png`);
export default {
  name: "home",
  data() {
    return {
      slideImg,
      glideOption: { perView: 1, autoplay: 3000 },
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
  components: {
    [Glide.name]: Glide,
    [GlideSlide.name]: GlideSlide
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
    display: flex;
    margin: auto;
    flex-direction: column;
    width: 70%;
    max-width: 800px;
    .el-button {
      margin-bottom: 10px;
      font-size: 17px;
    }
  }
}
</style>