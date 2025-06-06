import axios from "axios"
import router from "../router"
import Element from "element-ui";
import Vue from "vue";

axios.defaults.baseURL = process.env.VUE_APP_API_URL

const request= axios.create({
    timeout: 10000,
    headers:{

    }
})

request.interceptors.request.use(config=>{
    if (localStorage.getItem("server-userInfo") !== null) {
        config.headers["token"]  = JSON.parse(localStorage.getItem("server-userInfo")).token

    }
    return config
})

request.interceptors.response.use(
    response =>{
        let res = response.data
        if(res.code === 200){
            return response.data

        }else{
            //重定向 ||权限不足
            if(res.code === 301 ||res.code === 401){
                Element.Message.error(res.msg)
                    //跳转到登录页面
                 router.push({path:"/login"})
                return Promise.reject(res.msg)
            }
            Element.Message.error(!res.msg?"系统异常，请联系管理员":res.msg)
            return Promise.reject(res.msg)
        }
    },
    error=>{
        console.log(error)
        return Promise.reject(error)
    }
)

export default  request
