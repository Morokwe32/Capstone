<template>
  <div class="card-container card-container-admin">
    <div class="form-container-admin">
      <form @submit.prevent="updateUser">
        <h2 class="form-header">Update User</h2>
        <h3 class="form-subheader">User ID: {{ user.id }}</h3>

        <div class="form-group">
          <label for="firstName">First Name:</label>
          <input id="firstName" v-model="user.firstName" placeholder="Enter first name" required type="text">
        </div>
        <div class="form-group">
          <label for="lastName">Last Name:</label>
          <input id="lastName" v-model="user.lastName" placeholder="Enter last name" required type="text">
        </div>
        <div class="form-group">
          <label for="email">Email:</label>
          <input id="email" v-model="user.email" placeholder="Enter email" required type="email">
        </div>
        <div class="form-group">
          <label for="password">Password:</label>
          <input id="password" v-model="user.password" placeholder="Enter password" required type="password">
        </div>

        <div class="button-container">
          <button class="confirm-button button" type="submit">
            <i class="fas fa-check"></i> Update
          </button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

axios.defaults.baseURL = 'http://localhost:8080';

axios.interceptors.request.use(
    config => {
      const token = localStorage.getItem('token');
      if (token) {
        config.headers['Authorization'] = `Bearer ${token}`;
      }
      return config;
    },
    error => {
      return Promise.reject(error);
    }
);

export default {
  data() {
    return {
      user: {
        id: this.$route.params.id,
        firstName: "",
        lastName: "",
        email: "",
        password: "",
      },
      loading: false,
      errorMessage: '',
    };
  },
  mounted() {
    this.fetchUserProfile();
  },
  methods: {
    updateUser() {
      this.loading = true;
      const token = localStorage.getItem('token');
      axios
          .put(`/api/admin/users/update/${this.user.id}`, {
            firstName: this.user.firstName,
            lastName: this.user.lastName,
            email: this.user.email,
            password: this.user.password
          }, {
            headers: {
              Authorization: `Bearer ${token}`
            }
          })
          .then(response => {
            console.log(response);
            this.loading = false;
            this.$router.push({ name: 'UserManagement' }); // Redirect to UserManagement.vue
          })
          .catch(error => {
            console.log(error);
            this.loading = false;
            this.errorMessage = 'Failed to update user. Please try again.';
          });
    },
    fetchUserProfile() {
      this.loading = true;
      const userId = this.$route.params.id;
      const token = localStorage.getItem('token');
      axios
          .get(`/api/admin/users/read/${userId}`, {
            headers: {
              Authorization: `Bearer ${token}`
            }
          })
          .then(response => {
            const userData = response.data;
            this.user.id = userData.id;
            this.user.firstName = userData.firstName;
            this.user.lastName = userData.lastName;
            this.user.email = userData.email;
            this.user.password = userData.password;
            this.loading = false;
          })
          .catch(error => {
            console.log(error);
            this.loading = false;
            this.errorMessage = 'Failed to fetch user profile. Please try again.';
          });
    },
    resetForm() {
      this.user = {
        id: "",
        firstName: "",
        lastName: "",
        email: "",
        password: "",
      };
    },
  },
};
</script>

<style scoped>
/* Your styles here */
</style>
