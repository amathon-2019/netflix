<template>
  <div class="Navigation">
    <router-link :to="{name:'home'}">
      <img class="Navigation-image" :src="logo " alt srcset />
    </router-link>
  </div>
</template>

<script>
const logo = require('@/assets/image/logo.png');
export default {
  data() {
    return {
      logo
    };
  }
};
</script>

<style lang='scss' scoped>
@import '@/assets/css/index.scss';
.Navigation {
  background-color: $background-color;
  text-align: center;
  padding: 15px 0px;
  &-image {
    width: 130px;
  }
}
</style>