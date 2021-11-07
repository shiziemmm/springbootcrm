import { createStore } from 'vuex'
const store = createStore({
    state: {
        token: '',
        // children:{
        //     children:window.localStorage.getItem('children' || '[]') == null ?
        //         '' : JSON.parse(window.localStorage.getItem('children' || '[]'))
        // },

    },
    mutations: {
        initMenu(state, menus){
            //将获取的菜单信息，装载到children对象中
            state.children = menus;
            window.console.log("存储成功");
            window.localStorage.setItem('children',JSON.stringify(menus));
            window.console.log("children:"+JSON.stringify(menus))
        },

    }
})
export default store
