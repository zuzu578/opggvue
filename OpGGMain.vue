<template>
      <div class="main_contents">
        <div class="banner_image">
            <img src="https://opgg-static.akamaized.net/logo/20210717025919.c71d1c8b76704b41ad64462ac4e7ef5c.png">

        </div>
        <div class="searchArea">
            <div class="input-group mb-3">
            <input type="text" id="SummonerName" value="" class="form-control" placeholder="소환사명을 입력해주세요." aria-label="Username" aria-describedby="basic-addon1">
            <button type="button"  @click="search()" class="btn btn-light">검색</button>
        </div>
   </div>

   <div class="search_result">
       <h1 class="userResult"> 소환사 검색 결과 </h1>
        <div class="contents_image_area" v-if="profileIconId.length == 0">
      <small> <!-- 이미지가 없는 게시물일 경우 아무런 이미지를 표시하지 않습니다.--> </small>
        
     </div>
       <div class="profile_image" v-else> 
             <img :src="'https://opgg-static.akamaized.net/images/profile_icons/profileIcon' + profileIconId +'.jpg?image=q_auto:best&v=1518361200'">
        
      </div>

       <div class="user_nickname">
            {{name}} 
        </div>

      

             

      

   </div>
   <div class="rotation_items">
      <p class="rotaion_title"> 로테이션 챔피언 </p>
     
    <span v-if="freeChampionIds[0]== 7">
      <img src="https://opgg-static.akamaized.net/images/lol/champion/LeBlanc.png?image=c_scale,q_auto,w_140&v=1624418935">
    </span>
     <span v-if="freeChampionIds[1]== 19">
      <img src="https://opgg-static.akamaized.net/images/lol/champion/Warwick.png?image=c_scale,q_auto,w_140&v=1624418935">
    </span>
     <span v-if="freeChampionIds[2]== 29">
      <img src="https://opgg-static.akamaized.net/images/lol/champion/Twitch.png?image=c_scale,q_auto,w_140&v=1624418935">
    </span>
     <span v-if="freeChampionIds[3]== 39">
      <img src="https://opgg-static.akamaized.net/images/lol/champion/Irelia.png?image=c_scale,q_auto,w_140&v=1624418935">
    </span>
     <span v-if="freeChampionIds[4]== 42">
      <img src="https://opgg-static.akamaized.net/images/lol/champion/Corki.png?image=c_scale,q_auto,w_140&v=1624418935">
    </span>
     <span v-if="freeChampionIds[5]== 53">
      <img src="https://opgg-static.akamaized.net/images/lol/champion/Blitzcrank.png?image=c_scale,q_auto,w_140&v=1624418935">
    </span>
     <span v-if="freeChampionIds[6]== 55">
      <img src="https://opgg-static.akamaized.net/images/lol/champion/Katarina.png?image=c_scale,q_auto,w_140&v=1624418935">
    </span>
     <span v-if="freeChampionIds[7]== 82">
      <img src="https://opgg-static.akamaized.net/images/lol/champion/Mordekaiser.png?image=c_scale,q_auto,w_140&v=1624418935">
    </span>
     <span v-if="freeChampionIds[8]== 83">
      <img src="https://opgg-static.akamaized.net/images/lol/champion/Yorick.png?image=c_scale,q_auto,w_140&v=1624418935">
    </span>
     <span v-if="freeChampionIds[9]== 98">
      <img src="https://opgg-static.akamaized.net/images/lol/champion/Fiddlesticks.png?image=c_scale,q_auto,w_140&v=1624418935">
    </span>
     <span v-if="freeChampionIds[10]== 104">
      <img src="https://opgg-static.akamaized.net/images/lol/champion/Graves.png?image=c_scale,q_auto,w_140&v=1624418935">
    </span>
     <span v-if="freeChampionIds[11]== 164">
      <img src="https://opgg-static.akamaized.net/images/lol/champion/Camille.png?image=c_scale,q_auto,w_140&v=1624418935">
    </span>
     <span v-if="freeChampionIds[12]== 497">
      <img src="https://opgg-static.akamaized.net/images/lol/champion/Rakan.png?image=c_scale,q_auto,w_140&v=1624418935">
    </span>
      <span v-if="freeChampionIds[13]== 498">
      <img src="https://opgg-static.akamaized.net/images/lol/champion/Xayah.png?image=c_scale,q_auto,w_140&v=1624418935">
    </span>
      <span v-if="freeChampionIds[14]== 518">
      <img src="https://opgg-static.akamaized.net/images/lol/champion/Neeko.png?image=c_scale,q_auto,w_140&v=1624418935">
    </span>
    </div>


   </div>
</template>

<script>
import axios from 'axios'
export default {
    data(){
        return{
            freeChampionIds : '',
            freeChampionIdsForNewPlayers:'',
            name:'',
            profileIconId:'',

        }

    },
    created(){
    // riot api 를 이용하여 로테이션 챔피언 list 를 가져옵니다. 
    axios.get('http://localhost:8082/myopggapp/getRotationChapion')  
    .then((res)=>{
        console.log(res.data);
        this.freeChampionIds = res.data.freeChampionIds;
        this.freeChampionIdsForNewPlayers = res.data.freeChampionIdsForNewPlayers;
        
    }) 


    },
    mounted(){

 
    },
    methods:{
        search(){
            var SummonerName = document.getElementById("SummonerName").value;
            console.log("SummonerName ====> " + SummonerName);
                axios.get('http://localhost:8082/myopggapp/searchSummoner',{
                 params: {
                 SummonerName : SummonerName
                }
            })    
            .then((res)=>{
                 console.log(res.data.name);
                 this.name = res.data.name;
                 this.profileIconId = res.data.profileIconId;
                 console.log(res.data);
                 
        
    })
}  
    },
}
</script>

<style>
.userResult{
    color:white;
}
.search_result{
    margin-bottom:200px;

}

.user_nickname{
    font-weight: bold;
    font-size: 40px;
    color:black;
}
.profile_image img{
    width:20%;
}
.rotaion_title{
    color:white;
    font-weight: bold;
    font-size:40px;
    
}
.searchArea{
    margin-top:40px;
    margin-bottom:40px;


}
.main_contents{
    width:1200px;
    margin:0 auto;

}
body{
    background-color:rgb(21, 107, 255);
}
@import 'https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css'
</style>