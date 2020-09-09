<template>
    <div>
        <Navigation></Navigation>
        <section id="main-container">
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

            <div id="search-keyword">
                <label><i class="fas fa-search"></i>
                <input type="search" placeholder="책 검색..." @keyup.enter="search" v-model="keyword"></label>
            </div>

            <div id="search-keyword-result" v-for="book in books" v-bind:key="book">
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
    location.assign('/book/login.html');
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
                    location.reload(true);
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
                .then(response => {
                    location.reload(true);
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
    ::-webkit-scrollbar {
        width: 6px;
    }
    ::-webkit-scrollbar-track {
        background-color: transparent;
    }
    ::-webkit-scrollbar-thumb {
        border-radius: 3px;
        background-color: #f5e5a9;
    }
    :focus {
        outline: none;
    }
    * {
        margin: 0;
        padding: 0;
        font-family: 'Nanum Gothic', sans-serif;
    }
    body {
        background: #f4f2db;
    }

    #main-container {
        padding-left: 85px;
    }

    #profile {
        display: flex;
        justify-content: flex-end;
        padding: 20px;
    }
    #profile i {
        font-size: 70px;
        cursor: pointer;
        color: #134775;
    }
    #profile img {
        width: 70px;
        height: 70px;
        border-radius: 50%;
    }
    #profile input[type=file] {
        display: none;
    }
    #profile input[type=text] {
        font-size: 1.1rem;
        border: none;
        font-weight: bold;
        background: none;
        margin-top: 20px;
        width: 100px;
        margin-left: 15px;
    }

    #search-keyword {
        display: flex;
        justify-content: center;
        height: 15vh;
        align-items: center;
    }
    #search-keyword i {
        color: #134775;
        font-size: 3rem;
        cursor: pointer;
    }
    #search-keyword input {
        margin-left: 20px;
        font-size: 3rem;
        border: none;
        background: none;
        border-bottom: 1px solid #134775;
        width: 900px;
    }

    #search-keyword-result {
        display: flex;
        margin: 30px;
    }
    #search-result-image {
        position: relative;
    }
    #search-result-image img {
        width: 110px;
        height: 100%;
    }
    #search-result-image:hover span {
        display: flex;
    }
    #search-result-image span {
        background: black;
        position: absolute;
        color: white;
        top:0;
        left: 0;
        width: 100%;
        height: 100%;
        opacity: 0.7;
        cursor: pointer;
        display: none;
        justify-content: center;
        align-items: center;
        border: none;
    }

    #search-result-text {
        display: flex;
        flex-direction: column;
        justify-content: center;
        margin-left: 15px;
    }
    #search-result-text h2 {
        font-size: 1.5rem;
        margin-bottom: 15px;
    }
    #search-result-text p {
        line-height: 1.6;
        margin-bottom: 15px;
        font-size: 1rem;
    }
</style>