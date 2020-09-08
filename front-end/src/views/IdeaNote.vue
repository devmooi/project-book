<template>
  <div>
      <Navigation></Navigation>
      <section>
          <div id="idea-list">
              <h2>아이디어 노트</h2>
              <i class="fas fa-search" @mouseenter="bookListView"></i>
              <div id="search-result" @mouseleave="bookListHidden">
                  <div v-for="list in bookList" v-bind:key="list" @click="insertReadList(list.book.bookIsbn)">
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
              <div v-for="list in readList" v-bind:key="list" >      
                  <div v-if="list.readCheck!='완료'" id="read-list" @click="selectReadList">
                      <h3 v-html="list.book.bookTitle"></h3>
                      <p>
                          <span v-html="list.readDate"></span>
                          <span @click="deleteReadList(list.readCode)">삭제</span>
                          <span v-html="list.readCheck" @click="updateReadList(list.readCode, list.readCheck)" id="update-read-list"></span>
                      </p>
                  </div>            
              </div>
              <div v-for="list in readList" v-bind:key="list" @click="selectReadList">
                  <div v-if="list.readCheck=='완료'" id="read-list" class="complete">
                      <h3 v-html="list.book.bookTitle"></h3>
                      <p>
                          <span v-html="list.readDate"></span>
                          <span @click="deleteReadList(list.readCode)">삭제</span>
                          <span v-html="list.readCheck" @click="updateReadList(list.readCode, list.readCheck)" id="update-read-list"></span>
                      </p>
                  </div>               
              </div>
          </div>
          <div id="idea-detail">
              <div>
                  <h4>책 제목 : </h4>
                  <p>저자 : </p>
                  <p>출판사 : </p>
              </div>
              <div>
                  <h4>본 것 (What I See)</h4>
              </div>
              <div>
                  <h4>적용할 것 (What I Apply)</h4>
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
        axios
            .get('http://localhost:7777/api/readList/' + token)
            .then(response => {
                this.readList = response.data;
            })
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
        insertReadList(bookIsbn) {
            axios
                .post('http://localhost:7777/api/readList', {
                    bookIsbn: bookIsbn,
                    userEmail: token
                })
                .then(response => {
                    if(response.status==200) {
                        location.reload(true);
                    } else {
                        alert("이미 추가된 책입니다");
                    }
                })
        },
        updateReadList(readCode, readCheck) {
            if(readCheck=='완료') return;

            switch(readCheck) {
                case '학습일': readCheck='1일 후'; break;
                case '1일 후': readCheck='3일 후'; break;
                case '3일 후': readCheck='7일 후'; break;
                case '7일 후': readCheck='15일 후'; break;
                case '15일 후': readCheck='30일 후'; break;
                case '30일 후': readCheck='60일 후'; break;
                case '60일 후': readCheck='완료'; break;
            }
    
            axios
                .put('http://localhost:7777/api/readList', {
                    readCode: readCode,
                    readCheck: readCheck
                })
                .then(response => {
                    location.reload(true);
                })
        },
        deleteReadList(readCode) {
            axios
                .delete('http://localhost:7777/api/readList/'+readCode)
                .then(response => {
                    location.reload(true);
                })
        },
        selectReadList() {
            alert('안녕 ~~ 드디어 세부 설정으로 들어가니? 오전에 끝내자!');
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
    h3 {
        font-size: 1.1rem;
        margin-bottom: 8px;
        line-height: 1.2;
    }
    p {
        font-size: 0.9rem;
    }

    #idea-list {
        height: 100vh;
        position: fixed;
        overflow: auto;
        width: 30%;
        background: #f4f2db;
        border-right: 1px solid #f5e5a9;
        box-shadow: 1px 1px 1px #f5e5a9;
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
    #read-list {
        clear: both;
        border-top: 1px solid #f5e5a9;
        padding: 20px;
        cursor: pointer;
    }
    #read-list:hover {
        background: #f5e5a9;
    }
    #read-list h3 {
        line-height: 1.5;
    }
    #read-list p {
        color: #777;
        overflow: hidden;
    }
    #read-list p span:first-child {
        float: left;
    }
    #read-list p span:first-child ~ span {
        float: right;
        cursor: pointer;
        margin-left: 15px;
    }
    #read-list p span:first-child ~ span:hover {
        color: tomato;
    }
    .complete {
        color: #777;
        cursor: default;
    }
    .complete p span:last-child {
        cursor: default!important;
    }
    .complete p span:last-child:hover {
        color: #777!important;
    }
    #idea-detail {
        margin-left: 32%;
        background: white;
    }
</style>