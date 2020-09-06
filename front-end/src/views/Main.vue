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
                        </div>
                        <div id="search-result-text">
                            <!-- book.isbn : book primary key -->
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
                <p>프로필 사진</p>
                <p>이름</p>
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
            books:[]
        }
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
        /* background: pink; */
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
    #search-keyword input:focus {
        outline: none;
    }
    #search-result {
        margin-top: 20px;
        display: flex;
    }
    #search-result-image img {
        height: 100%;
        width:90px;
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
    }
    #sub {
        background: yellow;
        width: 30%;
        position: fixed;
        right: 0;
        top:0;
        height: 100vh;
    }
</style>