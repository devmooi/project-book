<template>
  <div>
      <Navigation></Navigation>
      <section>
          <h2>읽을 책 리스트</h2>
          <div v-for="list in bookList" v-bind:key="list" id="book-list">
            <img :src="list.book.bookImg">
            <div>
                <h3 v-html="list.book.bookTitle"></h3>
                <p>
                    {{list.book.bookAuthor}} | 
                    {{list.book.bookPublisher}} |
                    {{list.book.bookPubdate}}
                </p>
                <p v-html="list.book.bookDesc"></p>
            </div>
            <input type="date" v-model="list.listDate" @change="updateBookDate(list.listCode, $event)">
            <button @click="deleteBookData(list.listCode)"><i class="fas fa-trash"></i> 삭제</button>
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
    name: 'BookList',
    components: {
        Navigation
    },
    data() {
        return {
            bookList:[]
        }
    },
    mounted() {
        axios
            .get('http://localhost:7777/api/bookList/' + token)
            .then(response => {
                this.bookList = response.data;
            })
    },
    methods: {
        updateBookDate(listCode, event) {
            axios
                .put('http://localhost:7777/api/bookList', {
                    listCode: listCode,
                    listDate: event.target.value
                })
                .then(response => {
                    location.reload(true);
                })
        },
        deleteBookData(listCode) {
            axios
                .delete('http://localhost:7777/api/bookList/'+listCode)
                .then(response => {
                    location.reload(true);
                })
        }
    }
}
</script>

<style scoped>
    section {
        padding-left: 85px;
    }
    :focus {
        outline: none;
    }
    section h2 {
        padding: 20px;
        font-size: 1.5rem;
    }
    #book-list {
        display: flex;
        background: white;
        margin: 20px;
        padding: 10px;
        border-radius: 5px;
        box-shadow: 5px 5px 5px #f5e5a9;
        align-items: center;
    }
    #book-list img {
        margin-right: 10px;
    }
    #book-list h3 {
        font-size: 1.1rem;
        margin-bottom: 5px;
    }
    #book-list p {
        font-size: 0.9rem;
        line-height: 1.8;
        margin-bottom: 5px;
    }
    #book-list input[type=date] {
        width: 260px;
        border: none;
        font-size: 0.9rem;
        padding-left: 20px;
        padding-right: 20px;
    }
    #book-list button {
        width: 150px;
        cursor: pointer;
        border: none;
        border-radius: 5px;
        font-weight: bold;
        padding: 5px;
        background: transparent;
        color: #aaa;
        font-size: 0.9rem;
    }
    #book-list button:hover {
        color: tomato;
    }
</style>