import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

const store = new Vuex.Store({
	state: {
		hasLogin: false,
		loginProvider: "",
		openid: null,
		testvuex:false,
        colorIndex: 0,
        colorList: ['#FF0000','#00FF00','#0000FF'],
		user : {
			account : "",
			password : "",
			username : "",
			aid : undefined,
			pic : "",
			nick : "",
			id:"",
		}
	},
	mutations: {
		login(state, provider) {
			state.hasLogin = true;
			state.loginProvider = provider;
		},
		logout(state) {
			state.hasLogin = false
			state.openid = null
		},
		setOpenid(state, openid) {
			state.openid = openid
		},
		setTestTrue(state){
			state.testvuex = true
		},
		setTestFalse(state){
			state.testvuex = false
		},
        setColorIndex(state,index){
            state.colorIndex = index
        },
		setUser(state,user){
			state.hasLogin = true;
			state.user = user;
		}
	},
    getters:{
        currentColor(state){
            return state.colorList[state.colorIndex]
        },
		hasLogin(state){
			return state.hasLogin
		},
		getUserDetail(state){
			return state.user
		}
    },
	actions: {
		// lazy loading openid
		getUserOpenId: async function ({
			commit,
			state
		}) {
			return await new Promise((resolve, reject) => {
				if (state.openid) {
					resolve(state.openid)
				} else {
					uni.login({
						success: (data) => {
							commit('login')
							setTimeout(function () { //模拟异步请求服务器获取 openid
								const openid = '123456789'
								console.log('uni.request mock openid[' + openid + ']');
								commit('setOpenid', openid)
								resolve(openid)
							}, 1000)
						},
						fail: (err) => {
							console.log('uni.login 接口调用失败，将无法正常使用开放接口等服务', err)
							reject(err)
						}
					})
				}
			})
		},
		storeUserData({ commit }, user){
			commit('setUser',user)
		},
		logout({commit}){
			commit('logout')
		}
	}
})

export default store
