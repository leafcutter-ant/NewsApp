package sayan.banerjee.newsapp.news.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import sayan.banerjee.newsapp.news.model.common.Article

class News {

    @SerializedName("status")
    @Expose
    var status: String? = null
    @SerializedName("totalResults")
    @Expose
    var totalResults: Int? = null
    @SerializedName("articles")
    @Expose
    var articles: List<Article>? = null

}
