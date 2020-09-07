<template>
    <div>
        <Navigation></Navigation>
        <section>
            <div id="main">
                <div id="search-keyword">
                    <label><i class="fas fa-search"></i>
                    <input type="search" placeholder="Search..." @keyup.enter="search" v-model="keyword"></label>
                </div>

                <div v-if="books!=null">
                    <div id="search-result" v-for="book in books" v-bind:key="book">
                        <div id="search-result-image">
                            <img :src="book.image">
                            <span @click.prevent="insertBook(book)">읽을 책 추가</span>
                        </div>
                        <div id="search-result-text">
                            <h2 v-html="book.title"></h2>
                            <p>
                                <span v-html="book.author"></span> | 
                                <span v-html="book.publisher"></span> | 
                                <span v-html="book.pubdate"></span>
                            </p>
                            <p v-html="book.description"></p>
                        </div>
                    </div>
                </div>
            </div>
            <div id="sub">
                <div id="profile">
                    <label for="file">
                        <i class="fas fa-user-circle" v-if="user.userImg==null"></i>
                        <img :src="getImgUrl(user.userImg)" v-bind:alt="user.userImg" v-if="user.userImg!=null">
                    </label>
                    <input id="file" type="file" @change="updateUserImg" enctype="multipart/form-data" accept=".gif, .jpg, .png">
                    <form @submit.prevent="updateUser(user.userName)">
                         <input type="text" v-model="user.userName">
                    </form>
                </div>

                <div id=""></div>
            </div>
        </section>
    </div>
</template>

<script>
import axios from "axios";
import Navigation from "@/components/Navigation";

function getToken() {
    return localStorage.getItem('bookToken');
}
const token = getToken();
if(token === null) {
    location.assign('/login.html');
}

export default {
    name: 'Main',
    components: {
        Navigation
    },
    data() {
        return {
            keyword:'',
            books:[],
            user:[],
            files:''
        }
    },
    mounted() {
        axios
            .get('http://localhost:7777/api/bookUser/' + token)
            .then(response => {
                this.user = response.data;
            })
    },
    methods: {
        search() {
            axios
                .get('http://localhost:7777/api/bookSearch/' + this.keyword)
                .then(response => {
                    this.books = response.data.items;
                })
                .catch(error => {
                    alert(error);
                })
        },
        insertBook(book) {
            axios
                .post('http://localhost:7777/api/book', {
                    bookIsbn: book.isbn,
                    bookImg: book.image,
                    bookTitle: book.title.replace(/(<([^>]+)>)/ig,""),
                    bookAuthor: book.author.replace(/(<([^>]+)>)/ig,""),
                    bookPublisher: book.publisher.replace(/(<([^>]+)>)/ig,""),
                    bookPubdate: book.pubdate,
                    bookDesc: book.description.replace(/(<([^>]+)>)/ig,"")
                })
            axios
                .post('http://localhost:7777/api/bookList', {
                    bookIsbn: book.isbn,
                    userEmail: token
                })
                .then(response => {
                    if(response.status==200) {
                        alert("읽을 책 추가");
                    } else {
                        alert("이미 추가된 책입니다");
                    }
                })
        },
        updateUser(name) {
            axios
                .put('http://localhost:7777/api/bookUser', {
                    userName: name,
                    userEmail: token
                })
                .then(response => {
                    location.href="/";
                })
        },
        updateUserImg(event) {
            const formData = new FormData();
            formData.append('uploadFile', event.target.files[0]);
            formData.append('userEmail', token);

            for(let key of formData.entries()){
                console.log(`${key}`);
            }
            
            axios
                .patch('http://localhost:7777/api/bookUser', formData, {
                    headers: {
                        'Content-Type' : 'multipart/form-data'
                    }
                })
        },
        getImgUrl(profile_image) {
            var images = require.context('../assets/upload/', false, /\.(png|jpe?g|svg)$/)
            return images('./' + profile_image)
        }
    }
}
</script>

<style>
    * {
        margin: 0;
        padding: 0;
        font-family: 'Nanum Gothic', sans-serif;
    }
    body {
        background: #f4f2db;
    }
    section {
        padding-left: 85px;
        padding-right: 30%;
    }
    #main {
        padding: 20px;
    }
    #search-keyword i {
        color: #134775;
        font-size: 1.1rem;
        cursor: pointer;
    }
    #search-keyword input {
        margin-left: 10px;
        height: 1.8rem;
        padding: 5px;
        border: none;
        border-bottom: 1px solid #134775;
        background: transparent;
        width: 59vw;
    }
    input:focus {
        outline: none;
    }
    #search-result {
        margin-top: 20px;
        display: flex;
    }
    #search-result-image {
        position: relative;
        background: yellow;
    }
    #search-result-image:hover span {
        display: flex;
    }
    #search-result-image img {
        width:110px;
        height: 100%;
    }
    #search-result-image span {
        position: absolute;
        top: 0;
        left: 0;
        background: black;
        width: 100%;
        height: 100%;
        color: white;
        text-align: center;
        justify-content: center;
        align-items: center;
        opacity: 0.7;
        cursor: pointer;
        display: none;
        border: none;
    }
    #search-result-text {
        padding: 10px;
    }
    #search-result-text h2 {
        font-size: 1.1rem;
        margin-bottom: 10px;
    }
    #search-result-text p {
        margin-bottom: 10px;
        line-height: 1.8;
        font-size: 0.9rem;
    }
    #sub {
        width: 30%;
        position: fixed;
        right: 0;
        top:0;
        height: 100vh;
    }
    #profile {
        display: flex;
        flex-direction: column;
        align-items: center;
        padding: 20px;
        display: relative;
    }
    #profile i {
        font-size: 120px;
        color: #134775;
        cursor: pointer;
    }
    #profile input[type=text] {
        font-size: 1.1rem;
        border: none;
        font-weight: bold;
        background: none;
        text-align: center;
        margin-top: 20px;
    }
    #profile input[type=file] {
        display: none;
    }
    #profile img {
        width: 120px;
        border-radius: 50%;
        height: 120px;
    }
</style>