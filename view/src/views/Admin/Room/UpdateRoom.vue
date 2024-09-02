<template>
  <div class="content-container">
    <div class="card-container">
      <h1>Update  Rooms</h1>
      <h2>List of {{ category }} Rooms</h2>
      <table>
        <thead>
        <tr>
          <th @click="sortRooms('id')">ID</th>
          <th @click="sortRooms('make')">Make</th>
          <th @click="sortRooms('model')">Model</th>
          <th @click="sortRooms('year')">Year</th>
          <th @click="sortRooms('category')">Category</th>
          <th @click="sortRooms('priceGroup')">Price Group</th>
          <th @click="sortRooms('licensePlate')">License Plate</th>
          <th>Edit</th>
          <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="room in sortedRooms" :key="room.id">
          <td>{{ room.id }}</td>
          <td>
            <input v-model="room.make" :disabled="!room.editMode" />
          </td>
          <td>
            <input v-model="room.model" :disabled="!room.editMode" />
          </td>
          <td>
            <input v-model="room.year" :disabled="!room.editMode" />
          </td>
          <td>
            <input v-model="room.category" :disabled="!room.editMode" />
          </td>
          <td>
            <select v-model="room.priceGroup" :disabled="!room.editMode" required>
              <option value="ECONOMY">ECONOMY</option>
              <option value="STANDARD">STANDARD</option>
              <option value="LUXURY">LUXURY</option>
              <option value="PREMIUM">PREMIUM</option>
              <option value="EXOTIC">EXOTIC</option>
              <option value="SPECIAL">SPECIAL</option>
              <option value="OTHER">OTHER</option>
              <option value="NONE">NONE</option>
            </select>
          </td>

          <td>
            <input v-model="room.licensePlate" :disabled="!room.editMode" />
          </td>
          <td>
            <button @click="toggleEditMode(room)">
              {{ room.editMode ? 'Save' : 'Edit' }}
            </button>
          </td>
          <td>
            <button @click="deleteRoom(room.id)">Delete</button>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
// Add this line to set a default base URL for your API
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
  name: 'EditRoom',
  data() {
    return {
      rooms: [],
      category: '',
      sortColumn: '', // Current column to sort by
      sortDirection: '', // Current sort direction
    };
  },
  mounted() {
    this.fetchRooms();
  },
  methods: {
    fetchRooms() {
      const category = 'all';
      const token = localStorage.getItem('token');
      axios
          .get(`/api/rooms/${category}`, {
            headers: {
              Authorization: `Bearer ${token}`
            }
          })
          .then((response) => {
            this.rooms = response.data.map((room) => ({
              ...room,
              editMode: false,
            }));
            this.category = category;
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
    deleteRoom(carId) {
      const token = localStorage.getItem('token');
      axios
          .delete(`/api/admin/rooms/delete/${carId}`, {
            headers: {
              Authorization: `Bearer ${token}`
            }
          })
          .then((response) => {
            this.fetchRooms();
            console.log(response);
            console.log('Room deleted');
          })
          .catch((error) => {
            console.log(error);
            console.log('Room not deleted');
          });
    },
    toggleEditMode(room) {
      if (room.editMode) {
        this.updateRoom(room);
      }
      room.editMode = !room.editMode;
    },
    updateRoom(room) {
      const token = localStorage.getItem('token');
      axios
          .put(`/api/admin/rooms/update/${room.id}`, room, {
            headers: {
              Authorization: `Bearer ${token}`
            }
          })
          .then((response) => {
            console.log(response);
            console.log('Room updated');
          })
          .catch((error) => {
            console.log(error);
            console.log('Room not updated');
          });
    },
  },
  computed: {
    sortedRooms() {
      let sortedRooms = [...this.rooms];

      if (this.sortColumn) {
        sortedRooms.sort((a, b) => {
          let valueA = a[this.sortColumn];
          let valueB = b[this.sortColumn];

          if (typeof valueA === 'string') {
            valueA = valueA.toLowerCase();
            valueB = valueB.toLowerCase();
          }

          if (valueA < valueB) {
            return this.sortDirection === 'asc' ? -1 : 1;
          }
          if (valueA > valueB) {
            return this.sortDirection === 'asc' ? 1 : -1;
          }
          return 0;
        });
      }

      return sortedRooms;
    },
  },
};
</script>

<style scoped>
/* Add custom styles for the component */
</style>
