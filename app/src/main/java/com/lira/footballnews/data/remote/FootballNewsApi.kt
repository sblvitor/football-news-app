package com.lira.footballnews.data.remote

import com.lira.footballnews.domain.News
import retrofit2.Call
import retrofit2.http.GET

interface FootballNewsApi {

    @GET("news.json")
    fun getNews(): Call<ArrayList<News>>

}