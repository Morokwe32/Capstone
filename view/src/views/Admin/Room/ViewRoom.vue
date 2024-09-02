<template>
  <div class="card-container card-container-admin">
    <div class="form-container">
      <div class="room-profile">
        <h1><i class="fas fa-room"></i> Room Profile: </h1>
        <div class="profile-details" v-if="room">
          <div>
            <p><label>Room Number:</label><span>{{ room.roomNumber }}</span></p>

            <p><label>Room Type:</label><span>{{ room.roomType }}</span></p>

            <p><label>Capacity:</label><span>{{ room.capacity }}</span></p>

            <p><label>Price Group:</label><span>{{ room.priceGroup }}</span></p>

            <p><label>Is Available:</label><span>{{ room.isAvailable }}</span></p>
          </div>
        </div>
        <div v-else>
          <p>Loading room profile...</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
// Set a default base URL for your API
axios.defaults.baseURL = 'http://localhost:8080';

export default {
  name: 'ViewRoom',
  data() {
    return {
      room: null,
    };
  },
  mounted() {
    this.fetchRoomProfile();
  },
  methods: {
    fetchRoomProfile() {
      // Get the room ID from the route parameter
      const roomId = this.$route.params.id;
      axios
          .get(`/api/admin/rooms/read/${roomId}`)
          .then((response) => {
            this.room = response.data;
          })
          .catch((error) => {
            console.log(error);
          });
    },
  },
};
</script>

<style scoped>
.card-container {
  justify-content: center;
}
.room-profile {
  margin-top: 20px;
}

.room-profile h1 {
  margin-bottom: 10px;
}

.profile-details div {
  margin-bottom: 10px;
}

label {
  font-weight: bold;
}

span {
  margin-left: 10px;
}
</style>
