<template>
    <div>
      <button @click="login">Login</button>
      <button @click="refresh">Refresh Token</button>
      <button @click="logout">Logout</button>
    </div>
  </template>
  
  <script>
  import { reactive, toRefs, onMounted } from 'vue'
  import axios from 'axios'
  import { useStore } from 'vuex'
  
  export default {
    setup() {
      const state = reactive({
        jwt: '',
        access_token: '',
        refresh_token: '',
        error: null,
        loading: false,
      })
      
      const store = useStore()
  
      const login = async () => {
        state.loading = true
        try {
          const res = await axios.post('/login', { username: 'test', password: 'test' })
          state.jwt = res.data.jwt
          store.commit('setJwt', state.jwt)
          store.dispatch('refreshTokens')
        } catch (err) {
          state.error = err.message
        }
        state.loading = false
      }
  
      const refresh = async () => {
        state.loading = true
        try {
          const res = await axios.post('/refresh', { refresh_token: state.refresh_token })
          state.access_token = res.data.access_token
          store.commit('setAccessToken', state.access_token)
        } catch (err) {
          state.error = err.message
        }
        state.loading = false
      }
  
      const logout = () => {
        store.commit('setJwt', '')
        store.commit('setAccessToken', '')
        store.commit('setRefreshToken', '')
      }
  
      onMounted(() => {
        state.jwt = store.state.jwt
        state.access_token = store.state.access_token
        state.refresh_token = store.state.refresh_token
      })
  
      return {
        ...toRefs(state),
        login,
        refresh,
        logout,
      }
    },
  }
</script>  



<!--Vuex, store.js-->

<script>
import { createStore } from 'vuex'

const store = createStore({
  state: {
    jwt: '',
    access_token: '',
    refresh_token: '',
  },
  mutations: {
    setJwt(state, jwt) {
      state.jwt = jwt
    },
    setAccessToken(state, access_token) {
      state.access_token = access_token
    },
    setRefreshToken(state, refresh_token) {
      state.refresh_token = refresh_token
    },
  },
  actions: {
    refreshTokens({ state, commit }) {
      axios.post('/refresh', { refresh_token: state.refresh_token })
        .then(res => {
          commit('setAccessToken', res.data.access_token)
          
 </script>