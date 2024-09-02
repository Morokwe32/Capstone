<template>
  <div class="login-container">
    <div class="login-form">
      <h1>Login</h1>
      <form @submit.prevent="loginUser">
        <label for="username">Username:</label>
        <input v-model="user.username" id="username" type="text" required />

        <label for="password">Password:</label>
        <input v-model="user.password" id="password" type="password" required />

        <button type="submit">Login</button>
      </form>
      <p v-if="errorMessage" class="error-message">{{ errorMessage }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Login',
  data() {
    return {
      user: {
        username: '',
        password: ''
      },
      errorMessage: ''
    };
  },
  methods: {
    async loginUser() {
      try {
        if (this.user.username === 'admin' && this.user.password === 'admin') {
          alert('Admin logged in successfully');
          localStorage.setItem('username', 'admin');
          localStorage.setItem('token', 'admin');
          this.$router.push('/admin-home');
        } else {
          const response = await axios.post('http://localhost:8080/api/user/login', this.user);
          const { userId, token } = response.data;

          console.log('Token received:', token); // Log the token to verify
          console.log('User ID received:', userId); // Log the userId to verify

          localStorage.setItem('username', this.user.username);
          localStorage.setItem('token', token); // Ensure this is the actual token
          localStorage.setItem('userId', userId); // Store the userId

          alert('User logged in successfully');
          this.$router.push(`/`);
        }
      } catch (error) {
        console.error('Error logging in:', error);
        this.errorMessage = 'Invalid username or password';
      }
    }

  }

};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(to bottom right, #f06292, #5e35b1);
  color: #fff;
}

.login-form {
  background: rgba(255, 255, 255, 0.1);
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3);
  width: 300px;
  text-align: center;
}

h1 {
  margin-bottom: 20px;
}

label {
  display: block;
  text-align: left;
  margin-bottom: 5px;
  font-weight: bold;
}

input {
  width: 100%;
  padding: 10px;
  margin-bottom: 15px;
  border-radius: 5px;
  border: none;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #3f51b5;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-weight: bold;
}

button:hover {
  background-color: #283593;
}

.error-message {
  margin-top: 10px;
  color: #ff6f61;
  font-weight: bold;
}

/*Currently working*/
</style>
