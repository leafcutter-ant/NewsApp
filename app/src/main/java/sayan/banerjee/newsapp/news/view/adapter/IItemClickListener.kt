package sayan.banerjee.newsapp.news.view.adapter

import sayan.banerjee.newsapp.news.model.common.Article

interface IItemClickListener {
    fun onItemClicked(position: Int, article: Article)
}