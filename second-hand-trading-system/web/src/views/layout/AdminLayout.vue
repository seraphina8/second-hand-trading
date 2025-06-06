<template>
  <el-container style="height: 100%; ">
    <el-aside width="200px" style="background-color: #000000;overflow: auto;">
      <h3 class="title">二手交易平台后台</h3>
      <!-- 图标更换    https://element.eleme.cn/#/zh-CN/component/icon-->
      <el-menu
          :collapse-transition="false"
          background-color="#000000"
          text-color="#fff"
          router
          style="border: none;"
          :default-active="$route.path">
        <el-menu-item index="/admin/home"><i class="el-icon-s-home"></i><span slot="title">首页</span></el-menu-item>
        <RecursiveMenu v-for="menu in menuTree" :menu="menu" :key="menu.id"/>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header style="text-align: right; font-size: 12px">
         <span v-if="userInfo.img!=null">
            <el-avatar  style="width: 30px;height: 30px;position: relative;top: 10px;right: 10px" shape="square" :size="30" :src="JSON.parse(userInfo.img)[0].url"></el-avatar>
        </span>
        <el-dropdown>
                    <span class="el-dropdown-link">
                        {{ userInfo.username }}<i class="el-icon-arrow-down el-icon--right"></i>
                    </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item><span @click="editUserInfo">个人信息</span></el-dropdown-item>
              <el-dropdown-item><span @click="toFront">跳转前台</span></el-dropdown-item>
              <el-dropdown-item><span @click="editPassword">修改密码</span></el-dropdown-item>
            <el-dropdown-item divided><span @click="logout">退出登录</span></el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
      <el-main>
        <el-card>
          <router-view/>
        </el-card>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
import tools from "@/utils/utils";
import RecursiveMenu from '../../components/RecursiveMenu.vue'

export default {
  components: {
    RecursiveMenu
  },
  data() {
    return {
      userInfo: tools.getUserInfo(),
      menuTree: []
    }
  },
  created() {
    this.getMenuTree();
  },
  methods: {
    logout() {
      this.$message({
        message: "退出登录成功，正在跳转",
        type: "success"
      })
      localStorage.clear()
      this.$router.push({path: "/login"})
    },
    toFront() {
      this.$router.push({path: "/front"})
    },
    getMenuTree() {
      this.menuTree = this.userInfo.menuTree;
    },
    editUserInfo() {
      this.$router.push({path: "/admin/editUserInfo"})
    },
    editPassword() {
      this.$router.push({path: "/admin/editPassword"})
    },
  }
};
</script>

<style>
.el-menu-item [class^=fa] {
  margin-right: 5px;
  width: 24px;
}

.el-aside::-webkit-scrollbar {
  display: none;
}

.el-header {
  box-shadow: rgb(204, 204, 204) 5px 0px 5px;
  text-align: center;
  line-height: 60px;
}


/*更改下拉框的样式*/
.el-dropdown-link {
  cursor: pointer;
}

.el-icon-arrow-down {
  font-size: 12px;
}

.title {
  text-align: center;
  color: #fff;
  height: 30px;
  line-height: 30px;
  margin-top: 11px;
}

/*搜索框大小定义*/
.input {
  width: 200px !important;
  margin-right: 10px
}

.mr10px {
  margin-right: 10px;
}

.pb10px {
  padding-bottom: 10px;
}

.demo-drawer_footer {
  text-align: center;
  margin-bottom: 10px;
}

.search-form {

}

</style>

