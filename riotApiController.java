{\rtf1\ansi\ansicpg949\cocoartf2580
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fswiss\fcharset0 Helvetica;\f1\fnil\fcharset129 AppleSDGothicNeo-Regular;\f2\fnil\fcharset0 LucidaGrande;
}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\paperw11900\paperh16840\margl1440\margr1440\vieww11520\viewh8400\viewkind0
\pard\tx566\tx1133\tx1700\tx2267\tx2834\tx3401\tx3968\tx4535\tx5102\tx5669\tx6236\tx6803\pardirnatural\partightenfactor0

\f0\fs24 \cf0 package com.myopgg.myopggapp;\
\
import java.io.BufferedReader;\
import java.io.InputStreamReader;\
import java.lang.reflect.Type;\
import java.net.HttpURLConnection;\
import java.net.URL;\
import java.text.DateFormat;\
import java.util.ArrayList;\
import java.util.Date;\
import java.util.List;\
import java.util.Locale;\
import java.util.Map;\
import java.util.Set;\
\
\
import org.json.simple.JSONObject;\
\
import org.json.simple.parser.JSONParser;\
\
import javax.servlet.http.HttpServletRequest;\
\
import org.json.simple.JSONArray;\
import org.slf4j.Logger;\
import org.slf4j.LoggerFactory;\
import org.springframework.stereotype.Controller;\
import org.springframework.ui.Model;\
import org.springframework.web.bind.annotation.RequestMapping;\
import org.springframework.web.bind.annotation.RequestMethod;\
\
import com.google.gson.Gson;\
import com.google.gson.JsonArray;\
import com.google.gson.JsonElement;\
import com.google.gson.JsonObject;\
import com.google.gson.JsonParser;\
import com.google.gson.reflect.TypeToken;\
import com.myopgg.myopggapp.dto.ChampionInfoRotation;\
import com.myopgg.myopggapp.dto.ChampionMasteryDto;\
import com.myopgg.myopggapp.dto.ChampionsResourcesDto;\
import com.myopgg.myopggapp.dto.MatchlistDto;\
import com.myopgg.myopggapp.dto.SummonerDto;\
import com.myopgg.myopggapp.utils.IdForNameUtil;\
\
\
\
\
/**\
 * Handles requests for the application home page.\
 */\
