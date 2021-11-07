import { createStore } from 'vuex'
const store = createStore({
    state: {
        token: '',
        children:{
            children:window.localStorage.getItem('children' || '[]') == null ?
                '' : JSON.parse(window.localStorage.getItem('children' || '[]'))
        },

    },
    mutations: {
        initMenu(state, menus){
            //将获取的菜单信息，装载到children对象中
            state.children = menus;
            window.console.log("存储成功");
            window.localStorage.setItem('children',JSON.stringify(menus));
            window.console.log("children:"+JSON.stringify(menus))
        },
        login(state,user) { //存登录信息
            console.log("存储的值", user);
            //在localStorage中添加用户，字符串对象
            //JSON.stringify 将对象序列化为JSON格式的字符串
            localStorage.setItem("loginuser", JSON.stringify(user));
            state.user = {
                ...user
            }
            state.user.empName = user.empName;
            console.log("vvvv" + user.empName);
        },
    },

})
export default store
