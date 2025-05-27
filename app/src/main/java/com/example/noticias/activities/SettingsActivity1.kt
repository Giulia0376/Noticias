package com.example.noticias.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.noticias.databinding.ActivityDetail2Binding

class SettingsActivity1 : AppCompatActivity() {

    lateinit var binding: ActivityDetail2Binding // <- plural

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetail2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val prefs = getSharedPreferences("news_app_prefs", MODE_PRIVATE)
    }
}