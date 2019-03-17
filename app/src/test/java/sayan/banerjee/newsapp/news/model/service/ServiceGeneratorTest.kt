package sayan.banerjee.newsapp.news.model.service

import org.junit.Test

class ServiceGeneratorTest {
    @Test
    fun createServiceTest() {
        ServiceGenerator.createService(INewsService::class.java)
    }
}