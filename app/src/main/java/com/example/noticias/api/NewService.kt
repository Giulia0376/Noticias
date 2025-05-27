package com.example.noticias.api
import com.example.noticias.data.NoticiasSearchResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


//Configuración de Retrofit para consumir la API

interface NewsService {

    @GET("top-headlines")
    suspend fun getTopNews(
        @Query("language") language: String = "en",
        @Query("apiKey") apiKey: String = "64ecdfc345a6447b97e653cb059b0baf"
    ): NoticiasSearchResponse

    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String = "64ecdfc345a6447b97e653cb059b0baf"
    ): NoticiasSearchResponse



    companion object {
        fun getInstance(): NewsService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(NewsService::class.java)
        }
    }
}

//interface NewService {
//    @GET("search/{name}")
//    suspend fun findSuperheroesByName(@Path("name") name: String): NoticiasSearchResponse
//
//    @GET("{charcter-id}")
//    suspend fun findSuperheroById(@Path("charcter-id") id: String): Noticias
//
//    companion object {
//        fun getInstance(): NewService {
//            val retrofit = Retrofit.Builder()
//                .baseUrl("https://newsapi.org/v2/everything?q=apple&from=2025-05-21&to=2025-05-21&sortBy=popularity&apiKey=API_KEY")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//            return retrofit.create(NewService::class.java)
//        }
//    }
//}


