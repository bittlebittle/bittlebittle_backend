<script>
import { ref } from 'vue';
import axios from 'axios';

export default {
  setup() {
    const users = ref([]);
    const user = ref({});
    const userNo = ref(null);

    const updateUser = (user) => {
      axios
        .post('/api/admin/set-data', user)
        .then((response) => {
          console.log('User updated:', response.data);
        })
        .catch((error) => {
          console.error('Error updating user:', error);
        });
    };

    const deleteUser = (user) => {
      axios
        .post(`/api/admin/deletion/${user.userNo}`, user)
        .then((response) => {
          console.log('User deleted:', response.data);
        })
        .catch((error) => {
          console.error('Error deleting user:', error);
        });
    };

    const getUsers = () => {
      axios
        .get('/api/admin')
        .then((response) => {
          users.value = response.data;
        })
        .catch((error) => {
          console.error('Error getting users:', error);
        });
    };

    const getUser = (userNo) => {
      axios
        .get(`/api/admin/${userNo}`)
        .then((response) => {
          user.value = response.data;
        })
        .catch((error) => {
          console.error('Error getting user:', error);
        });
    };

    const registerAdminUser = (user) => {
      axios
        .post('/api/admin/register', user)
        .then((response) => {
          console.log('Admin user registered:', response.data);
        })
        .catch((error) => {
          console.error('Error registering admin user:', error);
        });
    };

    const loginUserToken = (user) => {
      axios
        .post('/api/admin/login', user)
        .then((response) => {
          console.log('User logged in:', response.data);
        })
        .catch((error) => {
          console.error('Error logging in user:', error);
        });
    };

    const logoutUser = () => {
      axios
        .post('/api/admin/logout')
        .then((response) => {
          console.log('User logged out:', response.data);
        })
        .catch((error) => {
          console.error('Error logging out user:', error);
        });
    };

    return {
      users,
      user,
      userNo,
      updateUser,
      deleteUser,
      getUsers,
      getUser,
      registerAdminUser,
      loginUserToken,
      logoutUser,
    };
  },
};
</script>
