<template>
  <div class="card-container">
    <div class="form-container">
      <div>
        <div class="form-header">
          <h2><i class="fas fa-user"></i> Welcome to Your Profile</h2>
        </div>
        <hr>
        <div class="profile-info">
          <div class="profile-image">
            <img v-if="user.profileImageUrl" :src="user.profileImageUrl" alt="Profile Image" class="avatar">
            <img v-else src="@/assets/Images/Defaults/group.png" alt="Placeholder Image" class="avatar">
          </div>
          <hr>
          <p><strong><i class="fas fa-envelope"></i> Email:</strong> {{ user.email }}</p>
          <p><strong><i class="fas fa-user"></i> First Name:</strong> {{ user.firstName }}</p>
          <p><strong><i class="fas fa-user"></i> Last Name:</strong> {{ user.lastName }}</p>
          <hr>
          <div class="button-container">
            <button @click="editProfile" class="update-button button"><i class="fa fa-pencil"></i> Edit Profile</button>
          </div>
        </div>
      </div>
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
      user: {}
    };
  },

  mounted() {
    const token = localStorage.getItem('token');
    const userId = 1; // Example userId, replace with actual userId

    if (token) {
      axios
          .get(`http://localhost:8080/api/user/profile/${userId}`, {
            headers: {
              Authorization: `Bearer ${token}`
            }
          })
          .then(response => {
            this.user = response.data;
          })
          .catch(error => {
            console.error('Error fetching user data:', error);
          });
    } else {
      console.error('Token not found');
    }
  },

  methods: {
    editProfile() {
      this.$router.push("/edit-profile");
    }
  }
};
</script>

<style scoped>
.card-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(to bottom right, #f06292, #5e35b1);
  color: #fff;
}

.form-container {
  background: rgba(255, 255, 255, 0.1);
  padding: 20px;
  border-radius: 10px;
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3);
}

.form-header h2 {
  margin: 0;
  font-weight: bold;
  text-align: center;
}

.profile-info {
  margin-top: 20px;
}

.avatar {
  width: 100px;
  height: 100px;
  object-fit: cover;
  border-radius: 50%;
}

.profile-image {
  text-align: center;
}

.button-container {
  text-align: center;
  margin-top: 20px;
}

.update-button {
  background-color: #3f51b5;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-weight: bold;
}

.update-button:hover {
  background-color: #283593;
}
</style>
