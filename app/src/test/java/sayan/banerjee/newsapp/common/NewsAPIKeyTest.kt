package sayan.banerjee.newsapp.common

import org.junit.Assert
import org.junit.Test

class NewsAPIKeyTest {

    @Test
    fun getNewsApiKeyTest() {
        Assert.assertEquals("be18e2d91b384a80b5058db52622dbbe", NewsAPIKey.getNewsApiKey())
        Assert.assertNotEquals("abcd", NewsAPIKey.getNewsApiKey())
    }
}