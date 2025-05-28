package com.example.noticias.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.noticias.R
import com.example.noticias.data.Noticias
import com.example.noticias.data.Source
import com.example.noticias.databinding.ActivityDetail2Binding
import com.example.noticias.utils.SessionManager
import com.squareup.picasso.Picasso
import androidx.core.net.toUri


class DetailActivity2 : AppCompatActivity() {
    companion object {
        const val NOTICIAS_AUTHOR       = "NOTICIAS_AUTHOR"
        const val NOTICIAS_TITLE        = "NOTICIAS_TITLE"
        const val NOTICIAS_DESCRIPTION  = "NOTICIAS_DESCRIPTION"
        const val NOTICIAS_URL          = "NOTICIAS_URL"
        const val NOTICIAS_IMAGE        = "NOTICIAS_IMAGE"
        const val NOTICIAS_PUBLISHED    = "NOTICIAS_PUBLISHED"
        const val NOTICIAS_CONTENT      = "NOTICIAS_CONTENT"
        const val NOTICIAS_SOURCE_ID    = "NOTICIAS_SOURCE_ID"
        const val NOTICIAS_SOURCE_NAME  = "NOTICIAS_SOURCE_NAME"
    }

    private lateinit var binding: ActivityDetail2Binding
    lateinit var noticias: Noticias
    lateinit var session: SessionManager
    var isFavorite = false
    lateinit var favoriteMenuItem: MenuItem


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

        session = SessionManager(this)

        val title = intent.getStringExtra(NOTICIAS_TITLE)!!
        val urlToImage = intent.getStringExtra(NOTICIAS_IMAGE)!!
        val description = intent.getStringExtra(NOTICIAS_DESCRIPTION)!!
        val content = intent.getStringExtra(NOTICIAS_CONTENT)!!
        val author = intent.getStringExtra(NOTICIAS_AUTHOR)!!
        val publishedAt = intent.getStringExtra(NOTICIAS_PUBLISHED)!!
        val url = intent.getStringExtra(NOTICIAS_URL)!!
        val sourceId = intent.getStringExtra(NOTICIAS_SOURCE_ID)
        val sourceName = intent.getStringExtra(NOTICIAS_SOURCE_NAME)

        val source = Source(sourceId, sourceName)
        noticias = Noticias(source, author, title, description, url, urlToImage, publishedAt, content)

        isFavorite = false//session.getFavoriteNoticias() == NOTICIAS_ID

        binding.titleTextView.text = noticias.title
        binding.descriptionTextView.text = "${noticias.description}\n\n${noticias.content}"
        Picasso.get().load(noticias.urlToImage).into(binding.noticiasImageView)

        binding.showInBrowserButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, noticias.url.toUri())
            startActivity(browserIntent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.new_menu, menu)

        favoriteMenuItem = menu.findItem(R.id.menu_favorite)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorite -> {
                if (isFavorite) {
                    session.setFavoriteNoticias("")

                } else {
                    //session.setFavoriteNoticias(NOTICIAS_ID)
                }
                isFavorite = !isFavorite
                setFavoriteIcon()

                return true
            }



            R.id.menu_share -> {

                val sendIntent = Intent()
                sendIntent.setAction(Intent.ACTION_SEND)
                sendIntent.putExtra(Intent.EXTRA_TEXT, noticias.url)
                sendIntent.setType("text/plain")

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                return true
            }

            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    fun setFavoriteIcon() {
        if (isFavorite) {
            favoriteMenuItem.setIcon(R.drawable.ic_favorite_selected)
        } else {
            favoriteMenuItem.setIcon(R.drawable.ic_favorite)
        }
    }
}



