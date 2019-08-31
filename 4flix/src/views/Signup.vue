<template>
  <div class="signup">
    <h1 class="title">회원가입</h1>
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
          <p id="result" class="success">{{emailValidationResult}}</p>
        </div>
        <div v-else>
          <p id="result" class="fail">{{emailValidationResult}}</p>
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
        <div v-if="isCorrectPassword">
          <p id="result" class="success">{{passwordValidationResult}}</p>
        </div>
        <div v-else>
          <p id="result" class="fail">{{passwordValidationResult}}</p>
        </div>
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
        <div v-if="isConfirmed">
          <p id="result" class="success">{{confirmPassword}}</p>
        </div>
        <div v-else>
          <p id="result" class="fail">{{confirmPassword}}</p>
        </div>
      </div>
      
    </div>
    <div class="signup-button">
        <el-button @click="signup">회원가입 완료</el-button>
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
      emailValidationResult: "",
      passwordValidationResult: "",
      confirmPassword: "",
      isCorrectEmail: false,
      isCorrectPassword: false,
      isConfirmed: false
    };
  },
  watch: {
    email: function(email) {
      let re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      if (re.test(email)) {
        this.isCorrectEmail = true;
        this.emailValidationResult = "올바른 이메일 형식입니다.";
      } else {
        this.isCorrectEmail = false;
        this.emailValidationResult = "이메일 형식이 올바르지 않습니다.";
      }
    },
    password: function(password) {
      if (password.length > 0 && password.length < 10) {
        this.isCorrectPassword = false;
        this.passwordValidationResult = "비밀번호가 너무 짧습니다.";
      } else if (password.length > 10) {
        this.isCorrectPassword = false;
        alert("10글자 미만으로 입력해주세요");
      } else {
        this.isCorrectPassword = true;
        this.passwordValidationResult = "올바른 비밀번호 형식입니다.";
      }
    },
    passwordConfirm: function(passwordConfirm) {
      if (this.password === passwordConfirm) {
        this.isConfirmed = true;
        this.confirmPassword = "비밀번호가 일치합니다.";
      } else {
        this.isConfirmed = false;
        this.confirmPassword = "비밀번호가 일치하지 않습니다.";
      }
    }
  },
  methods: {
    signup() {
      this.$axios
        .post("/regist", {
          email: this.email,
          password: this.password
        })
        .then(res => {
          alert("회원가입이 완료되었습니다.");
          this.$router.push({ name: "home" });
        })
        .catch(e => {
          if ((e.message = "Request failed with status code 500")) {
            alert("이미 가입된 이메일입니다.");
            this.$router.push({ name: "home" });
          }
        });
    }
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
  .el-input__clear {
    margin-right: 40px;
  }
}
</style>
<style lang="scss" scoped>
@import "@/assets/css/index.scss";
</style>
