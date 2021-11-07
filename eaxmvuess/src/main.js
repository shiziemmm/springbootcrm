import { createApp } from 'vue'
import App from './App.vue'

//导包axios--------------------------------
import axios from 'axios'
import VueAxios from 'vue-axios'
import store from './components/store/index'

//引入路由--------------------------------
import router from './router/index.js'
//引入ElementUI--------------------------------
import ElementUI from 'element-plus'
import 'element-plus/theme-chalk/index.css'
//ElementUI改中文
import 'dayjs/locale/zh-cn'
import locale from "element-plus/lib/locale/lang/zh-cn"

axios.defaults.baseURL = "http://localhost:8188/"
//设置默认的请求超时时间。例如超过了5s，就会告知用户当前请求超时，请刷新等。
axios.defaults.timeout = 5000;




createApp(App)
    .use(VueAxios,axios)
    .use(router)
    .use(store)
    .use(ElementUI,{locale})
    .mount('#app')
