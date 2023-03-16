<template>
    <div>
      <button @click="login">로그인</button>
      <button @click="refreshToken">토큰 갱신</button>
    </div>
  </template>
  
  <script>
  import axios from "axios";
  import { reactive } from "vue";
  
  export default {
    name: "JwtUtilTest",
    setup() {
      const state = reactive({
        accessToken: "",
        refreshToken: "",
        userJwtIdx: "",
        subject: "testSubject",
      });
  
      const config = {
        headers: {
          "Content-Type": "application/json",
        },
      };
  
      const login = async () => {
        try {
          const response = await axios.post(
            "/api/auth/login",
            {
              username: "test",
              password: "test",
            },
            config
          );
          state.accessToken = response.data.accessToken;
          state.refreshToken = response.data.refreshToken;
          state.userJwtIdx = response.headers.refreshTokenIdx;
          console.log("accessToken: ", state.accessToken);
          console.log("refreshToken: ", state.refreshToken);
          console.log("userJwtIdx: ", state.userJwtIdx);
        } catch (error) {
          console.log(error);
        }
      };
  
      const refreshToken = async () => {
        try {
          const response = await axios.post(
            "/api/auth/refresh",
            { userJwtIdx: state.userJwtIdx },
            {
              headers: {
                "Content-Type": "application/json",
                RefreshTokenIdx: state.userJwtIdx,
              },
            }
          );
          state.accessToken = response.data.accessToken;
          state.refreshToken = response.data.refreshToken;
          console.log("accessToken: ", state.accessToken);
          console.log("refreshToken: ", state.refreshToken);
        } catch (error) {
          console.log(error);
        }
      };
  
      return {
        login,
        refreshToken,
        state,
      };
    },
  };
  </script>


<!--------
위 코드에서 login 함수는 /api/auth/login API를 호출하여 
accessToken, refreshToken, userJwtIdx 값을 받아오고, 
refreshToken 함수는 /api/auth/refresh API를 호출하여 
accessToken, refreshToken 값을 갱신합니다.

또한, state 변수는 reactive 함수를 이용하여 
accessToken, refreshToken, userJwtIdx, subject 값을 
갖는 객체를 생성하였습니다. config 변수는 
axios 요청 시 필요한 Content-Type 헤더 값을 저장하는 객체입니다.

-->