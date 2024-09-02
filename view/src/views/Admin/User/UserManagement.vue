<template>
  <div class="content-container">
    <div class="content-header">
      <h1><i class="fas fa-users"></i> User Management</h1>
      <div class="search-bar-container">
        <div class="search-bar">
          <div class="search-input">
            <input v-model="searchQuery" placeholder="Search..." type="text" />
            <button class="read-button button" @click="resetSearch">
              <i class="fas fa-search"></i> Reset
            </button>
          </div>
          <router-link class="add-button button" to="/admin/users/create">
            <i class="fas fa-user"></i> Add User
          </router-link>
        </div>
      </div>
    </div>

    <table>
      <thead>
      <tr>
        <th @click="sortUsers('id')">ID <i class="fas fa-sort"></i></th>
        <th @click="sortUsers('firstName')">First Name <i class="fas fa-sort"></i></th>
        <th @click="sortUsers('lastName')">Last Name <i class="fas fa-sort"></i></th>
        <th @click="sortUsers('email')">Email <i class="fas fa-sort"></i></th>
        <th class="actions-column">Actions</th>
      </tr>
      </thead>
      <tbody v-if="!loading">
      <tr v-for="user in filteredUsers" :key="user.id">
        <td v-if="!user.editing">{{ user.id }}</td>
        <td v-else>
          <input v-model="user.id" disabled type="text" />
        </td>

        <!-- First Name -->
        <td v-if="!user.editing">{{ user.firstName }}</td>
        <td v-else>
          <input v-model="user.firstName" type="text" />
        </td>

        <!-- Last Name -->
        <td v-if="!user.editing">{{ user.lastName }}</td>
        <td v-else>
          <input v-model="user.lastName" type="text" />
        </td>

        <!-- Email -->
        <td v-if="!user.editing">{{ user.email }}</td>
        <td v-else>
          <input v-model="user.email" type="text" />
        </td>

        <!-- Password -->
        <td v-if="!user.editing">
          <input v-model="user.password" type="password" disabled />
        </td>
        <td v-else>
          <input v-model="user.password" type="password" placeholder="Enter new password" />
        </td>

        <!-- Actions -->
        <td>
          <template v-if="!user.editing">
            <button class="delete-button button" @click="deleteUser(user)">
              <i class="fas fa-trash"></i> Delete
            </button>
            <button class="update-button button" @click="editUser(user)">
              <i class="fas fa-edit"></i> Edit
            </button>
          </template>
          <template v-else>
            <button class="update-button button" @click="saveUser(user)">
              <i class="fas fa-save"></i> Save
            </button>
            <button class="delete-button button" @click="cancelEdit(user)">
              <i class="fas fa-times"></i> Cancel
            </button>
          </template>
        </td>
      </tr>
      </tbody>
    </table>

    <div v-if="loading" class="loading">Loading...</div>

    <!-- Confirmation Modal -->
    <div v-if="showConfirmationModal" class="modal-overlay">
      <div class="modal">
        <div class="modal-header">
          <h2>Confirm Delete</h2>
        </div>
        <div class="modal-body">
          <p>Are you sure you want to delete this user?</p>
        </div>
        <div class="modal-footer">
          <button class="confirm-button button" @click="confirmDeleteUser">Yes, Delete</button>
          <button class="cancel-button button" @click="cancelDeleteUser">Cancel</button>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import axios from "axios";

axios.defaults.baseURL = 'http://localhost:8080/api/user';

