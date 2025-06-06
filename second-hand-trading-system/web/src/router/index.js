import Vue from 'vue'
import VueRouter from 'vue-router'
import AdminLayout from '../views/layout/AdminLayout.vue'
import FrontLayout from '../views/layout/FrontLayout.vue'
import IndexLayout from '../views/layout/IndexLayout.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'index',
        component: IndexLayout,
        redirect: "/login",
        children: [
            {path: "login", name: "login", component: () => import('../views/Login')},
            {path: "register", name: "register", component: () => import('../views/Register')},
            {path: "resetPassword", name: "resetPassword", component: () => import('../views/ResetPassword')}
        ]
    },
    {
        path: '/admin',
        name: 'admin',
        component: AdminLayout,
        redirect: "/admin/home",
        children: [
            {path: "home", name: "", component: () => import('../views/admin/Home.vue')},
            {path: 'editUserInfo', name: 'editUserInfo', component: () => import('../views/editUserInfo.vue')},
            {path: 'editPassword', name: 'editPassword', component: () => import('../views/editPassword.vue')},
        ]
    },
    {
        path: '/front',
        name: 'front',
        component: FrontLayout,
        redirect: "/front/index",
        children: [
            {path: "index", name: "index", component: () => import('../views/front/Index')},
            {path: "about", name: "about", component: () => import('../views/front/About')},
            {path: "product", name: "product", component: () => import('../views/front/Product')},
            {path: "productDetail/:id", name: "productDetail", component: () => import('../views/front/ProductDetail')},
            {path: "tOrder", name: "tOrder", component: () => import('../views/front/TOrder')},
            {path: "personalCenter", name: "personalCenter", component: () => import('../views/front/PersonalCenter')},
            {path: "chatRecord", name: "chatRecord", component: () => import('../views/front/ChatRecord')},



            {path: "editUserInfo", name: "editUserInfo", component: () => import('../views/editUserInfo')},
            {path: "editPassword", name: "editPassword", component: () => import('../views/editPassword')},
        ]
    },
    {path: '*', name: 'NotFound', meta: {name: ''}, component: () => import('../views/404.vue')},
]

const router = new VueRouter({
    //路由模式
    mode: 'history',
    routes
})

export function setRoutes(menus) {
    if (!menus) {
        if (localStorage.getItem("server-userInfo") == null) {
            return
        }
        menus = JSON.parse(localStorage.getItem("server-userInfo")).menuList;
    }
    const additionalRoutes = generateDynamicRoutes(menus);
    additionalRoutes.forEach(route => {
        router.addRoute('admin', route); //
    });
}

// 权限判断函数，根据用户权限返回相应的路由配置
export function generateDynamicRoutes(menus) {
    let dynamicRoutes = [];
    console.log(menus)
    for (let menu of menus) {
        dynamicRoutes.push(
            {path: menu.address, name: menu.address, component: () => import('../views/admin/' + menu.component)}
        );
    }
    console.log(dynamicRoutes);
    return dynamicRoutes;
}

setRoutes()

export default router

