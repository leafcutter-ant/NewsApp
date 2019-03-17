package sayan.banerjee.newsapp.news.model.persistence.listeners

import sayan.banerjee.newsapp.news.model.common.Article

interface IAddArticleListener {
    fun onArticleAdded(articleOffline: Article)
    fun onAddArticleError(errorMessage: String)
}