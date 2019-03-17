package sayan.banerjee.newsapp.news.model.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import sayan.banerjee.newsapp.news.model.common.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun articleDao(): IArticleDAO

    companion object {
        @Volatile
        private var INSTANCE: ArticleDatabase? = null

        fun getInstance(context: Context): ArticleDatabase? {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: createArticleDatabase(context).also { INSTANCE = it }
            }
        }

        private fun createArticleDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext, ArticleDatabase::class.java, "article_database")
                        .fallbackToDestructiveMigration()
                        .build()

        private fun destroyInstance() {
            INSTANCE = null
        }
    }
}