<template>
  <div class="card-container card-container-admin">
    <div class="room-list">
      <h1><i class="fas fa-hotel"></i> Available Rooms</h1>

      <div v-if="rooms.length" class="room-cards">
        <div v-for="room in rooms" :key="room.id" class="room-card">
          <img :src="room.imageUrl || defaultImage" alt="Room Image" class="room-image" />
          <div class="room-info">
            <h2>Room Number: {{ room.roomNumber }}</h2>
            <p><strong>Room Type:</strong> {{ room.roomType }}</p>
            <p><strong>Capacity:</strong> {{ room.capacity }}</p>
            <p><strong>Price Group:</strong> {{ room.priceGroup }}</p>
            <p><strong>Available:</strong> {{ room.isAvailable ? 'Yes' : 'No' }}</p>
            <p><strong>Description:</strong> {{ room.description }}</p>
          </div>
        </div>
      </div>

      <div v-else>
        <p>No rooms available at the moment...</p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      rooms: [], // Store the list of rooms
      defaultImage: 'https://via.placeholder.com/150', // Placeholder image if no image URL is provided
    };
  },
  created() {
    this.fetchRooms(); // Fetch all rooms when the component is created
  },
  methods: {
    async fetchRooms() {
      try {
        const response = await axios.get('/api/admin/rooms/read');
        this.rooms = response.data; // Populate rooms array with the fetched data
      } catch (error) {
        console.error('Error fetching rooms:', error);
      }
    }
  }
};
</script>

<style scoped>
.room-list {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
}

.room-cards {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.room-card {
  background: #fff;
  border-radius: 8px;
  box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.15);
  margin: 15px;
  padding: 20px;
  max-width: 300px;
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.room-image {
  width: 100%;
  height: 200px;
  object-fit: cover;
  border-radius: 8px;
}

.room-info {
  margin-top: 10px;
}

.room-info h2 {
  font-size: 1.5rem;
  margin-bottom: 10px;
}

.room-info p {
  margin: 5px 0;
}
</style>
