package sayan.banerjee.newsapp.news.model.persistence

import android.arch.persistence.room.Room
import android.content.Context
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import sayan.banerjee.newsapp.common.BaseRepository
import sayan.banerjee.newsapp.news.model.common.Article
import sayan.banerjee.newsapp.news.model.persistence.listeners.IAddArticleListener
import sayan.banerjee.newsapp.news.view.adapter.ArticleAdapter
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ArticleDatabaseTest {
    private lateinit var articleDao: IArticleDAO
    private lateinit var db: ArticleDatabase
    private lateinit var adapter: ArticleAdapter

    @Mock
    var listener: IAddArticleListener? =null

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder<ArticleDatabase>(
                context!!, ArticleDatabase::class.java).build()
        articleDao = db.articleDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val article = Article("a","b","c","d","e","f","g")
        BaseRepository.getOnlineArticles(adapter, articleDao, listener!!)
        articleDao.insertArticleOffline(article)
    }
}