<template>
  <div class="content-container">
    <div class="content-header">
      <h1><i class="fas fa-door-open"></i> Room Management</h1>
      <div class="search-bar-container">
        <div class="search-bar">
          <div class="search-input">
            <input v-model="searchQuery" placeholder="Search..." type="text" />
            <button @click="resetSearch" class="button reset-button">
              <i class="fas fa-search"></i> Reset
            </button>
          </div>
          <router-link to="/admin/rooms/create" class="add-room-button button">
            <i class="fas fa-room"> </i> Add Room
          </router-link>
          <router-link to="/admin/rooms/update" class="delete-room-button button">
            <i class="fas fa-room"> </i> Update Room
          </router-link>
        </div>
      </div>
    </div>

    <table>
      <thead>
      <tr>
        <th @click="sortRooms('id')">ID <i class="fas fa-sort"></i></th>
        <th @click="sortRooms('roomType')">Room Type <i class="fas fa-sort"></i></th>
        <th @click="sortRooms('capacity')">Capacity <i class="fas fa-sort"></i></th>
        <th @click="sortRooms('priceGroup')">Price Group <i class="fas fa-sort"></i></th>
        <th @click="sortRooms('isAvailable')">Available <i class="fas fa-sort"></i></th>
        <th class="actions-column">Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="room in filteredRooms" :key="room.id">
        <td v-if="!room.editing">{{ room.id }}</td>
        <td v-else>
          <input type="text" v-model="room.id" disabled>
        </td>
        <td v-if="!room.editing">{{ room.roomType }}</td>
        <td v-else>
          <input type="text" v-model="room.roomType">
        </td>
        <td v-if="!room.editing">{{ room.capacity }}</td>
        <td v-else>
          <input type="number" v-model="room.capacity">
        </td>
        <td v-if="!room.editing">{{ room.priceGroup }}</td>
        <td v-else>
          <input type="text" v-model="room.priceGroup">
        </td>
        <td v-if="!room.editing">
          <input type="checkbox" v-model="room.isAvailable" :disabled="room.editing">
        </td>
        <td v-else>
          <input type="checkbox" v-model="room.isAvailable">
        </td>
        <td>
          <template v-if="!room.editing">
            <button @click="deleteRoom(room.id)" class="button delete-button">
              <i class="fas fa-trash"></i> Delete
            </button>
            <button @click="editRoom(room)" class="button update-button">
              <i class="fas fa-edit"></i> Edit
            </button>
            <button @click="openRoomView(room.id)" class="button read-button">
              <i class="fas fa-eye"></i> View
            </button>
          </template>
          <template v-else>
            <button @click="saveRoom(room)" class="button save-button">
              <i class="fas fa-save"></i> Save
            </button>
            <button @click="cancelEdit(room)" class="button cancel-button">
              <i class="fas fa-times"></i> Cancel
            </button>
          </template>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.baseURL = 'http://localhost:8080'; // Update the base URL as needed

export default {
  data() {
    return {
      rooms: [],
      loading: false,
      sortBy: "",
      searchQuery: "",
      showConfirmationModal: false,
      roomToDelete: null,
      successMessage: "",
      failureMessage: "",
    };
  },
  computed: {
    sortedRooms() {
      if (this.sortBy) {
        return [...this.rooms].sort((a, b) => (a[this.sortBy] > b[this.sortBy] ? 1 : -1));
      }
      return this.rooms;
    },
    filteredRooms() {
      const query = this.searchQuery.toLowerCase();
      return this.sortedRooms.filter(room => {
        return Object.values(room).some(val =>
            typeof val === 'string' && val.toLowerCase().includes(query)
        );
      });
    },
  },
  methods: {
    fetchRooms() {
      this.loading = true;
      axios.get("/api/admin/rooms/all")
          .then(response => {
            console.log(response.data); // Inspect the structure
            this.rooms = response.data.map(room => ({
              ...room,
              isAvailable: Boolean(room.isAvailable),
            }));
          })
          .catch(error => {
            console.error(error);
            this.failureMessage = "Failed to fetch rooms. Please try again.";
          })
          .finally(() => {
            this.loading = false;
          });
    },
    async deleteRoom(roomId) {
      try {
        if (!confirm(`Are you sure you want to delete room with ID: ${roomId}?`)) {
          return;
        }

        const response = await axios.delete(`http://localhost:8080/api/admin/rooms/delete/${roomId}`);

        if (response.status === 200 && response.data) {
          alert(`Room with ID: ${roomId} was successfully deleted.`);
          this.fetchRooms();
        } else {
          alert(`Failed to delete room with ID: ${roomId}.`);
        }
      } catch (error) {
        console.error("Delete failed:", error.response);
        if (error.response && error.response.status === 404) {
          alert(`Room with ID: ${roomId} not found.`);
        } else {
          alert(`An error occurred while trying to delete room with ID: ${roomId}.`);
        }
      }
    },
    cancelDelete() {
      this.showConfirmationModal = false;
      this.roomToDelete = null;
    },
    confirmDelete() {
      if (this.roomToDelete) {
        this.loading = true;
        axios.delete(`/api/admin/rooms/delete/${this.roomToDelete.id}`)
            .then(() => {
              this.fetchRooms();
              this.successMessage = `Room ID: ${this.roomToDelete.id} was deleted successfully.`;
            })
            .catch(error => {
              console.error(error);
              this.failureMessage = "Failed to delete room. Please try again.";
            })
            .finally(() => {
              this.loading = false;
              this.showConfirmationModal = false;
              this.roomToDelete = null;
            });
      }
    },
    sortRooms(sortBy) {
      this.sortBy = sortBy;
    },
    resetSearch() {
      this.searchQuery = "";
    },
    editRoom(room) {
      room.editing = true;
    },
    saveRoom(room) {
      this.pushUpdatedRoom(room);
      room.editing = false;
      this.loading = true;
    },
    pushUpdatedRoom(room) {
      axios.put(`/api/admin/rooms/update/${room.id}`, room)
          .then(() => {
            this.successMessage = `Room ID: ${room.id} was updated successfully.`;
          })
          .catch(error => {
            console.error(error);
            this.failureMessage = `Room ID: ${room.id} was not updated successfully.`;
          })
          .finally(() => {
            this.loading = false;
          });
    },
    cancelEdit(room) {
      room.editing = false;
    },
  },
  created() {
    this.fetchRooms();
  },
};
</script>

