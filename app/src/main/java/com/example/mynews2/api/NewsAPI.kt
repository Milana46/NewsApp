package com.example.mynews2.api

import com.example.mynews2.models.NewsResponse
import com.example.mynews2.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//для HTTP запроса, у которого будут меняться параметры!!!параметры в query
//response-ожидаемый тип ответа!
interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country")
        countryCode:String="us",

        @Query("page")
        pageNumber:Int=1,

        @Query("key")
        keyApi:String=API_KEY
    ):Response<NewsResponse>

    @GET ("v2/everything")
    suspend fun searchForNews(

        @Query("q")
        searchQuery:String,

        @Query("page")
        pageNumber:Int=1,

        @Query("apiKey")
        apiKey:String= API_KEY

    ):Response<NewsResponse>
}