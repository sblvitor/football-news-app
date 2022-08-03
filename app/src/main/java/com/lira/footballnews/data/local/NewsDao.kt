package com.lira.footballnews.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lira.footballnews.domain.News

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(news: News)

    @Query("SELECT * FROM news WHERE favorite = 1")
    fun loadFavoriteNews(): LiveData<List<News>>

}