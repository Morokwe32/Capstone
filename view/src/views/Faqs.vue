<template>
  <div class="card-container">
    <div class="faq">
      <h1>Frequently Asked Questions</h1>
      <hr>
      <div v-if="faqList" class="faq-list">
        <ul>
          <li v-for="faq in faqList" :key="faq.id" class="faq-item">
            <h3>{{ faq.question }}</h3>
            <p>{{ faq.answer }}</p>
            <hr>
          </li>
        </ul>
      </div>
      <div v-else>
        <p>Loading FAQs...</p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Faqs',
  data() {
    return {
      faqList: null,
    };
  },
  mounted() {
    this.fetchFaqList();
  },
  methods: {
    fetchFaqList() {
      axios
          .get('http://localhost:8080/api/faqs/all')
          .then((response) => {
            this.faqList = response.data;
          })
          .catch((error) => {
            console.error(error);
          });
    },
  },
};
</script>

<style scoped>
body {
  background: linear-gradient(135deg, #f54d4f, #4452c5); /* Linear Gradient Background */
  margin: 0;
  padding: 0;
  font-family: Arial, sans-serif;
  color: #fdfefe; /* Soft White for global text color */
}

.faq-container {
  width: 80%;
  max-width: 900px;
  margin: 40px auto;
  padding: 30px;
  background-color: #fdfefe; /* Soft White */
  border-radius: 10px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
}

.faq-container h1 {
  margin-bottom: 20px;
  text-align: center;
  color: #4452c5; /* Blue for header */
  font-size: 2.5em;
  font-weight: bold;
}

.faq-container hr {
  border: none;
  border-top: 2px solid #f39c12; /* Gold for hr */
  margin: 20px 0;
}

.faq-list ul {
  padding: 0;
  list-style: none;
}

.faq-item {
  margin-bottom: 30px;
}

.faq-item h3 {
  font-size: 1.8em;
  margin-bottom: 10px;
  color: #4452c5; /* Blue for question headers */
}

.faq-item p {
  font-size: 1.2em;
  color: #555; /* Dark Gray for text */
}

.timestamp {
  font-size: 0.9em;
  color: #999; /* Light Gray for timestamps */
}

button {
  background-color: #4452c5; /* Blue for buttons */
  color: #fdfefe; /* Soft White for button text */
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease, color 0.3s ease;
}

button:hover {
  background-color: #fdfefe; /* Soft White for hover background */
  color: #4452c5; /* Blue for button text on hover */
}
</style>

