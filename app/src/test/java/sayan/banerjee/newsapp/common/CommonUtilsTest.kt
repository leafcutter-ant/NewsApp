package sayan.banerjee.newsapp.common

import org.junit.Assert
import org.junit.Test
import sayan.banerjee.newsapp.news.model.common.Article

class CommonUtilsTest : BaseUnitTest() {
    @Test
    fun getNewsApiKeyTest() {
        Assert.assertEquals("be18e2d91b384a80b5058db52622dbbe", NewsAPIKey.NEWS_AUTH_KEY)
        Assert.assertNotEquals("abcd", NewsAPIKey.NEWS_AUTH_KEY)
    }

    @Test
    fun getNewsBaseURLTest() {
        Assert.assertEquals("https://newsapi.org/v2/", CommonUtils.getNewsBaseURL())
        Assert.assertNotEquals("sayan", CommonUtils.getNewsBaseURL())
    }

    @Test
    fun configureGlideOnlineTest() {
        val article = Article("Sergey Brin","The Android",
                "https://www.google.com/sayan.png",
                "https://www.google.com/sayan.html",
                "Fundamentals of Android","10:24:60","Content")
        CommonUtils.configureGlideOnline(mImageView, article, mContext, 10,10)
    }
}