<template>
  <div class="card-container card-container-admin">
    <div class="form-container-admin">
      <form @submit.prevent="addUser">
        <h2 class="form-header">Add User</h2>
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
        <div class="form-group">
          <label for="phoneNumber">Phone Number:</label>
          <input id="phoneNumber" v-model="user.phoneNumber" placeholder="Enter phone number" required type="text">
        </div>
        <div class="form-group">
          <label for="picture">Profile Picture:</label>
          <input id="picture" type="file" @change="handleFileUpload">
        </div>
        <div class="button-container">
          <button class="confirm-button button" type="submit"><i class="fas fa-check"></i> Confirm</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from "axios";

// Set a default base URL for your API
axios.defaults.baseURL = 'http://localhost:8080';

// Add an interceptor for every request
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
        firstName: '',
        lastName: '',
        email: '',
        password: '',
        phoneNumber: '',
      },
      picture: null // Store the selected file here
    };
  },
  methods: {
    handleFileUpload(event) {
      this.picture = event.target.files[0]; // Store the selected file
    },
    addUser() {
      const formData = new FormData();

      // Append user data
      formData.append('firstName', this.user.firstName);
      formData.append('lastName', this.user.lastName);
      formData.append('email', this.user.email);
      formData.append('password', this.user.password);
      formData.append('phoneNumber', this.user.phoneNumber);
      formData.append('userName', this.user.email); // Use email as username

      // Append the selected file
      if (this.picture) {
        formData.append("picture", this.picture);
      }

      axios.post('/api/admin/users/create', formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      })
          .then((response) => {
            console.log(response);
            alert('User added successfully');
          })
          .catch((error) => {
            console.error(error);
            alert('Failed to add user. Please try again.');
          });

      this.resetForm();
    },
    resetForm() {
      this.user = {
        firstName: "",
        lastName: "",
        email: "",
        password: "",
        phoneNumber: "",
      };
      this.picture = null;
    }
  }
};
</script>

<style scoped>
.background-container {
  background: linear-gradient(135deg, #f54d4f, #4452c5);
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  padding: 20px;
}


.form-container-admin {
  background: #fff;
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  width: 800px;
  width: 100%;
  align-items: center;
}

.form-header {
  margin-bottom: 20px;
  font-size: 1.5em;
  color: #333;
  text-align: center;
}

.form-group {
  margin-bottom: 15px;
}

.form-group label {
  display: block;
  margin-bottom: 10px;
  font-weight: bold;
}

.form-group input,
.form-group select {
  width: 100%;
  padding: 8px;
  border: 2px solid #ddd;
  border-radius: 4px;
}

.button-container {
  text-align: center;
}

.confirm-button {
  background-color: green;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
}

.confirm-button:hover {
  background-color: #0056b3;
}

.error-message {
  color: #e74c3c;
  margin-top: 15px;
  text-align: center;
}
</style>
