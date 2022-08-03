package com.lira.footballnews.ui.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.color.MaterialColors
import com.lira.footballnews.R
import com.lira.footballnews.databinding.NewsItemBinding
import com.lira.footballnews.domain.News
import com.squareup.picasso.Picasso

class NewsAdapter(private val items: List<News>, private val favoriteListener: (news: News) -> Unit): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(binding: NewsItemBinding): RecyclerView.ViewHolder(binding.root){
        val ivThumbnail = binding.ivThumbnail
        val tvTitle = binding.tvTitle
        val tvDescription = binding.tvDescription
        val btnOpenLink = binding.btnOpenLink
        val ivFavorite = binding.ivFavorite
        val ivShare = binding.ivShare
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.tvTitle.text = item.title
        holder.tvDescription.text = item.description
        Picasso.get().load(item.image)
            .fit()
            .into(holder.ivThumbnail)

        // Open link in browser
        holder.btnOpenLink.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(item.link)
            holder.itemView.context.startActivity(intent)
        }

        //Share
        holder.ivShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, item.link)
            holder.itemView.context.startActivity(Intent.createChooser(intent, "Share via"))
        }

        //Favorite - evento instanciado pelo fragment
        holder.ivFavorite.setOnClickListener {
            item.favorite = !item.favorite
            favoriteListener.invoke(item)
            notifyItemChanged(position)
        }

        if(!item.favorite){
            holder.ivFavorite.setColorFilter(ContextCompat.getColor(holder.itemView.context, R.color.favorite_inactive))
        }else{
            holder.ivFavorite.setColorFilter(MaterialColors.getColor(holder.ivFavorite,R.attr.colorSecondaryVariant))
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}