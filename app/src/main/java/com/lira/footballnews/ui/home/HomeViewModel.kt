package com.lira.footballnews.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lira.footballnews.domain.News

class HomeViewModel : ViewModel() {

    private val _news = MutableLiveData<ArrayList<News>>().apply {
        val news: ArrayList<News> = ArrayList()
        news.add(News("Santos amassa Palmeiras", "Jogo teve o placar de 4x0"))
        news.add(News("Brasil vs França, Quem Leva a Melhor?", "Equipes estão muito equilibradas"))
        news.add(News("Noticia de teste", "Algum subtitulo maneiro"))

        value = news
    }
    val news: LiveData<ArrayList<News>> = _news
}