<script>

import { ref } from 'vue';
import axios from 'axios';

const apiBaseUrl = 'http://localhost:8080';

export default {
  setup() {
    const users = ref([]);
    const user = ref({});
    const userNo = ref(null);

    async function createAccessJwt(subject) {
      try {
        const response = await axios.post(`${apiBaseUrl}/access-jwt`, { subject });
        return response.data;
      } catch (error) {
        console.error(error);
      }
    }

    async function createRefreshJwt(subject) {
      try {
        const response = await axios.post(`${apiBaseUrl}/refresh-jwt`, { subject });
        return response.data;
      } catch (error) {
        console.error(error);
      }
    }

    async function removeRefreshJwt(jwt, userJwt) {
      try {
        const response = await axios.post(`${apiBaseUrl}/remove-refresh-jwt`, { jwt, userJwt });
        return response.data;
      } catch (error) {
        console.error(error);
      }
    }

    async function getUsers() {
      try {
        const response = await axios.get(`${apiBaseUrl}/users`);
        users.value = response.data;
      } catch (error) {
        console.error(error);
      }
    }

    async function getUser(no) {
      try {
        const response = await axios.get(`${apiBaseUrl}/users/${no}`);
        user.value = response.data;
      } catch (error) {
        console.error(error);
      }
    }

    return {
      users,
      user,
      userNo,
      createAccessJwt,
      createRefreshJwt,
      removeRefreshJwt,
      getUsers,
      getUser,
    };
  },
};

</script>>