package com.lira.footballnews.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lira.footballnews.data.remote.FootballNewsApi
import com.lira.footballnews.domain.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel : ViewModel() {

    private val service: FootballNewsApi

    private val _news = MutableLiveData<ArrayList<News>>().apply {

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://sblvitor.github.io/football-news-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(FootballNewsApi::class.java)
        findNews()

    }

    private fun MutableLiveData<ArrayList<News>>.findNews() {
        service.getNews().enqueue(object : Callback<ArrayList<News>> {
            override fun onResponse(
                call: Call<ArrayList<News>>,
                response: Response<ArrayList<News>>
            ) {
                if (response.isSuccessful) {
                    value = response.body()!!
                } else {
                    //TODO pensar em uma estrategia de tratamento de erros
                }
            }

            override fun onFailure(call: Call<ArrayList<News>>, t: Throwable) {
                //TODO pensar em uma estrategia de tratamento de erros
            }

        })
    }

    val news: LiveData<ArrayList<News>> = _news
}