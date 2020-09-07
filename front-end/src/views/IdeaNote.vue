<template>
  <div>
      <Navigation></Navigation>
      <section>
          <div id="idea-list">
              <h2>아이디어 노트</h2>
              <i class="fas fa-search" @mouseenter="bookListView"></i>
              <div id="search-result" @mouseleave="bookListHidden">
                  <div v-for="list in bookList" v-bind:key="list" @click="insertIdeaNote(list.book.bookIsbn)">
                      <img :src="list.book.bookImg">
                      <div>
                        <h3 v-html="list.book.bookTitle"></h3>
                        <p>
                            {{list.book.bookAuthor}} | 
                            {{list.book.bookPublisher}} |
                            {{list.book.bookPubdate}}
                        </p>
                    </div>
                  </div>
              </div>
              <div v-for="list in readList" v-bind:key="list" id="read-list">
              </div>
          </div>
          <div id="idea-detail">
              
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
    name: 'IdeaNote',
    components: {
        Navigation
    },
    data() {
        return {
            bookList:[],
            readList:[]
        }
    },
    mounted() {
        
    },
    methods: {
        bookListView() {
            document.querySelector('#search-result').style.display="block";
            axios
                .get('http://localhost:7777/api/bookList/' + token)
                .then(response => {
                    this.bookList = response.data;
                })
        },
        bookListHidden() {
            document.querySelector('#search-result').style.display="none";
        },
        insertIdeaNote(bookIsbn) {
            axios
                .post('http://localhost:7777/api/readList', {
                    bookIsbn: bookIsbn,
                    userEmail: token
                })
                .then(response => {
                    if(response.status==200) {
                        alert("읽은 책 추가");
                    } else {
                        alert("이미 추가된 책입니다");
                    }
                })
        }
    }
}
</script>

<style scoped>
    body {
        background: white !important;
    }
    section {
        display: flex;
        padding-left: 85px;
    }

    #idea-list {
        height: 100vh;
        position: fixed;
        overflow: auto;
        width: 30%;
        background: #f4f2db;
    }
    #idea-list h2 {
        padding: 20px;
        font-size: 1.5rem;
        float: left;
    }
    #idea-list .fa-search {
        float: right;
        padding: 22px;
        font-size: 1.3rem;
        color: #aaa;
        cursor: pointer;
    }
    #idea-list .fa-search:hover {
        color: #134775;
    }
    #search-result {
        clear: both;
        background: white;
        display: none;
    }
    #search-result > div {
        display: flex;
        padding: 10px;
        align-items: center;
    }
    #search-result > div:hover {
        background: #f5e5a9;
        cursor: pointer;
    }
    #search-result > div img {
        margin-right: 10px;
    }
    #search-result > div h3 {
        font-size: 1.1rem;
        margin-bottom: 8px;
        line-height: 1.2;
    }
    #search-result > div p {
        font-size: 0.9rem;
    }

    #idea-detail {
        margin-left: 32%;
        background: white;
    }
</style>