<template>
  <div>
    <el-button :disabled="isButtonDisabled" @click="sendVerificationCode">
      {{ isButtonDisabled ? '重新发送(' + countdown + 's)' : '发送验证码' }}
    </el-button>
  </div>
</template>

<script>
export default {
  name: "SmsCode",
  props: {
    mobile: {
      type: String,
      default: () => {
        return ""
      }
    }
  },
  data() {
    return {
      isButtonDisabled: false,
      countdown: 60, // 初始倒计时时间（秒）
      intervalId: null, // 定时器ID
    }
  },
  created() {
  },
  methods: {
    sendVerificationCode() {
      this.request.post("/sysSmsCode", {mobile: this.mobile}).then(res => {
        if (res.code === 200) {
          if (res.data) {
            this.$alert(res.data.content, '短信通知', {
              confirmButtonText: '确定'
            });
          }
          this.isButtonDisabled = true;
          this.countdown = 60;
          this.intervalId = setInterval(this.updateCountdown, 1000);
        }
      })
    },
    updateCountdown() {
      if (this.countdown > 0) {
        this.countdown--;
      } else {
        // 倒计时结束，清除定时器并重新启用按钮
        clearInterval(this.intervalId);
        this.isButtonDisabled = false;
        this.countdown = 60; // 重置为初始值
      }
    }
  }
}
</script>


<style>
</style>
