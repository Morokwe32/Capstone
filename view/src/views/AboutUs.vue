<template>
  <div class="card-container">
    <div class="form-container">
      <div>
        <div class="form-header">
          <h2>About Us</h2>
        </div>
        <hr>
        <div class="profile-info" v-if="about">
          <p><strong><i class="fa fa-map-marker"></i> Address:</strong> {{ about.address }}</p>
          <p><strong><i class="fa fa-calendar"></i> Office Hours:</strong> {{ about.officeHours }}</p>
          <p><strong><i class="fas fa-envelope"></i> Email:</strong> {{ about.email }}</p>
          <p><strong><i class="fas fa-phone"></i> Telephone Number:</strong> {{ about.telephone }}</p>
          <p><strong><i class="fa fa-mobile" aria-hidden="true"></i> WhatsApp Number:</strong> {{ about.whatsApp }}</p>
        </div>
        <div v-else>
          Loading...
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'AboutUs',
  data() {
    return {
      about: null,
    };
  },
  mounted() {
    this.fetchAboutUs();
    console.log('About Us component mounted');
  },
  methods: {
    fetchAboutUs() {
      const aboutId = this.$route.params.id || 1; // Default to ID 1 if not provided

      console.log('About ID:', aboutId);

      axios
          .get(`http://localhost:8080/api/aboutUs/read/${aboutId}`)
          .then(response => {
            console.log(response.data);
            this.about = response.data;
          })
          .catch(error => {
            console.error(error);
          });
    },
    navigateToAboutUs() {
      // Use this.$router.push to navigate to About Us with id 1
      this.$router.push({ name: 'AboutUs', params: { id: 2 } });
    },
  },
};
</script>

<style>
.card-container {
  width: 650px;
  margin: 0 auto;
}

.form-header {
  text-align: center;
  margin-bottom: 20px;
}

.profile-info p {
  font-size: 1.2em;
  color: #555;
}

.form-container {
  background-color: #fff;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
</style>
