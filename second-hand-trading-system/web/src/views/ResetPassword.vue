<template>
  <el-card class="login-box-card">
    <div class="clearfix">
      <H2 style="text-align: center">重置密码</H2>
    </div>
    <el-form ref="ruleForm" :model="from" :rules="rules" label-width="0px" size="medium" style="margin-top: 15px">
      <el-form-item label="" prop="mobile">
        <el-input v-model="from.mobile" placeholder="手机号">
          <template slot="prepend"><i class="el-icon-phone-outline"></i></template>
        </el-input>
      </el-form-item>
      <el-form-item label="" prop="smsCode">
        <el-input v-model="from.smsCode" placeholder="短信验证码">
          <template slot="prepend"><i class="el-icon-chat-round"></i></template>
          <SmsCode slot="append" :mobile="from.mobile"/>
        </el-input>
      </el-form-item>
      <el-form-item label="" prop="password">
        <el-input v-model="from.password" placeholder="密码" show-password>
          <template slot="prepend"><i class="el-icon-lock"></i></template>
        </el-input>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm()" style="width: 100%">重置</el-button>
      </el-form-item>
    </el-form>
    <div style="float: right;margin-bottom: 15px">
      <el-link type="info" style="margin-left: 10px" @click="toLogin">转到登录</el-link>
    </div>
  </el-card>
</template>
<script>
import SmsCode from "@/components/SmsCode.vue";

// 定义手机号验证规则
const validateMobile = (rule, value, callback) => {
  const reg = /^1[3-9]\d{9}$/;
  if (value && !reg.test(value)) {
    callback(new Error('请输入有效的手机号'));
  } else {
    callback();
  }
};

export default {
  components: {
    SmsCode
  },
  data() {
    return {
      from: {
        password: "",
        mobile: "",
        smsCode: ""
      },
      rules: {
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 6, message: '密码不能少于 6 位', trigger: 'blur'} // 添加密码长度验证规则
        ],
        mobile: [
          {required: true, message: '请输入手机号', trigger: 'blur'},
          {validator: validateMobile, trigger: 'blur'}
        ],
        smsCode: [
          {required: true, message: '请输入短信验证码', trigger: 'blur'},
        ],
      }
    }
  },
  methods: {
    submitForm() {
      this.$refs['ruleForm'].validate((valid) => {
        if (valid) {
          this.request.put("/sysUser/resetPasswordBySmsCode", this.from).then(res => {
            console.log(res)
            if (res.code === 200) {
              this.$message({
                message: "重置成功，正在跳转",
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

