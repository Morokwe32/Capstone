<template>
  <div id="app">
    <header>
      <nav>
        <div class="logo-container">
          <img src="@/assets/Images/Logo/logo.jpg" alt="Logo" class="logo" />
        </div>
        <ul class="nav-links">
          <li><router-link to="/">Home</router-link></li>
          <li><router-link to="/register">Register</router-link></li>
          <li><router-link to="/about-us">About Us</router-link></li>
          <li v-if="isLoggedIn"><router-link to="/bookings">Book</router-link></li>
          <li><router-link to="/contact-us">Contact Us</router-link></li>
          <li><router-link to="/faqs">FAQs</router-link></li>
          <li><router-link to="/help-desk">Help Desk</router-link></li>
          <li><router-link to="/login">Login</router-link></li>
          <li><router-link to="/signout">Signout</router-link></li>
          <li><router-link to="/user-profile">User Profile</router-link></li>
        </ul>
      </nav>
    </header>
    <main>
      <router-view></router-view>
    </main>
    <footer>
      <p>&copy; Martoue Boutique</p>
    </footer>
  </div>
</template>


<script>
export default {
  name: 'App',
  computed: {
    isLoggedIn() {
      // Check if the token is present in localStorage to determine if the user is logged in
      return !!localStorage.getItem('token');
    }
  }
};
</script>

<style>
/* General Styling */
body {
  font-family: 'Roboto', sans-serif;
  background-color: #f5f5f5;
  margin: 0;
  padding: 0;
  padding-top: 3.5rem; /* Adds space for fixed navbar */
}

/* Navigation Menu */
nav {
  background: rgba(255, 126, 95, 0.9); /* Slightly transparent gradient background */
  background: linear-gradient(135deg, rgba(255, 126, 95, 0.8), rgba(254, 80, 123, 0.8)); /* Gradient with transparency */
  padding: 0.5rem 1rem; /* Adjusted padding for a smaller navbar */
  box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1); /* Adds a slight shadow for a sleek effect */
  position: fixed; /* Fixes the navbar at the top */
  top: 0;
  width: 100%;
  z-index: 1000; /* Ensures the navbar stays on top of other content */
  display: flex;
  align-items: center; /* Centers items vertically */
}

/* Logo Container */
.logo-container {
  flex-shrink: 0; /* Prevents the logo from shrinking */
  margin-right: 2rem; /* Space between logo and menu items */
}

.logo {
  height: 40px; /* Adjust height as needed */
}

/* Navigation Links */
.nav-links {
  list-style-type: none;
  display: flex;
  margin: 0;
  padding: 0;
  flex-grow: 1; /* Allows the ul to grow and take up available space */
  align-items: center; /* Vertically align items */
}

.nav-links li {
  margin: 0 0.5rem; /* Adjusted spacing between links */
}

.nav-links li a {
  color: #fff;
  text-decoration: none;
  font-size: 0.8rem; /* Smaller font size */
  font-weight: bold;
  background: #4452c5; /* Solid background for buttons */
  padding: 0.3rem 1rem; /* Smaller padding */
  border-radius: 25px; /* Slightly rounder edges */
  transition: background-color 0.3s, transform 0.3s, box-shadow 0.3s;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.2); /* Slightly smaller shadow */
}

.nav-links li a:hover {
  background: #3541a5; /* Darker shade on hover */
  transform: scale(1.05); /* Slight zoom effect on hover */
  box-shadow: 0px 6px 12px rgba(0, 0, 0, 0.3); /* Stronger shadow on hover */
}

/* Additional Styles */
header nav ul {
  display: flex;
  list-style-type: none;
  padding: 0;
  margin: 0;
}

header nav ul li {
  margin-right: 15px; /* Adjusted spacing between links */
}

header nav ul li a {
  text-decoration: none;
  color: #fff; /* Adjust color as needed */
}

header nav ul li a:hover {
  text-decoration: underline; /* Optional: Add underline on hover */
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



