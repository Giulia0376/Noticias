package com.example.noticias.data

import androidx.annotation.ColorRes

import com.google.gson.TypeAdapter
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import javax.xml.transform.Source

data class Article(
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String
)

data class NewsResponse(
    val articles: List<Article>
)



