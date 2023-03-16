<script>

import { ref } from 'vue';
import axios from 'axios';

export default {
  setup() {
    const users = ref([]);
    const user = ref({});
    const userNo = ref(null);

    const api = axios.create({
      baseURL: 'api/users',
      headers: {
        'Content-Type': 'application/json; charset=utf-8',
      },
    });

    async function registerUser(userData) {
      try {
        await api.post('/users', userData);
      } catch (error) {
        console.error('Error registering user:', error);
      }
    }

    async function deleteUser(userNo) {
      try {
        const response = await api.post(`/${userNo}/deletion`, user.value);
        console.log(response.data);
      } catch (error) {
        console.error('Error deleting user:', error);
      }
    }

    async function updateUser(userData) {
      try {
        const response = await api.post('/set-data', userData);
        console.log(response.data);
      } catch (error) {
        console.error('Error updating user information:', error);
      }
    }

    async function getUser(userNo) {
      try {
        const response = await api.get(`/${userNo}`);
        user.value = response.data;
      } catch (error) {
        console.error('Error fetching user data:', error);
      }
    }

    async function loginUserToken(userData) {
      try {
        const response = await api.post('/login', userData);
        console.log(response.data);
        // Set headers for subsequent requests
        axios.defaults.headers.common['Authorization'] = `Bearer ${response.headers['access-token']}`;
      } catch (error) {
        console.error('Error logging in user:', error);
      }
    }

    async function logoutUser() {
      try {
        const response = await api.post('/logout');
        console.log(response.data);
        // Remove headers for subsequent requests
        delete axios.defaults.headers.common['Authorization'];
      } catch (error) {
        console.error('Error logging out user:', error);
      }
    }

    async function addUserTags(userNo, tagNoList) {
      try {
        await api.post('/tagadd', { userNo, tagNoList });
      } catch (error) {
        console.error('Error adding user tags:', error);
      }
    }

    async function deleteUserTags(userNo, tagNoList) {
      try {
        await api.delete(`/tagdelete/${userNo}`, { data: tagNoList });
      } catch (error) {
        console.error('Error deleting user tags:', error);
      }
    }

    return {
      users,
      user,
      userNo,
      registerUser,
      deleteUser,
      updateUser,
      getUser,
      loginUserToken,
      logoutUser,
      addUserTags,
      deleteUserTags,
    };
  },
};

</script>