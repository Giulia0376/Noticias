package com.example.noticias.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.noticias.R
import com.example.noticias.data.Noticias
import com.example.noticias.databinding.ActivityDetail2Binding


class DetailActivity2 : AppCompatActivity() {
    companion object {
        const val NOTICIAS_ID = "NOTICIAS_ID"
    }

    private lateinit var binding: ActivityDetail2Binding
    lateinit var noticias: Noticias

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetail2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id = intent.getStringExtra(NOTICIAS_ID)!!
    }
}