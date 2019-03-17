package sayan.banerjee.newsapp.news.model.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import io.reactivex.Flowable
import android.arch.persistence.room.Query
import io.reactivex.Single
import sayan.banerjee.newsapp.news.model.common.Article

@Dao
interface IArticleDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertArticleOffline(articleOffline: Article)

    @Query("select * from article_table")
    fun getArticlesOffline(): Flowable<MutableList<Article>>

    @Query("select * from article_table where urlToNews = :urlToNews")
    fun getSpecificArticle(urlToNews: String): Single<Article>
}