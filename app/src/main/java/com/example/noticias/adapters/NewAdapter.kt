package com.example.noticias.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.noticias.data.News
import com.example.noticias.data.NewsSearchResponse
import com.example.noticias.databinding.ItemNewBinding

import com.squareup.picasso.Picasso

class NewsAdapter(
    private val newsList: List<News>,
    private val onClick: (News) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = newsList[position]
        holder.bind(article, onClick)
    }

    override fun getItemCount(): Int = newsList.size

    class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: news, onClick: (Article) -> Unit) {
            binding.title.text = article.title
            binding.description.text = article.description
            Glide.with(binding.image.context).load(article.urlToImage).into(binding.image)
            binding.root.setOnClickListener { onClick(article) }
        }
    }
}


