package com.example.noticias.utils

import com.example.noticias.data.Article
import com.example.noticias.data.News
import com.example.noticias.data.NewsSearchResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsResponse {
    @GET("search/{name}")
    suspend fun findSuperheroesByName(@Path("name") name: String): Article

    @GET("{charcter-id}")
    suspend fun findSuperheroById(@Path("charcter-id") id: String): NewsResponse

    companion object {
        fun getInstance(): NewsResponse {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/everything?q=apple & desde=2025-05-19 & hasta =2025-05-19 & sortBy=popularidad &apiKey=API_KEY")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(NewsResponse::class.java)
        }
    }
}