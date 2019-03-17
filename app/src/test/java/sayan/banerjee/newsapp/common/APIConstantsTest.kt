package sayan.banerjee.newsapp.common

import org.junit.Assert
import org.junit.Test

class APIConstantsTest {
    @Test
    fun getNewsBaseURLTest() {
        Assert.assertEquals("https://newsapi.org/v2/", CommonUtils.getNewsBaseURL())
        Assert.assertNotEquals("sayan", CommonUtils.getNewsBaseURL())
    }
}