@Controller\
public class SearchSummoner \{\
	//API keys \
	//api 
\f1 \'c5\'b0\'b4\'c2
\f0  
\f1 \'c0\'af\'c8\'bf\'b1\'e2\'b0\'a3\'c0\'cc
\f0  
\f1 \'c1\'b8\'c0\'e7\'c7\'cf\'b9\'c7\'b7\'ce
\f0  
\f1 \'c0\'e7\'b9\'df\'b1\'de\'c0\'bb
\f0  
\f1 \'b9\'de\'be\'c6\'be\'df\'c7\'d5\'b4\'cf\'b4\'d9
\f0 .\
	final static String API_KEY = "RGAPI-715e6e7f-64c0-4532-817f-0cdaeb4d6854";\
	@RequestMapping(value = "/ShowchampionStatics" , method = RequestMethod.GET)\
	public String ShowchampionStatics(HttpServletRequest req, Model model) \{\
		// /lol/static-data/v3/champions\
		//https://kr.api.riotgames.com /lol/static-data/v3/champions"+"?api_key="+API_KEY;\
		BufferedReader br = null;\
		ChampionsResourcesDto rdto = null;\
		try \{\
			//https://kr.api.riotgames.com/lol/platform/v3/champion-rotations"+"?api_key="+API_KEY\
			String urlstr = "https://kr.api.riotgames.com/lol/static-data/v3/champions"+"?api_key="+API_KEY;\
			URL url = new URL(urlstr);\
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();\
			urlconnection.setRequestMethod("GET");\
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8")); \
			String result = "";\
			String line;\
			while((line = br.readLine()) != null) \{ \
				result = result + line;\
			\}\
			JsonParser jsonParser = new JsonParser();\
			JsonObject k = (JsonObject) jsonParser.parse(result);\
			String version = k.get("version").getAsString();\
			System.out.println("version = ====>" + version);\
			String champListData = k.get("champListData").getAsString();\
			System.out.println("champListData = ====>" + champListData);\
			Boolean dataById = k.get("dataById").getAsBoolean();\
			System.out.println("dataById = ====>" + dataById);\
			String locale = k.get("locale").getAsString();\
			System.out.println("locale = ====>" + locale);\
			\
			model.addAttribute("version", version);\
			model.addAttribute("champListData", champListData);\
			model.addAttribute("dataById", dataById);\
			model.addAttribute("locale", locale);\
			\
			\
		\}catch(Exception e) \{\
			System.out.println(e);\
		\}\
		\
		return "ShowchampionStatics";\
	\}\
	\
	\
	@RequestMapping(value = "/", method = RequestMethod.GET)\
	public String home(HttpServletRequest httpServletRequest, Model model) \{\
		BufferedReader br = null;\
		ChampionInfoRotation cdto = null;\
		try \{\
			///lol/platform/v3/champion-rotations\
			// /lol/platform/v3/champion-rotations\
			String urlstr = "https://kr.api.riotgames.com/lol/platform/v3/champion-rotations"+"?api_key="+API_KEY;\
			URL url = new URL(urlstr);\
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();\
			urlconnection.setRequestMethod("GET");\
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8")); \
			String result = "";\
			String line;\
			while((line = br.readLine()) != null) \{ \
				result = result + line;\
			\}\
			JsonParser jsonParser = new JsonParser();\
			JsonObject k = (JsonObject) jsonParser.parse(result);\
			int maxNewPlayerLevel = k.get("maxNewPlayerLevel").getAsInt();\
			System.out.println("maxNewPlayerLevel =========>"+maxNewPlayerLevel);\
			JsonArray freeChampionIdsForNewPlayers = new JsonArray();\
			 freeChampionIdsForNewPlayers =  k.get("freeChampionIdsForNewPlayers").getAsJsonArray();\
			System.out.println("freeChampionIdsForNewPlayers ========>" + freeChampionIdsForNewPlayers );\
			JsonArray freeChampionIds = new JsonArray();\
			freeChampionIds = k.get("freeChampionIds").getAsJsonArray();\
			System.out.println("freeChampionIds======>"+freeChampionIds);\
			//cdto = new ChampionInfoRotation(maxNewPlayerLevel,freeChampionIdsForNewPlayers,freeChampionIds);\
			//model.addAttribute("SummonerName",SummonerName);\
			IdForNameUtil a1 = new IdForNameUtil();\
			//a1.ChangeName(freeChampionIds); ==> 
\f1 \'c3\'a8\'c7\'c7\'be\'f0
\f0  
\f1 \'c0\'cc\'b8\'a7
\f0  
\f1 \'b9\'d9\'b2\'d9\'b1\'e2
\f0  \
			\
			model.addAttribute("rotationName",a1.ChangeName(freeChampionIds));\
			model.addAttribute("maxNewPlayerLevel",maxNewPlayerLevel);\
			model.addAttribute("freeChampionIdsForNewPlayers",freeChampionIdsForNewPlayers);\
			model.addAttribute("freeChampionIds",freeChampionIds);\
			\
		\}catch(Exception e)\{\
			System.out.println(e.getMessage());\
		\}\
		\
		return "home";\
	\}\
	\
	\
	@RequestMapping(value = "/search", method = RequestMethod.GET)\
	public String result(Model model, HttpServletRequest httpServletRequest) \{\
		////https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/%EC%98%A4%EC%88%9C%EB%8F%84%EC%88%9C%EB%8F%84%EB%9E%80%EB%8F%84%EB%9E%80?api_key=RGAPI-637a6ba5-549d-43da-b3e2-961dfc8a8766\
		\
		//VersionCheck.checkVersion();\
		BufferedReader br = null;\
		String SummonerName = httpServletRequest.getParameter("SummonerName");\
		System.out.println("SummonerName===>"+SummonerName);\
		SummonerDto temp= null;\
		com.myopgg.myopggapp.dto.LeagueEntrydto[] leagueInfo = null;\
		//LeagueEntrydto[] leagueInfo = null;\
		try\{      \
			///lol/summoner/v4/summoners/by-account/\{encryptedAccountId\}\
			String urlstr = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/"+\
					SummonerName.replace(" ", "")		+"?api_key="+API_KEY;\
			URL url = new URL(urlstr);\
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();\
			urlconnection.setRequestMethod("GET");\
			\
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8")); // 
\f2 \uc0\u65533 
\f1 \'97\'ac\'ea\'b8\'b0\'ec\'97\'90
\f0  
\f1 \'eb\'ac\'b8\'ec\'9e\'90
\f2 \uc0\u65533 
\f1 \'97\'b4
\f2 \uc0\u65533 
\f1 \'9d\'84
\f0  
\f1 \'eb\'b0\'9b\'ec\'95\'84
\f2 \uc0\u65533 \u65533 \u65533 
\f1 \'9d\'bc
\f0 .\
			String result = "";\
			String line;\
			while((line = br.readLine()) != null) \{ // 
\f1 \'ea\'b7
\f2 \uc0\u65533 
\f0  
\f1 \'eb\'b0\'9b\'ec\'95\'84
\f2 \uc0\u65533 
\f1 \'98\'a8
\f0  
\f1 \'eb\'ac\'b8\'ec\'9e\'90
\f2 \uc0\u65533 
\f1 \'97\'b4
\f2 \uc0\u65533 
\f1 \'9d\'84
\f0  
\f1 \'ea\'b3\'84\'ec\'86\'8d
\f0  br
\f2 \uc0\u65533 
\f1 \'97\'90
\f2 \uc0\u65533 
\f1 \'84\'9c
\f0  
\f1 \'ec\'a4\'84\'eb\'8b\'a8
\f2 \uc0\u65533 
\f1 \'9c\'84\'eb\'a1
\f2 \uc0\u65533 
\f0  
\f1 \'eb\'b0\'9b\'ea\'b3\'a0
\f0  
\f1 \'ec\'b6\'9c\'eb\'a0\'a5
\f2 \uc0\u65533 
\f1 \'95\'98\'ea\'b2\'a0\'eb\'8b\'a4
\f0 .\
				result = result + line;\
			\}\
			JsonParser jsonParser = new JsonParser();\
			JsonObject k = (JsonObject) jsonParser.parse(result);\
			int profileIconId = k.get("profileIconId").getAsInt();\
			String name = k.get("name").getAsString();\
			System.out.println(name);\
			String puuid = k.get("puuid").getAsString();\
			long summonerLevel = k.get("summonerLevel").getAsLong();\
			System.out.println(summonerLevel);\
			long revisionDate = k.get("revisionDate").getAsLong();\
			String id = k.get("id").getAsString();\
			String accountId = k.get("accountId").getAsString();\
			System.out.println("accountId ====> "+accountId);\
			temp = new SummonerDto(profileIconId,name,puuid,summonerLevel,revisionDate,id,accountId);\
			model.addAttribute("SummonerName",SummonerName);\
			model.addAttribute("profileIconId",profileIconId);\
			model.addAttribute("name",name);\
			model.addAttribute("puuid",puuid);\
			model.addAttribute("summonerLevel",summonerLevel);\
			model.addAttribute("revisionDate",revisionDate);\
			model.addAttribute("id",id);\
			model.addAttribute("accountId",accountId);\
		\}catch(Exception e)\{\
			System.out.println(e.getMessage());\
		\}\
		String[] leagueName = null ; \
		try\{            \
			String urlstr = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/"+\
					temp.getId()		+"?api_key="+API_KEY;\
			URL url = new URL(urlstr);\
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();\
			urlconnection.setRequestMethod("GET");\
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8")); // 
\f2 \uc0\u65533 
\f1 \'97\'ac\'ea\'b8\'b0\'ec\'97\'90
\f0  
\f1 \'eb\'ac\'b8\'ec\'9e\'90
\f2 \uc0\u65533 
\f1 \'97\'b4
\f2 \uc0\u65533 
\f1 \'9d\'84
\f0  
\f1 \'eb\'b0\'9b\'ec\'95\'84
\f2 \uc0\u65533 \u65533 \u65533 
\f1 \'9d\'bc
\f0 .\
			String result = "";\
			String line;\
			while((line = br.readLine()) != null) \{ // 
\f1 \'ea\'b7
\f2 \uc0\u65533 
\f0  
\f1 \'eb\'b0\'9b\'ec\'95\'84
\f2 \uc0\u65533 
\f1 \'98\'a8
\f0  
\f1 \'eb\'ac\'b8\'ec\'9e\'90
\f2 \uc0\u65533 
\f1 \'97\'b4
\f2 \uc0\u65533 
\f1 \'9d\'84
\f0  
\f1 \'ea\'b3\'84\'ec\'86\'8d
\f0  br
\f2 \uc0\u65533 
\f1 \'97\'90
\f2 \uc0\u65533 
\f1 \'84\'9c
\f0  
\f1 \'ec\'a4\'84\'eb\'8b\'a8
\f2 \uc0\u65533 
\f1 \'9c\'84\'eb\'a1
\f2 \uc0\u65533 
\f0  
\f1 \'eb\'b0\'9b\'ea\'b3\'a0
\f0  
\f1 \'ec\'b6\'9c\'eb\'a0\'a5
\f2 \uc0\u65533 
\f1 \'95\'98\'ea\'b2\'a0\'eb\'8b\'a4
\f0 .\
				result = result + line;\
			\}\
			JsonParser jsonParser = new JsonParser();\
			JsonArray arr = (JsonArray) jsonParser.parse(result);\
			leagueInfo = new com.myopgg.myopggapp.dto.LeagueEntrydto[arr.size()];\
			leagueName = new String[arr.size()];\
			for(int i=0; i<arr.size(); i++) \{\
				JsonObject k =  (JsonObject) arr.get(i);\
				int wins = k.get("wins").getAsInt();\
				int losses = k.get("losses").getAsInt();\
				String rank = k.get("rank").getAsString();\
				String tier = k.get("tier").getAsString();\
				String queueType = k.get("queueType").getAsString();\
				int leaguePoints = k.get("leaguePoints").getAsInt();\
				String leagueId = k.get("leagueId").getAsString();\
				\
				model.addAttribute("wins",wins);\
				model.addAttribute("losses",losses);\
				model.addAttribute("rank",rank);\
				model.addAttribute("tier",tier);\
				model.addAttribute("queueType",queueType);\
				model.addAttribute("leaguePoints",leaguePoints);\
				model.addAttribute("leagueId",leagueId);\
				System.out.println("wins"+wins);\
				System.out.println(losses);\
				System.out.println(rank);\
				System.out.println(tier);\
				System.out.println(queueType);\
				System.out.println(leaguePoints);\
				System.out.println(leagueId);\
\
				leagueInfo[i] = new com.myopgg.myopggapp.dto.LeagueEntrydto(queueType, wins, losses, leagueId, rank,tier, leaguePoints);\
				urlstr = "https://kr.api.riotgames.com/lol/league/v4/leagues/"+\
						leagueInfo[i].getLeagueId()		+"?api_key="+API_KEY;\
				url = new URL(urlstr);\
				urlconnection = (HttpURLConnection) url.openConnection();\
				urlconnection.setRequestMethod("GET");\
				br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8")); // 
\f2 \uc0\u65533 
\f1 \'97\'ac\'ea\'b8\'b0\'ec\'97\'90
\f0  
\f1 \'eb\'ac\'b8\'ec\'9e\'90
\f2 \uc0\u65533 
\f1 \'97\'b4
\f2 \uc0\u65533 
\f1 \'9d\'84
\f0  
\f1 \'eb\'b0\'9b\'ec\'95\'84
\f2 \uc0\u65533 \u65533 \u65533 
\f1 \'9d\'bc
\f0 .\
				result = "";\
				line ="";\
				while((line = br.readLine()) != null) \{ // 
\f1 \'ea\'b7
\f2 \uc0\u65533 
\f0  
\f1 \'eb\'b0\'9b\'ec\'95\'84
\f2 \uc0\u65533 
\f1 \'98\'a8
\f0  
\f1 \'eb\'ac\'b8\'ec\'9e\'90
\f2 \uc0\u65533 
\f1 \'97\'b4
\f2 \uc0\u65533 
\f1 \'9d\'84
\f0  
\f1 \'ea\'b3\'84\'ec\'86\'8d
\f0  br
\f2 \uc0\u65533 
\f1 \'97\'90
\f2 \uc0\u65533 
\f1 \'84\'9c
\f0  
\f1 \'ec\'a4\'84\'eb\'8b\'a8
\f2 \uc0\u65533 
\f1 \'9c\'84\'eb\'a1
\f2 \uc0\u65533 
\f0  
\f1 \'eb\'b0\'9b\'ea\'b3\'a0
\f0  
\f1 \'ec\'b6\'9c\'eb\'a0\'a5
\f2 \uc0\u65533 
\f1 \'95\'98\'ea\'b2\'a0\'eb\'8b\'a4
\f0 .\
					result = result + line;\
				\}\
				jsonParser = new JsonParser();\
				k = (JsonObject) jsonParser.parse(result);\
				leagueName[i] = k.get("name").getAsString();\
			\}\
			System.out.print(leagueInfo[0]);\
			model.addAttribute("summoner", temp);\
			//https://opgg-static.akamaized.net/images/profile_icons/profileIcon6.jpg?image=q_auto:best&v=1518361200\
			model.addAttribute("profileImgURL", "https://opgg-static.akamaized.net/images/profile_icons/profileIcon"+temp.getProfileIconId()+".jpg?image=q_auto:best&v=1518361200");\
			model.addAttribute("leagueInfo", leagueInfo);\
			model.addAttribute("tierImgURL", "img/emblems/Emblem_"+leagueInfo[0].getTier()+".png");\
			model.addAttribute("leagueName", leagueName);\
			\
		\}catch(Exception e)\{\
			System.out.println(e.getMessage());\
		\} \
		br = null;\
		\
		SummonerName = httpServletRequest.getParameter("SummonerName");\
		System.out.println("SummonerName===>"+SummonerName);\
		MatchlistDto md= null;\
		try \{\
			//
\f1 \'c2\'fc\'b0\'ed
\f0  \
			//https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/%EC%98%A4%EC%88%9C%EB%8F%84%EC%88%9C%EB%8F%84%EB%9E%80%EB%8F%84%EB%9E%80?api_key=RGAPI-637a6ba5-549d-43da-b3e2-961dfc8a8766\
		\
			// macth list 
\f1 \'ba\'b8\'bf\'a9\'c1\'d6\'b1\'e2
\f0  \
			//https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/2yOznX13C2V-V-GvTY-FZmwzy0kJUg4ibRdd-WsLlghNhn3ZGnrsiA-n?api_key=RGAPI-637a6ba5-549d-43da-b3e2-961dfc8a8766\
			\
			System.out.println("getAccountId 
\f1 \'b7\'ce
\f0  macth List select ");\
			String urlstr = "https://kr.api.riotgames.com/lol/match/v4/matchlists/by-account/"+\
					temp.getAccountId()		+"?api_key="+API_KEY;\
			URL url = new URL(urlstr);\
			System.out.println(temp.getAccountId());\
			System.out.println("getID() ===> "+temp.getId());\
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();\
			urlconnection.setRequestMethod("GET");\
			\
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8")); // 
\f2 \uc0\u65533 
\f1 \'97\'ac\'ea\'b8\'b0\'ec\'97\'90
\f0  
\f1 \'eb\'ac\'b8\'ec\'9e\'90
\f2 \uc0\u65533 
\f1 \'97\'b4
\f2 \uc0\u65533 
\f1 \'9d\'84
\f0  
\f1 \'eb\'b0\'9b\'ec\'95\'84
\f2 \uc0\u65533 \u65533 \u65533 
\f1 \'9d\'bc
\f0 .\
			String result = "";\
			String line;\
			while((line = br.readLine()) != null) \{ \
				result = result + line;\
			\}\
			JsonParser jsonParser = new JsonParser();\
			JsonObject k = (JsonObject) jsonParser.parse(result);\
			\
			\
			int startIndex = k.get("startIndex").getAsInt();\
			System.out.println(startIndex);\
			int endIndex = k.get("endIndex").getAsInt();\
			JsonArray matches = new JsonArray();\
			matches = k.get("matches").getAsJsonArray();\
			System.out.println("jsonArray ====== = = = =");\
			\
			Gson gson = new Gson();\
			Type type = new TypeToken<List<Map<String, Object>>>()\{\}.getType();\
		    List<Map<String, Object>> contactList = gson.fromJson(matches, type);\
			\
			System.out.println("matches ====>"+matches);\
			System.out.println("endIndex" + endIndex);\
			int totalGames = k.get("totalGames").getAsInt();\
			System.out.println("totalGames"+totalGames);\
			//long gameId = k.get("gameId").getAsLong();\
			//System.out.println("gameId" + gameId);\
			\
			//long timestamp = k.get("timestamp").getAsLong();\
			//System.out.println("timestamp"+timestamp);\
			\
			String accountId =temp.getAccountId();\
			//System.out.println("accountId =====> "+accountId);\
			md = new MatchlistDto(startIndex,totalGames,endIndex,matches);\
			model.addAttribute("accountId",accountId);\
			model.addAttribute("startIndex", startIndex);\
			model.addAttribute("totalGames", totalGames);\
			model.addAttribute("endIndex", endIndex);\
			model.addAttribute("matches", contactList);\
			IdForNameUtil a1 = new IdForNameUtil();\
			//a1.ChangeName(freeChampionIds); ==> 
\f1 \'c3\'a8\'c7\'c7\'be\'f0
\f0  
\f1 \'c0\'cc\'b8\'a7
\f0  
\f1 \'b9\'d9\'b2\'d9\'b1\'e2
\f0  \
			\
			\
			\
			\
			//model.addAttribute("timestamp", timestamp);\
		\}catch(Exception e) \{\
			System.out.println(e.getMessage());\
		\}\
		br = null;\
		\
		ChampionMasteryDto cdto= null;\
		try \{\
			// /lol/champion-mastery/v4/champion-masteries/by-summoner/\{encryptedSummonerId\}\
			System.out.println("getId 
\f1 \'b7\'ce
\f0   champin mastery List select ");\
			String urlstr = "https://kr.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/"+\
					temp.getId()		+"?api_key="+API_KEY;\
			URL url = new URL(urlstr);\
			System.out.println(temp.getId());\
			System.out.println("getID() ===> "+temp.getId());\
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();\
			urlconnection.setRequestMethod("GET");\
			\
			br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8")); // 
\f2 \uc0\u65533 
\f1 \'97\'ac\'ea\'b8\'b0\'ec\'97\'90
\f0  
\f1 \'eb\'ac\'b8\'ec\'9e\'90
\f2 \uc0\u65533 
\f1 \'97\'b4
\f2 \uc0\u65533 
\f1 \'9d\'84
\f0  
\f1 \'eb\'b0\'9b\'ec\'95\'84
\f2 \uc0\u65533 \u65533 \u65533 
\f1 \'9d\'bc
\f0 .\
			String result = "";\
			String line;\
			while((line = br.readLine()) != null) \{ \
				result = result + line;\
			\}\
		\
			JsonParser jsonParser = new JsonParser();\
			JsonArray k = (JsonArray) jsonParser.parse(result);\
			System.out.println("===========" + k);\
			//JsonArray championPointsUntilNextLevel = new JsonArray();\
			//long championPointsUntilNextLevel = Long.parseLong(k.get("championPointsUntilNextLevel").toString());\
			 Gson gson = new Gson();\
			Type type = new TypeToken<List<ChampionMasteryDto>>()\{\}.getType();\
		    List<ChampionMasteryDto> championMastery = gson.fromJson(k, type);\
		    model.addAttribute("championMastery",championMastery);\
		    \
			/*\
		    System.out.println("championPointsUntilNextLevel" + championPointsUntilNextLevel);\
			System.out.println("debug_001");\
			System.out.println("championPointsUntilNextLevel" +championPointsUntilNextLevel);\
			boolean chestGranted = k.get("chestGranted").getAsBoolean();\
			System.out.println("chestGranted" +chestGranted);\
			System.out.println("debug_002");\
			long championId = k.get("championId").getAsLong();\
			System.out.println("debug_003");\
			System.out.println("championId" +championId);\
			long lastPlayTime = k.get("lastPlayTime").getAsLong();\
			System.out.println("debug_004");\
			System.out.println("lastPlayTime" +lastPlayTime);\
			int championLevel = k.get("championLevel").getAsInt();\
			System.out.println("debug_005");\
			System.out.println("championLevel" +championLevel);\
			String summonerId = k.get("summonerId").getAsString();\
			System.out.println("debug_006");\
			System.out.println("summonerId" +summonerId);\
			int championPoints = k.get("championPoints").getAsInt();\
			System.out.println("debug_007");\
			System.out.println("championPoints" +championPoints);\
			long championPointsSinceLastLevel = k.get("championPointsSinceLastLevel").getAsInt();\
			System.out.println("debug_008");\
			System.out.println("championPointsSinceLastLevel" +championPointsSinceLastLevel);\
			int tokensEarned = k.get("tokensEarned").getAsInt();\
			System.out.println("debug_009");\
			System.out.println("tokensEarned" +tokensEarned);\
			System.out.println("debug_010");\
			\
			System.out.println("data-onloaded");\
			model.addAttribute("championPointsUntilNextLevel",championPointsUntilNextLevel);\
			model.addAttribute("chestGranted",chestGranted);\
			model.addAttribute("championId",championId);\
			model.addAttribute("lastPlayTime",lastPlayTime);\
			model.addAttribute("championLevel",championLevel);\
			model.addAttribute("summonerId",summonerId);\
			model.addAttribute("championPoints",championPoints);\
		\
			model.addAttribute("championPointsSinceLastLevel",championPointsSinceLastLevel);\
			model.addAttribute("championPointsUntilNextLevel",championPointsUntilNextLevel);\
			model.addAttribute("tokensEarned",tokensEarned);\
			*/\
			\
			\
		\
		\}catch(Exception e) \{\
			System.out.println(e);\
		\}\
		\
	\
		return "result";\
			\
	\}	\
\}\
}