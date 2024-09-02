<template>
  <div class="card-container">
    <div class="help-desk">
      <h1>Help Desk</h1>
      <div class="filter-container">
        <select v-model="selectedCategory" class="filter-dropdown">
          <option value="All">Show All</option>
          <option v-for="category in categories" :key="category" :value="category">{{ category }}</option>
        </select>
<!--        <button class="faq-button button" @click="openFAQPage">FAQ</button>-->
      </div>
      <hr>
      <div v-if="filteredHelpDesks && filteredHelpDesks.length > 0">
        <div v-for="helpDesk in filteredHelpDesks" :key="helpDesk.id">
          <h3>{{ helpDesk.title }}</h3>
          <p>{{ helpDesk.content }}</p>
          <hr>
        </div>
      </div>
      <div v-else>
        <p>Loading help desk content...</p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'HelpDesk',
  data() {
    return {
      helpDesks: null,
      originalHelpDesks: null,
      selectedCategory: 'All',
      categories: []
    };
  },
  computed: {
    filteredHelpDesks() {
      if (this.selectedCategory === 'All') {
        return this.helpDesks;
      } else {
        return this.helpDesks.filter(entry => entry.category === this.selectedCategory);
      }
    }
  },
  mounted() {
    this.fetchHelpDesks();
  },
  methods: {
    fetchHelpDesks() {
      axios
          .get('http://localhost:8080/api/help-desk/get-all')
          .then((response) => {
            this.helpDesks = response.data;
            this.originalHelpDesks = response.data;
            this.categories = [...new Set(response.data.map(entry => entry.category))];
          })
          .catch((error) => {
            console.error(error);
          });
    },
    // openFAQPage() {
    //   this.$router.push('/faq');
    // }
  }
};
</script>


<style scoped>
body {
  background: linear-gradient(135deg, #f54d4f, #4452c5); /* Linear Gradient Background */
}

.help-desk-container {
  width: 800px;
  margin: 0 auto;
  padding: 30px;
  background-color: #fdfefe; /* Soft White */
  border-radius: 10px;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
}

.help-desk h1 {
  text-align: center;
  color: #4452c5; /* Blue for headers */
  font-size: 2.5em;
  margin-bottom: 20px;
}

.help-desk hr {
  border: none;
  border-top: 2px solid #f39c12; /* Gold for hr */
  margin: 20px 0;
}

.help-desk-list ul {
  padding: 0;
  list-style: none;
}

.help-desk-item {
  margin-bottom: 30px;
}

.help-desk-item h3, .help-desk-item h4 {
  color: #4452c5; /* Blue for headers */
}

.help-desk-item p {
  color: #555; /* Dark Gray for content text */
  font-size: 1.1em;
}

.back-button {
  display: block;
  text-align: center;
  margin-top: 20px;
  padding: 10px 20px;
  background-color: #4452c5; /* Blue for button */
  color: #fdfefe; /* Soft White for text */
  border-radius: 5px;
  text-decoration: none;
  font-weight: bold;
}

.back-button:hover {
  background-color: #354a98; /* Darker shade of blue for hover */
}
</style>