<style scoped>
.content-container {
  background: linear-gradient(135deg, #f54d4f, #4452c5);
  padding: 15rem;
  border-radius: 10px;
  box-shadow: 0px 4px 15px rgba(0, 0, 0, 0.1);
}

.content-header {
  text-align: center;
  margin-bottom: 20px;
  color: #fff;
}

.search-bar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 20px;
}

.search-input {
  display: flex;
  align-items: center;
  background: rgba(255, 255, 255, 0.8);
  padding: 5px;
  border-radius: 25px;
}

.search-input input {
  margin-right: 10px;
  border: none;
  border-radius: 25px;
  padding: 0.5rem 1rem;
  font-size: 1rem;
  outline: none;
}

button {
  cursor: pointer;
  background: #4452c5;
  color: #fff;
  border: none;
  padding: 0.7rem 1.5rem;
  border-radius: 25px;
  transition: background-color 0.3s, transform 0.3s, box-shadow 0.3s;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2);
}

button:hover {
  background: #3541a5;
  transform: scale(1.05);
  box-shadow: 0px 6px 15px rgba(0, 0, 0, 0.3);
}

.add-button {
  background: #28a745;
}

.add-button:hover {
  background: #218838;
}

.add-room-button {
  background: #f54d4f;
  color: #fff;
  font-weight: bold;
  padding: 0.8rem 1.5rem;
  border-radius: 50px;
  text-decoration: none;
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2); /* Shadow for the bubbly effect */
  transition: background-color 0.3s, transform 0.3s, box-shadow 0.3s;
}

.add-room-button:hover {
  background: #e33033; /* Darker shade on hover */
  transform: scale(1.1); /* Slight zoom effect on hover */
  box-shadow: 0px 6px 15px rgba(0, 0, 0, 0.3); /* Stronger shadow on hover */
}

.add-room-button i {
  margin-right: 8px; /* Space between the icon and text */
}

.button {
  cursor: pointer;
}

.reset-button {
  background: #f54d4f;
}

.reset-button:hover {
  background: #e33033;
}

.actions-column {
  width: 200px;
}

table {
  width: 100%;
  border-collapse: collapse;
  background: rgba(255, 255, 255, 0.9);
  border-radius: 10px;
  overflow: hidden;
}

table th, table td {
  padding: 15px;
  border: 1px solid #ddd;
  text-align: left;
  color: #333;
}

table th {
  background-color: #4452c5;
  color: #fff;
  cursor: pointer;
  user-select: none;
  position: relative;
}

table th:hover {
  background-color: #3541a5;
}

table th i {
  margin-left: 5px;
}

table td {
  background-color: #f9f9f9;
}

table tr:nth-child(even) td {
  background-color: #f1f1f1;
}

.table-row {
  transition: background-color 0.3s;
}

.table-row:hover {
  background-color: #e0e0e0;
}

button.delete-button {
  background: #dc3545;
}

button.delete-button:hover {
  background: #c82333;
}

button.update-button {
  background: #17a2b8;
}

button.update-button:hover {
  background: #138496;
}

button.read-button {
  background: #007bff;
}

button.read-button:hover {
  background: #0056b3;
}

button.save-button {
  background: #28a745;
}

button.save-button:hover {
  background: #218838;
}

button.cancel-button {
  background: #ffc107;
}

button.cancel-button:hover {
  background: #e0a800;
}

button:disabled {
  background: #ccc;
  cursor: not-allowed;
}
</style>
