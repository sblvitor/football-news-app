package com.lira.footballnews.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lira.footballnews.databinding.NewsItemBinding
import com.lira.footballnews.domain.News

class NewsAdapter(private val items: ArrayList<News>): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

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
    }

    override fun getItemCount(): Int {
        return items.size
    }

}