<template>
  <div class="home">
    <vue-glide class="home-glide" v-bind="glideOption">
      <vue-glide-slide class="home-glide-slide" v-for="i in 10" :key="i">Slide {{ i }}</vue-glide-slide>
    </vue-glide>
  </div>
</template>

<script>
import { Glide, GlideSlide } from 'vue-glide-js';
import 'vue-glide-js/dist/vue-glide.css';
export default {
  name: 'home',
  data() {
    return {
      glideOption: { perView: 1, autoplay: 2000 }
    };
  },
  components: {
    [Glide.name]: Glide,
    [GlideSlide.name]: GlideSlide
  }
};
</script>


<style lang='scss' scoped>
@import '@/assets/css/index.scss';
.home {
  &-glide {
    &-slide {
      background-color: olive;
    }
  }
}
</style>