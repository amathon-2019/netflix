<template>
  <div class="myPage">
    <div class="myPage-container">
      <div class="myPage-netflix">
        넷플릭스
        <hr />
        <div v-if="getPayed">
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
          <el-button class="gray" @click="requestPay">결제하기</el-button>
        </div>
      </div>
      <div class="myPage-password">
        <span>비밀번호 변경</span>
        <hr />
        <div>
          <el-input v-model="prevPassword" show-password clearable placeholder="기존 비밀번호"></el-input>
          <el-input v-model="newPassword" show-password clearable placeholder="신규 비밀번호"></el-input>
          <el-input v-model="newPassword2" show-password clearable placeholder="신규 비밀번호 확인"></el-input>
          <el-button class="pwConfirm">비밀번호 변경</el-button>
        </div>
        <div></div>
      </div>
    </div>
  </div>
</template>

<script>
const IMP = window.IMP;
import { mapGetters } from 'vuex';
import axios from 'axios';
export default {
  data() {
    return {
      isResourceReady: false,
      netflixEmail: '',
      netflixPw: '',
      prevPassword: '',
      newPassword: '',
      newPassword2: ''
    };
  },
  computed: {
    ...mapGetters(['getUser', 'getPayed'])
  },
  methods: {
    requestPay() {
      IMP.request_pay(
        {
          pg: 'kakaopay',
          pay_method: 'card',
          merchant_uid: 'merchant_' + new Date().getTime(),
          name: '주문명:결제테스트',
          amount: 4000,
          buyer_email: this.getUser.email,
          buyer_tel: '010-1234-5678'
        },
        rsp => {
          console.log(rsp);
          if (rsp.success) {
            //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
            console.log(this.getUser.id);
            axios({
              url: 'http://13.124.122.246:8080/pay', //cross-domain error가 발생하지 않도록 주의해주세요
              method: 'post',
              headers: {
                'content-type': 'application/json'
              },
              data: {
                id: this.getUser.id
              }
            })
              .then(res => {
                console.log(res);
                this.netflixEmail = res.data.email;
                this.netflixPw = res.data.password;
                if (res.status == 202) {
                  var msg = '결제가 완료되었습니다.';
                  msg += '\n고유ID : ' + rsp.imp_uid;
                  msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                  msg += '\n결제 금액 : ' + rsp.paid_amount;
                  alert(msg);
                } else {
                  //[3] 아직 제대로 결제가 되지 않았습니다.
                  //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
                }
              })
              .catch(err => console.log(err));
          } else {
            var msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;

            alert(msg);
          }
        }
      );
    }
  },
  mounted() {
    IMP.init('imp52759429');
    const user = this.getUser;
    if (this.getPayed) {
      this.$axios
        .post('/account', { id: user.id })
        .then(res => {
          this.netflixEmail = res.data.email;
          this.netflixPw = res.data.password;
        })
        .catch(e => {
          console.log(e);
        });
    }
  }
};
</script>

<style lang="scss">
.el-input {
  .el-input__inner {
    width: 100%;
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