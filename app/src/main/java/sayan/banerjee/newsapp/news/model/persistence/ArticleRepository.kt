package sayan.banerjee.newsapp.news.model.persistence

import android.app.Application
import sayan.banerjee.newsapp.common.BaseRepository
import sayan.banerjee.newsapp.news.model.persistence.listeners.IAddArticleListener
import sayan.banerjee.newsapp.news.model.persistence.listeners.IGetArticlesListener
import sayan.banerjee.newsapp.news.view.adapter.ArticleAdapter

class ArticleRepository(application: Application) : BaseRepository() {

    private var mArticleDAO: IArticleDAO

    init {
        val articleDatabase = ArticleDatabase.getInstance(application)
        mArticleDAO = articleDatabase!!.articleDao()
    }

    fun getArticlesOffline(listener: IGetArticlesListener)
            = getOfflineArticles(mArticleDAO, listener)

    fun getArticlesOnline(articleAdapter: ArticleAdapter, listener: IAddArticleListener)
            = getOnlineArticles(articleAdapter, mArticleDAO, listener)

    companion object {
        val TAG: String = ArticleRepository::class.java.simpleName
    }
}