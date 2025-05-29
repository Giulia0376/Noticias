package com.example.noticias.activities
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noticias.R
import com.example.noticias.adapters.NewAdapter
import com.example.noticias.api.NewsService
import com.example.noticias.data.Noticias
import com.example.noticias.databinding.ActivityMainBinding
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//Para mostrar las noticias

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: NewAdapter

    var noticiaslist: List<Noticias> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  // Asegúrate que esté definido

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // traducir al español
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(TranslateLanguage.ENGLISH)
            .setTargetLanguage(TranslateLanguage.SPANISH)
            .build()
        val translator = Translation.getClient(options)

// Descargar el modelo si es necesario
        translator.downloadModelIfNeeded()
            .addOnSuccessListener {
                translator.translate("Hello, how are you?")
                    .addOnSuccessListener { translatedText ->
                        Log.d("Traductor", translatedText) // Muestra: "Hola, ¿cómo estás?"
                    }
            }



        //setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //menu color
        supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#FF0000")))



        adapter = NewAdapter(noticiaslist) { position ->
            val noticias = noticiaslist[position]

            val intent = Intent(this,DetailActivity2::class.java)
            //ojo aqui
            intent.putExtra(DetailActivity2.NOTICIAS_TITLE, noticias.title)
            intent.putExtra(DetailActivity2.NOTICIAS_IMAGE, noticias.urlToImage)
            intent.putExtra(DetailActivity2.NOTICIAS_DESCRIPTION, noticias.description)
            intent.putExtra(DetailActivity2.NOTICIAS_CONTENT, noticias.content)
            intent.putExtra(DetailActivity2.NOTICIAS_AUTHOR, noticias.author)
            intent.putExtra(DetailActivity2.NOTICIAS_PUBLISHED, noticias.publishedAt)
            intent.putExtra(DetailActivity2.NOTICIAS_URL, noticias.url)
            intent.putExtra(DetailActivity2.NOTICIAS_SOURCE_ID, noticias.source.id)
            intent.putExtra(DetailActivity2.NOTICIAS_SOURCE_NAME, noticias.source.name)
            startActivity(intent)

        }

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        getTopNews()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_search -> {
                startActivity(Intent(this, SettingsActivity1::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun getTopNews() {
        CoroutineScope(Dispatchers.IO).launch {
            // Llamada en segundo plano
            val result = NewsService.getInstance().getTopNews()
            runOnUiThread {
                // Modificar UI
                noticiaslist = result.articles
                adapter.updateItems(noticiaslist)
            }
        }
    }
}