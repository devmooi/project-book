<template>
  <nav>
      <h1><a href="/">book</a></h1>
      <ul>
          <li><i class="far fa-star"></i></li>
          <li><i class="far fa-heart"></i></li>
          <li><i class="far fa-calendar"></i></li>
      </ul>
      <p><i class="fas fa-sign-out-alt"></i></p>
  </nav>
</template>

<script>
export default {

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
        color: #f4f2db;
        text-decoration: none;
        font-size: 1.5rem;
        padding: 15px;
        display: block;
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
</style>