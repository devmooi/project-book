<template>
  <nav>
      <h1><a href="/">book</a></h1>
      <ul>
          <li><a href="/bookList"><i class="far fa-heart"></i></a></li>
          <li><a href="/ideaNote"><i class="far fa-star"></i></a></li>
      </ul>
      <p><i class="fas fa-sign-out-alt" @click="logout"></i></p>
  </nav>
</template>

<script>
export default {
    name: 'Navigation',
    methods: {
        logout() {
            localStorage.removeItem('bookToken');
            location.href="/login.html";
        }
    }
}
</script>

<style scoped>
    nav {
        background: #134775;
        position: fixed;
        height: 100vh;
        color: #f4f2db;
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }
    h1 a {
        text-decoration: none;
        font-size: 1.5rem;
        padding: 15px;
        display: block;
    }
    a {
        color: #f4f2db;
    }
    ul {
        list-style: none;
        text-align: center;
    }
    ul li {
        margin-bottom: 30px;
        font-size: 1.1rem;
        cursor: pointer;
    }
    p {
        text-align: center;
        padding: 20px;
        cursor: pointer;
    }
    i {
        padding: 5px;
        transition: 0.5s;
        border-radius: 5px;
    }
    i:hover {
        background: #f2f2db;
        color:#134775;
    }
</style>