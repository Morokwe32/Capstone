<template>
  <div class="home-container">
    <h1>Welcome to Martoue Hotel</h1>

    <p class="intro-paragraph">
      Discover the perfect blend of luxury, comfort, and sophistication at Martoue Hotel. Nestled in the heart of Capetown, our boutique hotel offers an oasis of tranquility amidst the vibrant city. Whether you're visiting for business or leisure, our exceptional service, beautifully designed rooms, and exquisite amenities ensure a stay that is nothing short of extraordinary. We invite you to experience the warmth and elegance that define Eleve Hotel, where every detail is crafted to elevate your stay.
    </p>
    <div class="image-gallery">
      <img src="@/assets/Images/Defaults/room.jpg" alt="Luxury Room" />
      <img src="@/assets/Images/Defaults/res1.jpg" alt="Exquisite Dining" />
      <img src="@/assets/Images/Defaults/vist1.jpg" alt="Stunning Views" />
    </div>
  </div>
</template>

<script>
export default {
  name: 'Home',
};
</script>

<style scoped>
body {
  background: linear-gradient(135deg, #f54d4f, #4452c5); /* Linear Gradient Background */
  margin: 0;
  padding: 0;
  font-family: Arial, sans-serif;
}

.home-container {
  text-align: center;
  color: #4452c5; /* Blue for header and text */
  padding: 40px;
}

h1 {
  font-size: 2.5em;
  margin-bottom: 20px;
}

nav ul {
  display: flex;
  justify-content: center; /* Center the navigation items */
  flex-wrap: wrap; /* Allow wrapping if the screen is too small */
  padding: 0;
  margin-bottom: 20px; /* Reduced margin to fit all items on one line */
  list-style-type: none;
}

nav ul li {
  margin: 5px 10px; /* Reduced margin for closer spacing */
}

nav ul li a {
  text-decoration: none;
  font-size: 1em; /* Slightly smaller font size */
  color: #4452c5; /* Blue for navigation links */
  padding: 8px 12px; /* Reduced padding for a more compact appearance */
  border-radius: 5px;
  background-color: #fdfefe; /* Soft White for button background */
  transition: background-color 0.3s ease, color 0.3s ease;
}

nav ul li a:hover {
  background-color: #4452c5; /* Blue for hover background */
  color: #fdfefe; /* Soft White for text on hover */
}

.intro-paragraph {
  font-size: 1.2em;
  line-height: 1.6;
  color: #fdfefe; /* Soft White for the paragraph text */
  margin-top: 30px; /* Added margin to separate the paragraph from the menu */
}

.image-gallery {
  display: flex;
  justify-content: center;
  gap: 20px; /* Space between images */
  margin-top: 30px; /* Space above the images */
}

.image-gallery img {
  width: 30%; /* Adjust to fit 3 images side by side */
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* Add a subtle shadow for depth */
}
</style>
