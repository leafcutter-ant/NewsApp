package sayan.banerjee.newsapp.common

class NewsAPIKey {
    companion object {
        private const val NEWS_AUTH_KEY = "be18e2d91b384a80b5058db52622dbbe"
        fun getNewsApiKey(): String = NEWS_AUTH_KEY
    }
}