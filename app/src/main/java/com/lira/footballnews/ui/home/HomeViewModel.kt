package com.lira.footballnews.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lira.footballnews.data.FootballNewsRepository
import com.lira.footballnews.domain.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {


    enum class State{
        DOING, DONE, ERROR
    }

    private val _news = MutableLiveData<List<News>>().apply {
        findNews()
    }

    private val _state = MutableLiveData<State>()

    private fun MutableLiveData<List<News>>.findNews() {
        //_state.value = State.DOING
        FootballNewsRepository.remoteApi.getNews().enqueue(object : Callback<List<News>> {
            override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                if (response.isSuccessful) {
                    value = response.body()!!
                    //_state.value = State.DONE
                } else {
                    //_state.value = State.ERROR
                }
            }

            override fun onFailure(call: Call<List<News>>, t: Throwable) {
                t.printStackTrace()
                //_state.value = State.ERROR
            }

        })
    }

    val news: LiveData<List<News>> = _news

    val states: LiveData<State> = _state

    suspend fun saveNews(news: News){
        FootballNewsRepository.localDb.newsDao().insert(news)
    }


}