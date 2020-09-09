<template>
  <div id="idea-container">
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
                  <div v-if="list.readCheck!='완료'" id="read-list" @click="selectReadDetail(list.readCode)">
                      <h3 v-html="list.book.bookTitle"></h3>
                      <p>
                          <span v-html="list.readDate"></span>
                          <span @click="deleteReadList(list.readCode)">삭제</span>
                          <span v-html="list.readCheck" @click="updateReadList(list.readCode, list.readCheck)" id="update-read-list"></span>
                      </p>
                  </div>            
              </div>
              <div v-for="list in readList" v-bind:key="list" @click="selectReadDetail(list.readCode)">
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
          <div id="idea-detail" v-if="note!=''">
              <h4>책 제목 : {{note.book.bookTitle}}</h4>
              <p>출판사 : {{note.book.bookPublisher}}</p>
              <p>저자 : {{note.book.bookAuthor}}</p>
              <h5>본 것 (What I See)</h5>
              <div :id="'read-note-'+read.noteCode" v-for="read in readNote" v-bind:key="read">
                  <p>
                      <input type="text" placeholder="위치.." @keyup.enter="updateReadPage(read.noteCode, $event)" v-model="read.notePage">
                      <button @click="deleteReadNote(read.noteCode)">삭제</button>
                  </p>
                  <resizable-textarea>
                      <textarea :id="'read-note-desc-'+read.noteCode" placeholder="내용을 입력해주세요.." @keyup="updateReadNote(read.noteCode, $event)" v-model="read.noteDesc"></textarea>
                  </resizable-textarea>
              </div>
              <div id="read-note">
                  <p>
                      <input type="text" placeholder="위치.." @keyup.enter="insertReadNote(note.readCode, $event)">
                  </p>
                  <resizable-textarea>
                      <textarea id="read-note-desc" placeholder="내용을 입력해주세요.." class="note-height"></textarea>
                  </resizable-textarea>
              </div>
              <h5>적용할 것 (What I Apply)</h5>
                <div :id="'idea-note-'+idea.ideaCode" v-for="idea in ideaNote" v-bind:key="idea">
                    <div v-if="idea.ideaCheck=='false'">
                        <input type="checkbox" :id="'idea-check-'+idea.ideaCode" @change="updateIdeaCheck(idea.ideaCode, $event)">
                        <label :for="'idea-check-'+idea.ideaCode"></label>
                        <input type="text" :id="'idea-todo-'+idea.ideaCode" placeholder="할일을 입력해주세요.." v-model="idea.ideaDesc" @keyup.enter="updateIdeaNote(idea.ideaCode, $event)">
                        <i class="fas fa-times" @click="deleteIdeaNote(idea.ideaCode, $event)"></i>
                    </div>
                    <div v-if="idea.ideaCheck=='true'">
                        <input type="checkbox" :id="'idea-check-'+idea.ideaCode" @change="updateIdeaCheck(idea.ideaCode, $event)" checked>
                        <label :for="'idea-check-'+idea.ideaCode"></label>
                        <input type="text" :id="'idea-todo-'+idea.ideaCode" placeholder="할일을 입력해주세요.." v-model="idea.ideaDesc" @keyup.enter="updateIdeaNote(idea.ideaCode, $event)" style="text-decoration:line-through">
                        <i class="fas fa-times" @click="deleteIdeaNote(idea.ideaCode, $event)"></i>
                    </div>                    
                </div>
                <div id="idea-note">
                    <div>
                        <input type="checkbox" id="idea-check">
                        <label for="idea-check"></label>
                        <input type="text" placeholder="할일을 입력해주세요.." @keyup.enter="insertIdeaNote(note.readCode, $event)">
                    </div>
                </div>
          </div>
      </section>
  </div>
</template>

<script>
import Vue from "vue";
import axios from "axios";
import Navigation from "@/components/Navigation";
import ResizableTextarea from '../assets/ResizableTextarea.js';
import VueInputAutowidth from 'vue-input-autowidth';

