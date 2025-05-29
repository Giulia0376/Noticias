package com.example.noticias.data

data class NoticiasSearchResponse(
    val articles: List<Noticias>
)
data class Noticias(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)

data class Source(
    val id: String?,
    val name: String?
)
data class Usuario(
    val nombre: String,
    val email: String,
    val edad: Int = 0
)

