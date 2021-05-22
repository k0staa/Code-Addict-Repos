<template>
  <div id="app">
    <img src="./assets/logo.png" />
    <h1>{{ msg }}</h1>
    <h2>API:</h2>
    <ul>
      <li><button @click="fetchNotSecured">Non secured API</button></li>
      <li><button @click="fetchSecured">Secured API</button></li>
    </ul>
    <h3>API response: {{ apiData }}</h3>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "app",
  props: ["keycloak"],
  data() {
    return {
      msg: "Welcome to Your Vue.js App",
      apiData: "",
    };
  },
  methods: {
    fetchSecured() {
      axios.get("http://localhost:8080/secured",{ headers: { Authorization: 'Bearer ' + this.keycloak.token } }).then((response) => {
        this.apiData = response.data;
      });
    },
    fetchNotSecured() {
      axios.get("http://localhost:8080/not-secured").then((response) => {
        this.apiData = response.data;
      });
    },
  },
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

h1,
h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
