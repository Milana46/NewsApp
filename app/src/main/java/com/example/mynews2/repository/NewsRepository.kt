package com.example.mynews2.repository

import com.example.mynews2.database.ArticleDatabase
import com.example.mynews2.api.NewsAPI
import com.example.mynews2.api.RetrofitInstance
import com.example.mynews2.models.Article

class NewsRepository(val db:ArticleDatabase) {
  //функции из NewsApi!!!
    suspend fun getHeadline(countryCode:String, pageNumber:Int)=
      RetrofitInstance.api.getHeadlines(countryCode,pageNumber)

    suspend fun searchForNews(searchQuery:String, pageNumber:Int)=
      RetrofitInstance.api.getHeadlines(searchQuery,pageNumber)


        //методы из articleDao!!!
    suspend fun upsert(article: Article)=db.getArticleDao().upsert(article)

    fun getFavoriteNews()=db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article:Article)=db.getArticleDao().deleteArticle(article)
}
