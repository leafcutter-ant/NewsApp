package sayan.banerjee.newsapp.news.model.persistence.listeners

import sayan.banerjee.newsapp.news.model.common.Article

interface IGetArticlesListener {
    fun onArticlesPopulated(articles: MutableList<Article>)
    fun onGetArticlesError(errorMessage: String)
}