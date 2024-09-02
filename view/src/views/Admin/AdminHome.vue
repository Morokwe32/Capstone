<template>
  <div class="task-bar">
    <button class="management-button" @click="navigateTo('/admin/user-management')">
      <img src="@/assets/Images/Logo/group.png" alt="User Management" />
    </button>
    <button class="management-button" @click="navigateTo('/admin/booking-management')">
      <img src="@/assets/Images/Logo/booking.png" alt="Booking Management" />
    </button>
    <button class="management-button" @click="navigateTo('/admin/room-management')">
      <img src="@/assets/Images/Logo/double-bed.png" alt="Room Management" />
    </button>
  </div>
</template>



<script>
export default {
  name: 'AdminManagementButtons',
  methods: {
    navigateTo(path) {
      this.$router.push(path).catch(err => {
        console.error(err);
      });
    }
  }
}
</script>



<style scoped>
.task-bar {
  display: flex;
  justify-content: center; /* Center the icons horizontally */
  background-color: #333; /* Dark background for the task bar */
  padding: 10px;
}

.management-button {
  all: unset;
  cursor: pointer;
  margin-right: 20px; /* Space between buttons */
  padding: 15px;
}

.management-button img {
  width: 300px; /* Smaller width for the icons */
  height: 300px; /* Smaller height for the icons */
  object-fit: contain; /* Maintain the aspect ratio of the images */
}

.management-button:last-child {
  margin-right: 0; /* Remove margin on the last button */
}

/* Optional: Add hover effect */
.management-button:hover img {
  transform: scale(1.1); /* Slightly increase the size on hover */
}
</style>


