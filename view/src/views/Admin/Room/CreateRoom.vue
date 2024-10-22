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

        <div>
          <label>
            Picture:
            <input
                type="file"
                @change="setPicture($event.target.files[0])"
            />
          </label>
        </div>


        <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
        <div v-if="successMessage" class="success-message">{{ successMessage }}</div>

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
      roomTypes: [],
      errorMessage: '',
      successMessage: '' // For displaying success message
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
      this.successMessage = ''; // Reset success message before new submission

      try {
        const response = await axios.post('/api/admin/rooms/create', this.room);
        console.log('Room added successfully', response.data);

        // Display success message
        this.successMessage = 'Successfully added a room!';

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
/* Add success message styling */
.success-message {
  color: #28a745;
  font-size: 1rem;
  margin-top: 10px;
}

/* Existing styles */
</style>
