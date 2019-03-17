package sayan.banerjee.newsapp.news.model.service

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import sayan.banerjee.newsapp.news.model.News

interface INewsService {

    @GET("top-headlines")
    fun getTopHeadlines(
            @Query("country") country: String,
            @Query("apiKey") apiKey: String
    ): Flowable<News>
}