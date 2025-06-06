<template>
  <el-card class="login-box-card">
    <div class="clearfix">
      <H2 style="text-align: center">登录</H2>
    </div>
    <el-tabs v-model="activeName" @tab-click="handleClick" style="margin-top: 10px">
      <el-tab-pane label="密码登录" name="password">
        <el-form ref="loginRuleForm" :model="login.from" :rules="login.rules" label-width="0px" size="medium" style="margin-top: 5px">
          <el-form-item label="" prop="username">
            <el-input v-model="login.from.username" placeholder="用户名/手机号">
              <template slot="prepend"><i class="el-icon-user"></i></template>
            </el-input>
          </el-form-item>
          <el-form-item label="" prop="password">
            <el-input v-model="login.from.password" placeholder="密码" show-password>
              <template slot="prepend"><i class="el-icon-lock"></i></template>
            </el-input>
          </el-form-item>
          <el-form-item size="large">
            <el-button type="primary" @click="submitForm('loginRuleForm',login.from)" style="width: 100%">登录</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="短信登录" name="code">
        <el-form ref="smsLoginRuleForm" :model="smsLogin.from" :rules="smsLogin.rules" label-width="0px" size="medium" style="margin-top: 5px">
          <el-form-item label="" prop="mobile">
            <el-input v-model="smsLogin.from.mobile" placeholder="手机号">
              <template slot="prepend"><i class="el-icon-phone-outline"></i></template>
            </el-input>
          </el-form-item>
          <el-form-item label="" prop="smsCode">
            <el-input v-model="smsLogin.from.smsCode" placeholder="短信验证码">
              <template slot="prepend"><i class="el-icon-chat-round"></i></template>
              <SmsCode slot="append" :mobile="smsLogin.from.mobile"/>
            </el-input>
          </el-form-item>
          <el-form-item size="large">
            <el-button type="primary" @click="submitForm('smsLoginRuleForm',smsLogin.from)" style="width: 100%">登录</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
    </el-tabs>
    <div style="float: right;margin-bottom: 15px">
      <el-link type="info" @click="toResetPassword">忘记密码</el-link>
      <el-link type="info" style="margin-left: 10px" @click="toRegister">免费注册</el-link>
    </div>
  </el-card>
</template>
<script>
import {setRoutes} from "@/router";
import SmsCode from "@/components/SmsCode.vue";

export default {
  components: {
    SmsCode
  },
  data() {
    return {
      activeName: 'password',
      login: {
        from: {
          username: "",
          password: ""
        },
        rules: {
          username: [
            {required: true, message: '请输入用户名/手机号', trigger: 'blur'},
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'blur'},
          ],
        },
      },
      smsLogin: {
        from: {
          mobile: "",
          smsCode: ""
        },
        rules: {
          mobile: [
            {required: true, message: '请输入手机号', trigger: 'blur'},
          ],
          smsCode: [
            {required: true, message: '请输入短信验证码', trigger: 'blur'},
          ],
        },
      },
    };
  },
  methods: {
    handleClick(tab, event) {
      this.resetForm();
    },
    submitForm(ref, from) {
      this.$refs[ref].validate((valid) => {
        if (valid) {
          let url = this.activeName === "password" ? "/sysUser/login" : "/sysUser/smsLogin";

          this.request.post(url, from).then(res => {
            console.log(res)
            if (res.code === 200) {
              let userInfo = res.data
              localStorage.setItem("server-userInfo", JSON.stringify(userInfo))
              setRoutes(userInfo.menuList);
              if (res.data.role.code === "ADMIN") {
                this.$router.push({path: "/admin"})
              }
              if (res.data.role.code === "USER") {
                this.$router.push({path: "/front"})
              }
            }
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm() {
      this.$refs['smsLoginRuleForm'].resetFields();
      this.$refs['loginRuleForm'].resetFields();
    },
    toRegister() {
      this.$router.push({path: "register"})
    },
    toResetPassword() {
      this.$router.push({path: "resetPassword"})
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