export default {
  name: "UserManagement",
  data() {
    return {
      users: [],
      sortedUsers: [],
      searchQuery: "",
      loading: false,
      showConfirmationModal: false,
      userToDeleteId: null,
      successModal: {
        show: false,
        message: "",
      },
      failModal: {
        show: false,
        message: "",
      },
    };
  },
  methods: {
    getUsers() {
      this.loading = true;
      axios.get('/api/admin/users/list/all')
          .then((response) => {
            this.users = response.data;
            this.sortedUsers = response.data;
            this.loading = false;
          })
          .catch((error) => {
            this.loading = false;
            this.showFailureModal("Failed to fetch users. Please try again.");
          });
    },
    sortUsers(sortKey) {
      this.sortedUsers = this.sortedUsers.sort((a, b) => {
        if (a[sortKey] < b[sortKey]) return -1;
        if (a[sortKey] > b[sortKey]) return 1;
        return 0;
      });
    },
    deleteUser(user) {
      this.userToDeleteId = user.id; // Correctly set the user ID
      this.showConfirmationModal = true;
    },
    confirmDeleteUser() {
      if (this.userToDeleteId) {
        const userId = this.userToDeleteId;
        this.loading = true;
        axios
            .delete(`http://localhost:8080/api/admin/users/delete/${userId}`)
            .then((response) => {
              this.showSuccessModal("User deleted successfully.");
              this.getUsers(); // Refresh the list
            })
            .catch((error) => {
              this.loading = false;
              this.showFailureModal("Failed to delete user. Please try again.");
            });
      }
      this.userToDeleteId = null;
      this.showConfirmationModal = false;
    },
    cancelDeleteUser() {
      this.userToDeleteId = null;
      this.showConfirmationModal = false;
    },
    editUser(user) {
      user.editing = true; // Enable inline editing
    },
    saveUser(user) {
      this.loading = true;
      axios
          .put(`http://localhost:8080/api/admin/users/update/${user.id}`, {
            firstName: user.firstName,
            lastName: user.lastName,
            email: user.email,
            password: user.password
          })
          .then(() => {
            user.editing = false; // Disable inline editing after saving
            this.showSuccessModal("User updated successfully.");
            this.getUsers(); // Refresh the list
          })
          .catch((error) => {
            this.loading = false;
            this.showFailureModal("Failed to update user. Please try again.");
          });
    },
    cancelEdit(user) {
      user.editing = false;
      this.getUsers(); // Revert changes by refreshing the user list
    },
    resetSearch() {
      this.searchQuery = "";
      this.sortedUsers = this.users;
    },
    showSuccessModal(message) {
      this.successModal = {
        show: true,
        message: message,
      };
    },
    showFailureModal(message) {
      this.failModal = {
        show: true,
        message: message,
      };
    },
  },
  computed: {
    filteredUsers() {
      if (!this.searchQuery) {
        return this.sortedUsers;
      }
      const query = this.searchQuery.toLowerCase();
      return this.sortedUsers.filter((user) => {
        return (
            String(user.id).toLowerCase().includes(query) ||
            (user.userName ? user.userName.toLowerCase().includes(query) : false) ||
            (user.firstName ? user.firstName.toLowerCase().includes(query) : false) ||
            (user.lastName ? user.lastName.toLowerCase().includes(query) : false) ||
            (user.phoneNumber ? user.phoneNumber.toLowerCase().includes(query) : false) ||
            (user.email ? user.email.toLowerCase().includes(query) : false)
        );
      });
    }
  },
  created() {
    this.getUsers();
  }
};
</script>


<style scoped>
.content-container {
  background: linear-gradient(135deg, #f54d4f, #4452c5);
  max-width: 1600px;
  margin: 20px auto;
  height: 450px;
  padding: 40px;
  border-radius: 20px;
  box-shadow: 0 4px 8px rgba(172, 44, 44, 0.1);
}
.content-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.search-bar-container {
  display: flex;
  align-items: center;
}

.search-bar {
  display: flex;
  align-items: center;
}

.search-input {
  display: flex;
  align-items: center;
  margin-right: 20px;
}

.search-input input {
  padding: 5px;
  font-size: 16px;
}

.button {
  padding: 5px 10px;
  margin: 0 5px;
}

.read-button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 12px 20px;
  font-size: 1em;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.add-button {
  background-color: #007bff;
  color: white;
  border: none;
  padding: 12px 20px;
  font-size: 1em;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.update-button {
  background-color: #FFC107;
  color: white;
}

.delete-button {
  background-color: #F44336;
  color: white;
}

.loading {
  text-align: center;
  font-size: 18px;
}

.actions-column {
  width: 150px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

table th, table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: left;
}

table th {
  background-color: #f2f2f2;
}

table td input {
  border: 1px solid #ddd;
  padding: 4px;
}

.table-actions {
  display: flex;
  justify-content: space-between;
}

.table-actions button {
  margin: 0 5px;
}
</style>
