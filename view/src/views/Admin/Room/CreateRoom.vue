<template>
  <div class="card-container card-container-admin">
    <div class="form-container-admin">
      <form @submit.prevent="addRoom">
        <h2 class="form-header">Add Room</h2>

        <div class="form-group">
          <label for="roomType">Room Type:</label>
          <select id="roomType" v-model="room.roomType" required>
            <option v-for="type in roomTypes" :key="type.id" :value="type.type">
              {{ type.type }}
            </option>
          </select>
        </div>

        <div class="form-group">
          <label for="capacity">Capacity:</label>
          <input id="capacity" v-model="room.capacity" required type="number">
        </div>

        <div class="form-group">
          <label for="priceGroup">Price Group:</label>
          <select id="priceGroup" v-model="room.priceGroup" required>
            <option value="ECONOMY">Economy</option>
            <option value="STANDARD">Standard</option>
            <option value="LUXURY">Luxury</option>
            <option value="PREMIUM">Premium</option>
            <option value="EXOTIC">Exotic</option>
            <option value="SPECIAL">Special</option>
            <option value="OTHER">Other</option>
            <option value="NONE">None</option>
          </select>
        </div>

        <div class="form-group">
          <label for="isAvailable">Is Available:</label>
          <select id="isAvailable" v-model="room.isAvailable" required>
            <option :value="true">Yes</option>
            <option :value="false">No</option>
          </select>
        </div>

        <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
        <div class="button-container">
          <button class="confirm-button button" type="submit"><i class="fas fa-check"></i> Confirm</button>
        </div>
      </form>
    </div>
  </div>
</template>


<script>
import axios from "axios";
import { PriceGroup } from "@/enums/PriceGroup";

axios.defaults.baseURL = 'http://localhost:8080/api/roomtypes/{{id}}';

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
      room: {
        id: null,
        roomType: '',
        capacity: 0,
        priceGroup: PriceGroup.LUXURY,
        isAvailable: true
      },
      roomTypes: [], // Array to hold room types from the backend
      errorMessage: '',
    };
  },
  created() {
    this.fetchRoomTypes();
  },
  methods: {
    async fetchRoomTypes() {
      try {
        const response = await axios.get('/api/roomtypes');
        this.roomTypes = response.data;
      } catch (error) {
        console.error('Error fetching room types:', error);
      }
    },
    async addRoom() {
      this.errorMessage = '';
      const token = localStorage.getItem('token');

      try {
        const response = await axios.post('/api/admin/rooms/create', this.room, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
        console.log('Room added successfully', response.data);
        this.resetForm();
      } catch (error) {
        console.error('Error adding room:', error);
        if (error.response && error.response.status === 400) {
          this.errorMessage = 'Invalid data. Please check the entered values.';
        } else {
          this.errorMessage = 'An error occurred while adding the room.';
        }
      }
    },
    resetForm() {
      this.room = {
        id: null,
        roomType: '',
        capacity: 0,
        priceGroup: '',
        isAvailable: true
      };
    }
  }
};
</script>


<style scoped>
/* General Styling */
body {
  font-family: 'Roboto', sans-serif;
  background-color: #f5f5f5;
  margin: 0;
  padding: 0;
}

/* Navigation Menu */
nav {
  background: rgba(255, 126, 95, 0.9); /* Slightly transparent gradient background */
  background: linear-gradient(135deg, rgba(255, 126, 95, 0.8), rgba(254, 80, 123, 0.8)); /* Gradient with transparency */
  padding: 0.8rem 1.5rem; /* Adjusted padding for a smaller navbar */
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1); /* Adds a slight shadow for a sleek effect */
  position: fixed; /* Fixes the navbar at the top */
  top: 0;
  width: 100%;
  z-index: 1000; /* Ensures the navbar stays on top of other content */
}

nav ul {
  list-style-type: none;
  display: flex;
  justify-content: space-around;
  margin: 0;
  padding: 0;
}

nav ul li {
  margin: 0;
}

nav ul li a {
  color: #fff;
  text-decoration: none;
  font-size: 1rem; /* Smaller font size */
  font-weight: bold;
  background: #4452c5; /* Solid background for buttons */
  padding: 0.6rem 2rem; /* Smaller padding */
  border-radius: 50px; /* Makes the buttons round */
  transition: background-color 0.3s, transform 0.3s, box-shadow 0.3s;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2); /* Adds a shadow to make the buttons stand out */
}

nav ul li a:hover {
  background: #3541a5; /* Darker shade on hover */
  transform: scale(1.05); /* Slight zoom effect on hover */
  box-shadow: 0px 6px 15px rgba(0, 0, 0, 0.3); /* Stronger shadow on hover */
}

/* Form Container */
.card-container-admin {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(135deg, #f54d4f, #4452c5);
  padding: 2rem 30rem;
}

.form-container-admin {
  background: #fff;
  border-radius: 12px;
  box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.15);
  padding: 3rem;
  width: 100%;
  max-width: 550px;
  border: 2px solid #4452c5;
}

.room-form {
  display: flex;
  flex-direction: column;
}

.form-header {
  text-align: center;
  font-size: 2.2rem;
  color: #4452c5;
  margin-bottom: 2rem;
  text-transform: uppercase;
}

.form-group {
  margin-bottom: 1.5rem;
}

label {
  font-weight: 600;
  font-size: 1.2rem;
  color: #1c1c1e;
  display: block;
  margin-bottom: 0.5rem;
}

input[type="text"],
input[type="number"],
select {
  width: 100%;
  padding: 1rem;
  border: 2px solid #ddd;
  border-radius: 8px;
  background: #f9f9f9;
  font-size: 1.1rem;
  transition: all 0.3s ease;
}

input[type="text"]:focus,
input[type="number"]:focus,
select:focus {
  border-color: #4452c5;
  outline: none;
  background: #fff;
}

.error-message {
  color: #ff4d4f;
  margin-top: 5px;
  font-size: 1rem;
}

.button-container {
  display: flex;
  justify-content: center;
  margin-top: 2rem;
}

.confirm-button {
  background-color: #4452c5;
  color: #fff;
  padding: 1rem 2.5rem;
  font-size: 1.2rem;
  font-weight: 600;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.confirm-button:hover {
  background-color: #3541a5;
  transform: translateY(-2px);
}


</style>
