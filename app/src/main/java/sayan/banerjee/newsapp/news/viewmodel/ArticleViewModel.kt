package sayan.banerjee.newsapp.news.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import sayan.banerjee.newsapp.news.model.persistence.ArticleRepository
import sayan.banerjee.newsapp.news.model.persistence.listeners.IAddArticleListener
import sayan.banerjee.newsapp.news.model.persistence.listeners.IGetArticlesListener
import sayan.banerjee.newsapp.news.view.adapter.ArticleAdapter

class ArticleViewModel(application: Application) : AndroidViewModel(application) {

    private var mArticleRepo: ArticleRepository = ArticleRepository(application)

    fun getArticlesOffline(listener: IGetArticlesListener) {
        mArticleRepo.getArticlesOffline(listener)
    }

    fun getArticlesOnline(articleAdapter: ArticleAdapter, listener: IAddArticleListener) {
        mArticleRepo.getArticlesOnline(articleAdapter, listener)
    }
}