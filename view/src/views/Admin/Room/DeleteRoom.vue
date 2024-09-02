<template>
  <div class="content-container">
    <div class="card-container">
      <h1>List of {{ category }} Rooms</h1>
      <table>
        <thead>
        <tr>
          <th @click="sortRooms('id')">ID</th>
          <th @click="sortRooms('roomType')">Room Type</th>
          <th @click="sortRooms('capacity')">Capacity</th>
          <th @click="sortRooms('priceGroup')">Price Group</th>
          <th @click="sortRooms('isAvailable')">Available</th>
          <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="room in sortedRooms" :key="room.id">
          <td>{{ room.id }}</td>
          <td>{{ room.roomType }}</td>
          <td>{{ room.capacity }}</td>
          <td>{{ room.priceGroup }}</td>
          <td>{{ room.isAvailable ? 'Yes' : 'No' }}</td>
          <td><button @click="deleteRoom(room.id)">Delete</button></td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

// Set a default base URL for your API
axios.defaults.baseURL = 'http://localhost:8080';

export default {
  name: 'RoomManagement',
  data() {
    return {
      rooms: [],
      category: 'all', // Setting default category to 'all'
      sortColumn: '', // Current column to sort by
      sortDirection: '', // Current sort direction
    };
  },
  mounted() {
    this.fetchRooms();
  },
  methods: {
    fetchRooms() {
      const token = localStorage.getItem('token');
      axios
          .get(`/api/rooms/${this.category}`, {
            headers: {
              Authorization: `Bearer ${token}`
            }
          })
          .then((response) => {
            this.rooms = response.data;
          })
          .catch((error) => {
            console.log(error);
          });
    },
    sortRooms(column) {
      if (this.sortColumn === column) {
        this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
      } else {
        this.sortColumn = column;
        this.sortDirection = 'asc';
      }
    },
    deleteRoom(roomId) {
      const token = localStorage.getItem('token');
      axios
          .delete(`/api/admin/rooms/delete/${roomId}`, {
            headers: {
              Authorization: `Bearer ${token}`
            }
          })
          .then((response) => {
            this.fetchRooms(); // Refresh the room list after deletion
            console.log('Room deleted', response);
          })
          .catch((error) => {
            console.log('Room not deleted', error);
          });
    },
  },
  computed: {
    sortedRooms() {
      if (this.sortColumn && this.sortDirection) {
        return this.rooms.sort((a, b) => {
          const aValue = a[this.sortColumn];
          const bValue = b[this.sortColumn];

          if (typeof aValue === 'string' && typeof bValue === 'string') {
            return this.sortDirection === 'asc'
                ? aValue.localeCompare(bValue)
                : bValue.localeCompare(aValue);
          } else {
            return this.sortDirection === 'asc' ? aValue - bValue : bValue - aValue;
          }
        });
      }
      return this.rooms;
    },
  },
};
</script>


<style scoped>
/* Add custom styles for the component */
</style>
