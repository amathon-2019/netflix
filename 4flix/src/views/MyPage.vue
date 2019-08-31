<template>
  <div class="myPage">
    <div class="myPage-container">
      <div class="myPage-netflix">
        넷플릭스
        <hr />
        <div v-if="isPayment">
          <div class="myPage-netflix-item">
            <label class="myPage-netflix-label" for="id">이메일</label>
            <div class="myPage-netflix-text">{{netflixEmail}}</div>
          </div>
          <div class="myPage-netflix-item">
            <label class="myPage-netflix-label" for="pw">비밀번호</label>
            <div class="myPage-netflix-text">{{netflixPw}}</div>
          </div>
        </div>
        <div class="myPage-netflix isPayment" v-else>
          <div class="myPage-netflix-desc">결제하지 않은 회원입니다.</div>
          <el-button class="gray">결제하기</el-button>
        </div>
      </div>
      <div class="myPage-password">
        <span>비밀번호 변경</span>
        <hr />
        <div>
          <el-input placeholder="기존 비밀번호"></el-input>
          <el-input placeholder="신규 비밀번호"></el-input>
          <el-input placeholder="신규 비밀번호 확인"></el-input>
          <el-button class="pwConfirm">비밀번호 변경</el-button>
        </div>
        <div></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isPayment: false,
      netflixEmail: 'gtah2yk@gmail.com',
      netflixPw: 'gtah2yk'
    };
  }
};
</script>

<style lang="scss">
.el-input {
  .el-input__inner {
    width: 100%;
    margin-bottom: 10px;
  }
}
</style>
<style lang='scss' scoped>
@import '@/assets/css/index.scss';
.myPage {
  hr {
    margin: 4px 0px;
  }
  .pwConfirm {
    display: block;
    margin-left: auto;
    margin-right: 0;
  }
  color: rgb(221, 221, 221);
  background-color: rgb(20, 20, 20);
  height: 80%;
  position: relative;

  &-container {
    width: 75%;
    max-width: 800px;
    padding: 15px 0px;
    margin: auto;
  }
  &-netflix {
    padding: 25px 0px;
    &-label {
      font-size: 80%;
      color: rgba(177, 177, 177, 0.883);
    }
    &-text {
      padding-bottom: 4px;
    }
    &-desc {
      margin-bottom: 14px;
    }
    &.isPayment {
      color: rgb(195, 195, 195);
      text-align: center;
    }
  }
}
</style>