Vue.use(VueInputAutowidth);

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
        Navigation,
        ResizableTextarea
    },
    data() {
        return {
            bookList:[],
            readList:[],
            note:[],
            readNote:[],
            ideaNote:[]
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
        selectReadDetail(readCode) {
            axios
                .get('http://localhost:7777/api/readDetail/'+readCode)
                .then(response => {
                    this.note = response.data;
                });
            axios
                .get('http://localhost:7777/api/readNote/' + readCode)
                .then(response => {
                    this.readNote = response.data;
                });
            axios
                .get('http://localhost:7777/api/ideaNote/' + readCode)
                .then(response => {
                    this.ideaNote = response.data;
                });
        },
        insertReadNote(readCode, event) {
            axios
                .post('http://localhost:7777/api/readNote', {
                    notePage:event.target.value,
                    readCode:readCode
                })
                .then(response => {
                    location.reload(true);
                })
        },
        updateReadPage(noteCode, event) {
            axios
                .put('http://localhost:7777/api/readNote', {
                    notePage:event.target.value,
                    noteCode:noteCode
                })
        },
        updateReadNote(noteCode, event) {
            axios
                .put('http://localhost:7777/api/readNote', {
                    noteDesc:event.target.value,
                    noteCode:noteCode
                })
        },
        deleteReadNote(noteCode) {
            axios
                .delete('http://localhost:7777/api/readNote/' + noteCode)
                .then(response => {
                    document.querySelector('#read-note-'+noteCode).style.display="none";
                })
        },
        insertIdeaNote(readCode, event) {
            axios
                .post('http://localhost:7777/api/ideaNote', {
                    ideaDesc:event.target.value,
                    readCode:readCode
                })
                .then(response => {
                    location.reload(true);
                })
        },
        updateIdeaNote(ideaCode, event) {
            axios
                .put('http://localhost:7777/api/ideaNote', {
                    ideaDesc:event.target.value,
                    ideaCode:ideaCode
                });          
        },
        updateIdeaCheck(ideaCode, event) {
            let check = document.querySelector('#idea-check-'+ideaCode).checked;
            axios
                .put('http://localhost:7777/api/ideaNote', {
                    ideaCheck: check,
                    ideaCode: ideaCode
                })
                .then(response => {
                    if(check==true) {
                        document.querySelector('#idea-todo-'+ideaCode).style.textDecoration="line-through";
                    } else {
                        document.querySelector('#idea-todo-'+ideaCode).style.textDecoration="none";
                    }
                });
        },
        deleteIdeaNote(ideaCode, event) {
            axios
                .delete('http://localhost:7777/api/ideaNote/' + ideaCode)
                .then(response => {
                    document.querySelector('#idea-note-'+ideaCode).style.display="none";
                })            
        }
    }
}
</script>

<style scoped>
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
    :focus {
        outline: none;
    }

    #idea-container {
        background: white;
        height: 100vh;
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
        width: 64vw;
    }
    #idea-detail h4 {
        font-size: 1.2rem;
        padding-top: 30px;
        padding-left: 15px;
    }
    #idea-detail > p {
        font-size: 0.9rem;
        float: right;
        padding-top: 10px;
        padding-right: 15px;
    }
    #idea-detail h5 {
        font-size: 1.1rem;
        clear: both;
        margin-top: 50px;
        margin-left: 15px;
        margin-bottom: 15px;
    }

    /* read-note */
    [id^=read-note] {
        display: flex;
        font-size: 0.9rem;
        margin-bottom: 20px;
    }
    [id^=read-note] p {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
    }
    [id^=read-note] p * {
        line-height: 1.5;
        padding: 3px;
    }
    [id^=read-note] p button {
        border: none;
        background: none;
        cursor: pointer;
    }
    [id^=read-note] p button:hover {
        color: tomato;
    }
    [id^=read-note] p input {
        border: none;
        font-weight: bold;
        resize: none;
        width: 120px;
        margin-left: 15px;
    }
    [id^=read-note] > textarea {
        width: 100%;
        resize: none;
        border: none;
        padding: 3px;
        line-height: 1.5;
    }
    
    /* idea-note */
    [id^=idea-note] div {
        display: flex;
        justify-content: center;
        margin-left: 15px;
        margin-right: 15px;
        margin-bottom: 10px;
        align-items: center;
    }
    [id^=idea-note] input[type=checkbox] {
        display: none;
    }
    [id^=idea-note] input[type=checkbox]+label {
        width: 16px;
        height: 16px;
        border: 2px solid #aaa;
        display: inline-block;
        cursor: pointer;
    }
    [id^=idea-note] input[type=checkbox]:checked+label {
        background: #134775;
    }
    [id^=idea-note] input[type=text] {
        width: 100%;
        margin-left: 10px;
        margin-right: 10px;
        border: none;
        padding: 5px;
        line-height: 1.5;
    }
    [id^=idea-note] i {
        color: #134775;
        cursor: pointer;
    }
    [id^=idea-note] i:hover {
        color: tomato;
    }
</style>