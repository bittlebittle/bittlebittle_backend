<template>
  <div>
    <button @click="socialLogin('google')">Login with Google</button>
    <button @click="logout">Logout</button>
  </div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios';

export default {
  setup() {
    const socialLogin = async (socialLoginType) => {
      try {
        const response = await axios.get(`accounts/auth/${socialLoginType}`);
        window.location.href = response.data;
      } catch (error) {
        console.error('Error during social login:', error);
      }
    };

    const logout = async () => {
      try {
        await axios.get('accounts/logout');
        window.location.href = '/';
      } catch (error) {
        console.error('Error during logout:', error);
      }
    };

    return {
      socialLogin,
      logout
    };
  }
};
</script>