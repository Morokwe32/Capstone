<template>
  <div class="content-container">
    <div class="content-header">
      <h1>
        <i class="fas fa-file-contract"></i> Booking Management
      </h1>
      <div class="search-bar-container">
        <div class="search-bar">
          <div class="search-input">
            <input v-model="searchQuery" placeholder="Search..." type="text" />
            <button @click="resetSearch" class="read-button button">
              <i class="fas fa-search"></i> Reset
            </button>
          </div>
          <router-link to="/admin/bookings/create" class="add-button button">
            <i class="fas fa-contact-book"></i> Add Booking
          </router-link>
        </div>
      </div>
    </div>
    <table>
      <thead>
      <tr>
        <th @click="sortBookings('id')">Booking ID <i class="fas fa-sort"></i></th>
        <th @click="sortBookings('user.userName')">User <i class="fas fa-sort"></i></th>
        <th @click="sortBookings('user.firstName')">First Name <i class="fas fa-sort"></i></th>
        <th @click="sortBookings('user.lastName')">Last Name <i class="fas fa-sort"></i></th>
        <th @click="sortBookings('room.roomType')">Room Type <i class="fas fa-sort"></i></th>
        <th @click="sortBookings('inDate')">Date Booked </th>
        <th @click="sortBookings('outDate')">Date Returned </th>
        <th class="actions-column">Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="booking in filteredBookings" :key="booking.id">
        <td v-if="!booking.editing">{{ booking.id }}</td>
        <td v-else>
          <input type="text" v-model="booking.id" />
        </td>
        <td v-if="!booking.editing">{{ booking.user.userName }}</td>
        <td v-else>
          <input type="text" v-model="booking.user.userName" />
        </td>
        <td v-if="!booking.editing">{{ booking.user.firstName }}</td>
        <td v-else>
          <input type="text" v-model="booking.user.firstName" />
        </td>
        <td v-if="!booking.editing">{{ booking.user.lastName }}</td>
        <td v-else>
          <input type="text" v-model="booking.user.lastName" />
        </td>
        <td v-if="!booking.editing">{{ booking.room.roomType }}</td>
        <td v-else>
          <input type="text" v-model="booking.room.roomType" />
        </td>
        <td v-if="!booking.editing">{{ booking.inDate }}</td>
        <td v-else>
          <input type="text" v-model="booking.inDate" />
        </td>
        <td v-if="!booking.editing">{{ booking.outDate }}</td>
        <td v-else>
          <input type="text" v-model="booking.outDate" />
        </td>
        <td>
          <div v-if="!booking.editing">
            <button @click="editBooking(booking)" class="update-button button">
              <i class="fas fa-edit"></i> Edit
            </button>
            <button @click="deleteBooking(booking)" class="delete-button button">
              <i class="fas fa-trash-alt"></i> Delete
            </button>
          </div>
          <div v-else>
            <button @click="saveBooking(booking)" class="accept-button button">
              <i class="fas fa-save"></i> Save
            </button>
            <button @click="cancelEdit(booking)" class="cancel-button button">
              <i class="fas fa-times"></i> Cancel
            </button>
          </div>
        </td>
      </tr>
      </tbody>
    </table>
    <div v-if="loading" class="loading">Loading...</div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "BookingManagement",
  data() {
    return {
      bookings: [],
      sortedBookingsList: [],
      sortBy: null,
      searchQuery: "",
      loading: false,
      bookingToDelete: null,
    };
  },
  methods: {
    getBookings() {
      this.loading = true;
      axios
          .get(`http://localhost:8080/api/admin/bookings/list/all`)
          .then((response) => {
            console.log("API Response:", response.data); // Debugging
            this.bookings = response.data.map((booking) => ({
              ...booking,
              user: booking.user || {},  // Ensure user details are present
              room: booking.room || {}   // Ensure room details are present
            }));
            this.sortedBookingsList = [...this.bookings];
            this.loading = false;
          })
          .catch((error) => {
            console.error("API Error:", error); // Debugging
            this.loading = false;
            alert("Failed to fetch bookings. Please try again."); // Replace modal with alert
          });
    },
    sortBookings(sortKey) {
      if (this.sortedBookingsList.length === 0) {
        return;
      }

      const currentSortKey = this.sortedBookingsList[0]._sortKey;
      let sortOrder = "asc";

      if (currentSortKey === sortKey && this.sortedBookingsList[0]._sortOrder === "asc") {
        sortOrder = "desc";
      }

      this.sortedBookingsList.sort((a, b) => {
        const valueA = this.getPropertyValue(a, sortKey);
        const valueB = this.getPropertyValue(b, sortKey);

        let comparison = 0;
        if (valueA > valueB) {
          comparison = 1;
        } else if (valueA < valueB) {
          comparison = -1;
        }

        return sortOrder === "asc" ? comparison : -comparison;
      });

      this.sortedBookingsList.forEach((booking) => {
        booking._sortKey = sortKey;
        booking._sortOrder = sortOrder;
      });
    },
    getPropertyValue(object, key) {
      const keys = key.split(".");
      let value = object;
      for (const k of keys) {
        value = value[k];
      }
      return value;
    },
    deleteBooking(booking) {
      if (booking) {
        const bookingId = booking.id;
        this.loading = true;
        axios
            .delete(`http://localhost:8080/api/bookings/delete/${bookingId}`)
            .then(() => {
              alert("Booking deleted successfully."); // Replace modal with alert
              this.getBookings(); // Refresh bookings list
            })
            .catch(() => {
              alert("Failed to delete booking. Please try again."); // Replace modal with alert
            })
            .finally(() => {
              this.loading = false;
              this.bookingToDelete = null;
            });
      }
    },
    editBooking(booking) {
      booking.editing = true;
    },
    saveBooking(booking) {
      this.loading = true;
      axios.put(`http://localhost:8080/api/bookings/update/${booking.id}`, booking)
          .then(() => {
            alert("Booking updated successfully.");
            this.getBookings(); // Refresh bookings list
          })
          .catch((error) => {
            alert("Failed to update booking. Please try again.");
            console.error("Update Error:", error.response ? error.response.data : error.message);
          })
          .finally(() => {
            this.loading = false;
            booking.editing = false;
          });
    },
    cancelEdit(booking) {
      booking.editing = false;
    },
    resetSearch() {
      this.searchQuery = "";
    }
  },
  computed: {
    filteredBookings() {
      const query = this.searchQuery.toLowerCase();
      return this.sortedBookingsList.filter(booking => {
        const userName = (booking.user && booking.user.userName) || '';
        const firstName = (booking.user && booking.user.firstName) || '';
        const lastName = (booking.user && booking.user.lastName) || '';
        const roomType = (booking.room && booking.room.roomType) || '';
        const inDate = booking.inDate || '';
        const outDate = booking.outDate || '';

        return (
            userName.toLowerCase().includes(query) ||
            firstName.toLowerCase().includes(query) ||
            lastName.toLowerCase().includes(query) ||
            roomType.toLowerCase().includes(query) ||
            inDate.toLowerCase().includes(query) ||
            outDate.toLowerCase().includes(query)
        );
      });
    }
  },
  created() {
    this.getBookings();
  }
};
</script>

