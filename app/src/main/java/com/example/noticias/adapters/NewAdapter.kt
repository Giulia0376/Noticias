package com.example.noticias.adapters
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.noticias.R
import com.example.noticias.data.Noticias
import com.example.noticias.data.NoticiasSearchResponse
import com.example.noticias.databinding.ItemNewsBinding
import com.squareup.picasso.Picasso


class NewAdapter(
    private var items: List<Noticias>,
    private val onClickListener: (Int) -> Unit
) : Adapter<NewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        val superhero = items[position]
        holder.render(superhero)
        holder.itemView.setOnClickListener {
            onClickListener(position)
        }
    }

    fun updateItems(items: List<Noticias>) {
        this.items = items
        notifyDataSetChanged()
    }
}

class NewViewHolder(val binding: ItemNewsBinding) : ViewHolder(binding.root) {


    fun render(noticias: Noticias) {
        binding.nameTextView.text = noticias.title
        Picasso.get().load(noticias.urlToImage).into(binding.avatarImageView)
    }
}



