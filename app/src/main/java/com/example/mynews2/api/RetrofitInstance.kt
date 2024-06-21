package com.example.mynews2.api


import com.example.mynews2.util.Constants.Companion.BASE_URL_TIME_DELAY
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitInstance {

    companion object{
        private val retrofit by lazy{
            val logging=HttpLoggingInterceptor()  //логирование сетевых запросов и ответов
            logging.setLevel(HttpLoggingInterceptor.Level.BODY) //установка уровня логирования
            val client=OkHttpClient.Builder() //настройка параметра http клиента
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL_TIME_DELAY)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api by lazy{
            retrofit.create(NewsAPI::class.java)  //создание экземпляра класса NewsApi
        }

    }
}