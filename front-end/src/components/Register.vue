<template>
    <form @submit.prevent="insertUser">
        <label>Name <input type="text" placeholder="Name" required v-model="name"></label>
        <label>Email <input type="email" placeholder="Email" required v-model="email"></label>
        <label>Password <input type="password" placeholder="Password" required v-model="password"></label>
        <button>Sign Up</button>
    </form>  
</template>

<script>
import axios from "axios";

export default {
    name: 'Register',
    data() {
        return {
            name:'',
            email:'',
            password:''
        }
    },
    methods: {
        insertUser() {
            axios
                .post('http://localhost:7777/api/bookUser', {
                    userName: this.name,
                    userEmail: this.email,
                    userPass: this.password
                })
                .then(response => {
                    alert('회원가입 완료');
                    location.href="login.html";
                })
                .catch(error => {
                    alert(error.response.data.error);
                })
        }
    }
}
</script>

<style>

</style>