<style scoped>
/* Global Styles */
.content-container {
  background: linear-gradient(135deg, #f3f4f6 0%, #e5e7eb 100%);
  border-radius: 5px;
  padding: 20px;
  margin: 20px;
}

.content-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.search-bar-container {
  display: flex;
  justify-content: flex-start;
}

.search-bar {
  display: flex;
  align-items: center;
}

.search-input {
  display: flex;
  align-items: center;
}

.search-input input {
  border: 1px solid #ddd;
  padding: 5px 10px;
  border-radius: 4px;
  margin-right: 10px;
}

.search-bar button {
  background: #007bff;
  color: white;
  border: none;
  padding: 5px 10px;
  border-radius: 4px;
  cursor: pointer;
  margin-right: 10px;
}

.add-button {
  background: #28a745;
}

.update-button {
  background: #007bff;
}

.delete-button {
  background: #dc3545;
}

.accept-button {
  background: #28a745;
}

.cancel-button {
  background: #6c757d;
}

.button {
  cursor: pointer;
  border: none;
  color: #fff;
  padding: 5px 10px;
  border-radius: 4px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

thead {
  background-color: #007bff;
  color: #fff;
}

th {
  padding: 10px;
  cursor: pointer;
}

td {
  padding: 10px;
}

.actions-column {
  width: 150px;
}

.loading {
  text-align: center;
  margin: 20px 0;
}
</style>
