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
export default {
  data() {
    return {
      isPayment: true,
      isResourceReady: false,
      netflixEmail: "",
      netflixPw: "",
      prevPassword: "",
      newPassword: "",
      newPassword2: "",
      uid: 2,
      uemail: "gtah2yk@gmail.com"
    };
  },
  methods: {
    requestPay() {
      IMP.request_pay(
        {
          pg: "kakaopay",
          pay_method: "card",
          merchant_uid: "merchant_" + new Date().getTime(),
          name: "주문명:결제테스트",
          amount: 4000,
          buyer_email: this.uemail,
          buyer_tel: "010-1234-5678"
        },
        function(rsp) {
          if (rsp.success) {
            //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
            jQuery
              .ajax({
                url: "/payments/complete", //cross-domain error가 발생하지 않도록 주의해주세요
                type: "POST",
                dataType: "json",
                data: {
                  imp_uid: rsp.imp_uid
                  //기타 필요한 데이터가 있으면 추가 전달
                }
              })
              .done(function(data) {
                //[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
                if (everythings_fine) {
                  var msg = "결제가 완료되었습니다.";
                  msg += "\n고유ID : " + rsp.imp_uid;
                  msg += "\n상점 거래ID : " + rsp.merchant_uid;
                  msg += "결제 금액 : " + rsp.paid_amount;
                  msg += "카드 승인번호 : " + rsp.apply_num;

                  alert(msg);
                } else {
                  //[3] 아직 제대로 결제가 되지 않았습니다.
                  //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
                }
              });
          } else {
            var msg = "결제에 실패하였습니다.";
            msg += "에러내용 : " + rsp.error_msg;

            alert(msg);
          }
        }
      );
    }
  },
  mounted() {
    IMP.init("imp52759429");
    if (this.isPayment) {
      this.$axios
        .post("/account", { id: "119" })
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
@import "@/assets/css/index.scss";
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