<template>
  <el-card class="login-box-card">
    <div class="clearfix">
      <H2 style="text-align: center">注册</H2>
    </div>
    <el-form ref="ruleForm" :model="from" :rules="rules" label-width="0px" size="medium" style="margin-top: 15px">
      <el-form-item label="" prop="username">
        <el-input v-model="from.username" placeholder="用户名">
          <template slot="prepend"><i class="el-icon-user"></i></template>
        </el-input>
      </el-form-item>
      <el-form-item label="" prop="password">
        <el-input v-model="from.password" placeholder="密码" show-password>
          <template slot="prepend"><i class="el-icon-lock"></i></template>
        </el-input>
      </el-form-item>
      <el-form-item label="" prop="mobile">
        <el-input v-model="from.mobile" placeholder="手机号">
          <template slot="prepend"><i class="el-icon-phone-outline"></i></template>
        </el-input>
      </el-form-item>
      <el-form-item label="" prop="smsCode">
        <el-input v-model="from.smsCode" placeholder="短信验证码">
          <template slot="prepend"><i class="el-icon-chat-round"></i></template>
          <SmsCode 
            slot="append" 
            :mobile="from.mobile" 
            :disabled="!isMobileValid"
            @send="handleSendSmsCode"
          />
        </el-input>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm()" style="width: 100%">注册</el-button>
      </el-form-item>
    </el-form>
    <div style="float: right;margin-bottom: 15px">
      <el-link type="info" style="margin-left: 10px" @click="toLogin">已有账号?去登录</el-link>
    </div>
  </el-card>
</template>
<script>
import SmsCode from "@/components/SmsCode.vue";

export default {
  components: {
    SmsCode
  },
  data() {
    return {
      from: {
        username: "",
        password: "",
        mobile: "",
        smsCode: "",
        isMobileValid:""
      },
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {
            min: 6,
            message: '密码至少需要六位',
            trigger: 'blur'
          }
        ],
        mobile: [
          {required: true, message: '请输入手机号', trigger: 'blur'},
          {
            pattern: /^1[3-9]\d{9}$/,
            message: '请输入有效的手机号',
            trigger: 'blur'
          }
        ],
        smsCode: [
          {required: true, message: '请输入短信验证码', trigger: 'blur'},
        ],
      }
    }
  },
  watch: {
    'from.mobile': function(newValue) {
      const mobileRule = this.rules.mobile.find(rule => rule.pattern);
      if (mobileRule) {
        this.isMobileValid = mobileRule.pattern.test(newValue);
      }
    }
  },
  methods: {
    handleSendSmsCode() {
      const mobileRule = this.rules.mobile.find(rule => rule.pattern);
      if (mobileRule && !mobileRule.pattern.test(this.from.mobile)) {
        this.$message.error('请输入有效的手机号');
        return;
      }

      this.request.post('/sysUser/sendSmsCode', { mobile: this.from.mobile })
        .then(response => {
          if (response.code === 200) {
            this.$message.success('验证码已发送，请查收');
          } else {
            this.$message.error('验证码发送失败，请重试');
          }
        })
        .catch(error => {
          console.error('发送验证码请求出错:', error);
          this.$message.error('验证码发送失败，请重试');
        });
    },
    submitForm() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          this.request.post("/sysUser/register", this.from).then(res => {
            console.log(res)
            if (res.code === 200) {
              this.$message({
                message: "注册成功，正在跳转",
                type: "success"
              })
              setTimeout(() => {
                this.$router.push({path: "login"})
              }, 1000)
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    toLogin() {
      this.$router.push({path: "login"})
    }
  }
}
</script>
<style scoped>
.login-box-card {
  position: absolute;
  top: 120px;
  right: 60px;
  width: 350px;
}
</style>

