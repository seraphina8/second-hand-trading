import Vue from 'vue'
import App from './App.vue'
import router from './router'

Vue.config.productionTip = false
import Element from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"
import './styles/index.css'

//设置项目样式为mini
Vue.use(Element, {size: "mini"});

import request from '@/utils/request'

Vue.prototype.request = request

import iconPicker from 'vue-fontawesome-elementui-icon-picker';

Vue.use(iconPicker);

// 创建 EventBus
const EventBus = new Vue();

// 将 EventBus 导出，以便在其他文件中使用
export { EventBus };

import JwChat from "jwchat";

Vue.use(JwChat);

new Vue({
    router,
    render: h => h(App)
}).$mount('#app')
