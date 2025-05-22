package com.example.noticias.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.noticias.R
import com.example.noticias.adapters.NewsAdapter
import com.example.noticias.data.News
import com.example.noticias.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//Para mostrar las noticias
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewsAdapter
    private lateinit var newsList: List<News>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = NewsAdapter(newsList) { article ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        fetchNews()
    }

    private fun fetchNews() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitInstance.api.getNews(
                "apple",
                "2025-05-19",
                "2025-05-19",
                "popularity",
                "YOUR_API_KEY"
            )
            if (response.isSuccessful) {
                newsList = response.body()?.articles ?: emptyList()
                withContext(Dispatchers.Main) {
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}
