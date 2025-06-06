import router from "../router"
import Element from "element-ui";
const _ = require('lodash');

const tools = {
    //获取认证信息
    getToken() {

        if (tools.isUserLogin()) {
            return JSON.parse(localStorage.getItem("server-userInfo")).token
        } else {
            Element.Message.error("用户信息获取失败，请重新登录")
            //跳转到登录页面
            router.push({path: "/login"})
        }
    },
    //获取用户信息
    getUserInfo() {
        if (tools.isUserLogin()) {
            console.log(JSON.parse(localStorage.getItem("server-userInfo")))
            return JSON.parse(localStorage.getItem("server-userInfo"))
        } else {
            Element.Message.error("用户信息获取失败，请重新登录")
            //跳转到登录页面
            router.push({path: "/login"})
        }
    },

    isUserLogin() {
        console.log("调用")
        return localStorage.getItem("server-userInfo") !== null
    },
    getFileInfo(info) {
        if (!info) {
            return ""
        }
        return JSON.parse(info);
    },
    cloneDeep(obj) {
        return _.cloneDeep(obj);
    }

}

export default tools
