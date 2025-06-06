<template>
  <div class="main">
    <MessagePoller></MessagePoller>
    <el-container>
      <el-header style="padding: 0">
        <div class="container-fluid" style="box-shadow: 5px 0 5px #ccc">
          <div class="container">
            <el-row class="nav">
              <el-col :span="4" class="logo">
                <div>
                  <router-link to="/front/index" tag="div">
                    <img src="@/assets/logo.jpg" alt="">
                  </router-link>
                </div>
              </el-col>
              <el-col :span="16">
                <div>
                  <el-menu :default-active="$route.path" class="el-menu-demo" mode="horizontal" router
                           @select="handleSelect">
                    <el-menu-item index="/front/index">首页</el-menu-item>
                    <el-menu-item index="/front/product">海鲜市场</el-menu-item>
                    <el-menu-item index="/front/tOrder">我买到的</el-menu-item>
                    <el-menu-item index="/front/personalCenter">个人中心</el-menu-item>
                    <el-menu-item index="/front/about">关于我们</el-menu-item>
                    <template v-if="!isUserLogin">
                      <el-menu-item index="/login">登录</el-menu-item>
                      <el-menu-item index="/register">去注册</el-menu-item>
                    </template>
                  </el-menu>
                </div>
              </el-col>
              <el-col :span="4" class="control">
                <i class="el-icon-bell" style="width: 30px;height: 30px;position: relative;top: 20px;right: 50px" @click="openMessage"></i>
                  <span v-if="userInfo.img!=null">
                    <el-avatar  style="width: 30px;height: 30px;position: relative;top: 10px;right: 10px" shape="square" :size="30" :src="JSON.parse(userInfo.img)[0].url"></el-avatar>
                  </span>
                  <el-dropdown v-if="isUserLogin">
                                    <span class="el-dropdown-link">{{ userInfo.username }}<i
                                        class="el-icon-arrow-down el-icon--right"></i></span>
                  <el-dropdown-menu slot="dropdown">
                      <el-dropdown-item><span @click="editUserInfo">个人信息</span></el-dropdown-item>
                      <el-dropdown-item><span @click="toHome">后台管理</span></el-dropdown-item>
                    <el-dropdown-item><span @click="editPassword">修改密码</span></el-dropdown-item>
                    <el-dropdown-item divided><span @click="logout">退出登录</span>
                    </el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
              </el-col>
            </el-row>
          </div>
        </div>
      </el-header>
      <div class="container">
        <el-main>
          <router-view/>
        </el-main>
      </div>
<!--      <div class="container-fluid">-->
<!--        <el-footer class="footer">-->
<!--          <p></p>-->
<!--          <p></p>-->
<!--        </el-footer>-->
<!--      </div>-->
    </el-container>
    <Massage v-if="showMessage" :selectRoomId="selectRoomId" @close="showMessage=false"></Massage>
  </div>
</template>

<script>
import tools from "@/utils/utils";
import MessagePoller from "@/components/MessagePoller.vue";
import Massage from "@/views/front/Massage.vue";
import {EventBus} from "@/main";

export default {
  components: {
    Massage,
    MessagePoller
  },
  data() {
    return {
      isUserLogin: tools.isUserLogin(),
      userInfo: tools.getUserInfo(),
      showMessage: false,
      selectRoomId: 0,
    };
  },
  created() {
    EventBus.$on('openMessage', (info) => {
      this.openMessage(info);
    })
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    logout() {
      this.$message({
        message: "退出登录成功，正在跳转",
        type: "success"
      })
      localStorage.clear()
      this.$router.push({path: "/login"})
    },
    toHome() {
      this.$router.push({path: "/admin/"})
    },
    editUserInfo() {
      this.$router.push({path: "/front/editUserInfo"})
    },
    editPassword() {
      this.$router.push({path: "/front/editPassword"})
    },

    openMessage(info) {
      if (info) {
        this.selectRoomId = info.id;
      }
      this.showMessage = true
    }
  },
  beforeDestroy() {
    EventBus.$off('openMessage');
  }
}
</script>


<style scoped>
.container {
  width: 1200px;
  margin: 0 auto;
  margin-bottom: 60px;
  /*background: pink;*/

}

.container-fluid {
  width: 100%;
  /*background: #909399;*/
}

.el-menu.el-menu--horizontal {
  border-bottom: none;
}

.el-menu, .el-header {
  background: #fff;
  /*background: #909399;*/
}

.el-footer {
  padding: 10px 0;
  height: auto !important;
}

.el-main {
  /*padding: 20px;*/
  /*box-shadow: 0 0 3px #ccc;*/
  /*margin: 30px 0 110px;*/
}

.main {
  position: relative;
  min-height: 100%;
}

.el-dropdown-link {
  cursor: pointer;
}

.el-icon-arrow-down {
  font-size: 12px;
}


</style>

<style scoped>
.nav {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.control {
  display: flex;
  justify-content: right;
}

.logo img {
  display: flex;
  align-items: center;
  width: 200px;
  cursor: pointer;
}

.footer {
  box-shadow: rgb(204, 204, 204) 5px 0px 5px;
  text-align: center;
  color: #000000;
  position: absolute;
  bottom: 0;
  width: 100%;
}

.footer p {
  line-height: 30px;
  font-size: 14px;

}
</style>
