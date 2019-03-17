package sayan.banerjee.newsapp.common

import android.util.Log
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import sayan.banerjee.newsapp.news.model.common.Article
import sayan.banerjee.newsapp.news.model.persistence.ArticleRepository
import sayan.banerjee.newsapp.news.model.persistence.IArticleDAO
import sayan.banerjee.newsapp.news.model.persistence.listeners.IAddArticleListener
import sayan.banerjee.newsapp.news.model.persistence.listeners.IGetArticlesListener
import sayan.banerjee.newsapp.news.model.service.INewsService
import sayan.banerjee.newsapp.news.model.service.ServiceGenerator
import sayan.banerjee.newsapp.news.view.adapter.ArticleAdapter

open class BaseRepository {
    companion object {

        private fun insertOfflineArticle(articleDAO: IArticleDAO, articleOffline: Article, listener: IAddArticleListener) {
            Completable.fromAction { articleDAO.insertArticleOffline(articleOffline) }
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : CompletableObserver {
                        override fun onComplete() {
                            listener.onArticleAdded(articleOffline)
                        }

                        override fun onSubscribe(d: Disposable) {
                        }

                        override fun onError(e: Throwable) {
                            listener.onAddArticleError(e.message!!)
                        }
                    })
        }

        fun getOfflineArticles(articleDAO: IArticleDAO, listener: IGetArticlesListener) =
                articleDAO.getArticlesOffline()
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            listener.onArticlesPopulated(it)
                        }, {
                            listener.onGetArticlesError(it.message!!)
                        })!!

        fun getOnlineArticles(articleAdapter: ArticleAdapter, articleDAO: IArticleDAO, listener: IAddArticleListener) {
            val moviesService = ServiceGenerator.createService(INewsService::class.java)
            moviesService.getTopHeadlines(APIConstants.COUNTRY, CommonUtils.getNewsAPIKey())
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.computation())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        it.articles?.let { it1 ->
                            articleAdapter.setDataOnline(it1)
                            for (index in 0 until it1.size) {
                                if (it1[index].author.isNullOrEmpty() || it1[index].title.isNullOrEmpty()
                                        || it1[index].urlToImage.isNullOrEmpty() || it1[index].urlToNews.isNullOrEmpty()
                                        || it1[index].description.isNullOrEmpty() || it1[index].publishedAt.isNullOrEmpty()
                                        || it1[index].content.isNullOrEmpty()) {
                                    continue
                                } else {
                                    insertOfflineArticle(articleDAO, it1[index], listener)
                                    //Log.i("getOnlineArticles:: ", it1[index].author)
                                }
                            }
                        }
                    }, {
                        Log.d(ArticleRepository.TAG, "getOnlineArticles Error")
                    })!!
        }
    }
}