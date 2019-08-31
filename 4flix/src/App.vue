<template>
  <div id="app">
    <navigation></navigation>
    <router-view />
  </div>
</template>
<script>
import Navigation from "./components/Navigation/Navigation";
export default {
  components: {
    navigation: Navigation
  }
};
</script>

<style lang="scss">
html,
body {
  margin: 0;
  padding: 0;
}
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
