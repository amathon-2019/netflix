<template>
  <div class="signup">
    <h1 class="title">회원 가입</h1>
    <div class="form-wrapper">
      <div class="input-wrapper">
        <el-input
          autocomplete="off"
          class="email-input"
          placeholder="이메일을 입력해주세요."
          style="color: #ffffff"
          v-model="email"
          clearable
        >
        </el-input>
        <div v-if="isCorrectEmail">
          <p id="result" class="success">{{validationResult}}</p>
        </div>
        <div v-else>
          <p id="result" class="fail">{{validationResult}}</p>
        </div>
        
      </div>
      <div class="input-wrapper">
        <el-input
          autocomplete="off"
          class="email-input"
          placeholder="비밀번호를 입력해주세요."
          style="color: #ffffff"
          v-model="password"
          show-password
        >
        </el-input>
      </div>
      <div class="input-wrapper">
        <el-input
          autocomplete="off"
          class="email-input"
          placeholder="비밀번호 확인"
          style="color: #ffffff"
          v-model="passwordConfirm"
          show-password
        >
        </el-input>
      </div>
      
    </div>
    <div class="signup-button">
        <el-button>회원가입 완료</el-button>
    </div>
    
  </div>
</template>

<script>
export default {
  name: "signup",
  data() {
    return {
      email: "",
      password: "",
      passwordConfirm: "",
      validationResult: "",
      isCorrectEmail: false
    };
  },
  watch: {
    email: function(email) {
      console.log(email);
      let re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      if (re.test(email)) {
        this.isCorrectEmail = true;
        this.validationResult = "올바른 이메일 형식입니다.";
      } else {
        this.validationResult = "이메일 형식이 올바르지 않습니다.";
      }
    }
  },
  methods: {
    // validate() {
    //   let result = validationResult;
    //   let email = this.email;
    // }
  }
};
</script>
<style lang="scss">
.signup {
  margin-top: 50px;

  .title {
    margin-bottom: 50px;
    font-size: 25px;
    color: #ffffff;
    text-align: center;
  }
  .form-wrapper {
    margin-bottom: 40px;

    .input-wrapper {
      display: flex;
      flex-direction: column;
      // justify-content: center;
      margin-bottom: 15px;
      text-align: center;
      #result {
        color: #ffffff;

        &.success {
          margin: 0 0 0 40px;
          text-align: left;
          color: green;
          font-size: 14px;
        }
        &.fail {
          margin: 0 0 0 40px;
          text-align: left;
          color: red;
          font-size: 14px;
        }
      }
    }
  }
  .signup-button {
    display: flex;
    justify-content: center;
  }
}
.email-input {
  width: 100%;

  .el-input__inner {
    width: 80%;
    height: 50px;
    // margin-bottom: 20px;
    border: none;
    border-radius: 4px;
    background-color: #333333;
    color: #ffffff;
  }
}
</style>
<style lang="scss" scoped>
@import "@/assets/css/index.scss";
</style>
