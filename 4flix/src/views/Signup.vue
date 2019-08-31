<template>
  <div class="signup">
    <div class="email-wrapper">
      <!-- <span style="color:#ffffff">e-mail</span> -->
      <el-input
        class="email-input"
        placeholder="Email"
        style="color: #ffffff"
        v-model="input"
        clearable
      >
      </el-input>
    </div>
    <div class="email-wrapper">
      <!-- <span style="color:#ffffff">password</span> -->
      <el-input
        class="email-input"
        placeholder="Password"
        style="color: #ffffff"
        v-model="input"
        clearable
      >
      </el-input>
    </div>
    
    
  </div>
</template>

<script>
export default {
  name: "signup",
  data() {
    return {
      input: ""
    };
  }
};
</script>
<style lang="scss">
* {
  background-color: #000000;
}
.signup {
  .email-wrapper {
    display: flex;
    justify-content: center;
  }
}
.email-input {
  width: 100%;

  .el-input__inner {
    width: 80%;
    height: 50px;
    margin-bottom: 20px;
    border: none;
    border-radius: 4px;
    background-color: #333333;
    color: white;
  }
}
</style>
