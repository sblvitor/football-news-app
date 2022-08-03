package com.lira.footballnews.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lira.footballnews.data.FootballNewsRepository
import com.lira.footballnews.domain.News

class FavoritesViewModel : ViewModel() {

    fun loadFavoriteNews(): LiveData<List<News>> {
        return FootballNewsRepository.localDb.newsDao().loadFavoriteNews()
    }

    suspend fun saveNews(news: News){
        FootballNewsRepository.localDb.newsDao().insert(news)
    }
}