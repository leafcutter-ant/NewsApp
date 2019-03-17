package sayan.banerjee.newsapp.news.view

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_news.*
import sayan.banerjee.newsapp.R
import sayan.banerjee.newsapp.common.BaseActivity
import sayan.banerjee.newsapp.common.CommonUtils.Companion.ARTICLE_KEY
import sayan.banerjee.newsapp.common.network.util.displayToast
import sayan.banerjee.newsapp.news.model.common.Article
import sayan.banerjee.newsapp.news.model.persistence.listeners.IAddArticleListener
import sayan.banerjee.newsapp.news.model.persistence.listeners.IGetArticlesListener
import sayan.banerjee.newsapp.news.view.adapter.ArticleAdapter
import sayan.banerjee.newsapp.news.view.adapter.IItemClickListener
import sayan.banerjee.newsapp.news.view.adapter.NewsDetailsActivity
import sayan.banerjee.newsapp.news.viewmodel.ArticleViewModel


class NewsActivity : BaseActivity(), IAddArticleListener, IGetArticlesListener, IItemClickListener {

    private var mArticlesOnline: MutableList<Article> = mutableListOf()
    private var mArticleAdapterOnline: ArticleAdapter? = null
    private var mArticleViewModel: ArticleViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        initViews()
        initArticleViewModel()
    }

    override fun onResume() {
        super.onResume()
        makeRequestForTopHeadLinesOnline()
    }

    private fun initViews() {
        val linearLayoutManager = LinearLayoutManager(this)
        recycler_news.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recycler_news.layoutManager = linearLayoutManager
    }

    private fun initArticleViewModel() {
        mArticleViewModel = ViewModelProviders.of(this@NewsActivity).get(ArticleViewModel::class.java)
    }

    private fun setAdapterOnline() {
        mArticleAdapterOnline = ArticleAdapter(this, mArticlesOnline, this)
        recycler_news.adapter = mArticleAdapterOnline
    }

    private fun setAdapterOffline(articlesOffline: MutableList<Article>) {
        mArticleAdapterOnline = ArticleAdapter(this, articlesOffline, this)
        recycler_news.adapter = mArticleAdapterOnline
    }

    private fun makeRequestForTopHeadLinesOnline() {
        mArticleAdapterOnline?.let {
            mArticleViewModel!!.getArticlesOnline(it, this)
        }
    }

    private fun sendBundleOfArticles(article: Article) {
        val bundle = Bundle()
        val intent = Intent(this, NewsDetailsActivity::class.java)
        bundle.putParcelable(ARTICLE_KEY, article)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun initOnlineFlow() {
        setAdapterOnline()
        makeRequestForTopHeadLinesOnline()
    }

    override fun initOfflineFlow() {
        mArticleViewModel!!.getArticlesOffline(this)
    }

    override fun onItemClicked(position: Int, article: Article) {
        Log.i(TAG, "Clicked: $position")
        sendBundleOfArticles(article)
    }

    override fun onArticleAdded(articleOffline: Article) {
        Log.i(TAG, "onArticleAdded")
    }

    override fun onAddArticleError(errorMessage: String) {
        Log.i(TAG, "onAddArticleError")
    }

    override fun onArticlesPopulated(articles: MutableList<Article>) {
        Log.i(TAG, "onArticlesPopulated: $articles")
        if (!articles.isNullOrEmpty()) {
            imageView_no_internet.visibility = GONE
            setAdapterOffline(articles)
        } else {
            imageView_no_internet.visibility = VISIBLE
            this.displayToast("No data in Room")
        }
    }

    override fun onGetArticlesError(errorMessage: String) {
        Log.i(TAG, "onGetArticlesError")
    }
}
