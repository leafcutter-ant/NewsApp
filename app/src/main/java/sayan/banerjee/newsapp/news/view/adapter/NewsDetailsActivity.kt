package sayan.banerjee.newsapp.news.view.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import kotlinx.android.synthetic.main.activity_news_details.*
import sayan.banerjee.newsapp.R
import sayan.banerjee.newsapp.common.BaseActivity
import sayan.banerjee.newsapp.common.CommonUtils
import sayan.banerjee.newsapp.common.CommonUtils.Companion.ARTICLE_KEY
import sayan.banerjee.newsapp.news.model.common.Article


class NewsDetailsActivity : BaseActivity() {

    private var mArticle: Article? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        getBundleArticle()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun invokeWebView(article: Article) {
        val webViewURL = article.urlToNews
        webView_news.settings.javaScriptEnabled = true
        webView_news.isHorizontalScrollBarEnabled = false
        webView_news.loadUrl(webViewURL)
    }

    private fun getBundleArticle() {
        val bundle = intent.extras
        mArticle = bundle?.getParcelable(ARTICLE_KEY)
    }

    private fun showWebView() {
        webView_news.visibility = VISIBLE
    }

    private fun hideWebView() {
        webView_news.visibility = GONE
    }

    private fun showNativeView() {
        imageView_person.visibility = VISIBLE
        textView_author_details.visibility = VISIBLE
        textView_title_details.visibility = VISIBLE
        textView_desc_details.visibility = VISIBLE
        textView_publishedAt_details.visibility = VISIBLE
        textView_content_details.visibility = VISIBLE
    }

    private fun hideNativeView() {
        imageView_person.visibility = GONE
        textView_author_details.visibility = GONE
        textView_title_details.visibility = GONE
        textView_desc_details.visibility = GONE
        textView_publishedAt_details.visibility = GONE
        textView_content_details.visibility = GONE
    }

    private fun setOfflineNativeViewData() {
        mArticle?.let { CommonUtils.configureGlideOnline(imageView_person, it, applicationContext, 200, 200) }
        textView_author_details.text = mArticle?.author
        textView_title_details.text = mArticle?.title
        textView_desc_details.text = mArticle?.description
        textView_publishedAt_details.text = mArticle?.publishedAt
        textView_content_details.text = mArticle?.content
    }

    private fun setOnlineWebViewData() = mArticle?.let { invokeWebView(it) }

    override fun initOnlineFlow() {
        showWebView()
        hideNativeView()
        setOnlineWebViewData()
    }

    override fun initOfflineFlow() {
        hideWebView()
        showNativeView()
        setOfflineNativeViewData()
        Log.i(TAG, "initOfflineFlow:: $mArticle")
    }

}
