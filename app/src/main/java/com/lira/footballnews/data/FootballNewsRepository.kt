package com.lira.footballnews.data

import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.lira.footballnews.App
import com.lira.footballnews.data.local.AppDatabase
import com.lira.footballnews.data.remote.FootballNewsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FootballNewsRepository {

    private const val REMOTE_API_URL: String = "https://sblvitor.github.io/football-news-api/"
    private const val LOCAL_DB_NAME: String = "football-news"

    val remoteApi: FootballNewsApi = Retrofit.Builder()
        .baseUrl(REMOTE_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(FootballNewsApi::class.java)

    val localDb = Room.databaseBuilder(App.instance, AppDatabase::class.java, LOCAL_DB_NAME).build()

}