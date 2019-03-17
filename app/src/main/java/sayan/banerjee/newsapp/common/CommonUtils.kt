package sayan.banerjee.newsapp.common

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import sayan.banerjee.newsapp.R
import sayan.banerjee.newsapp.news.model.common.Article


class CommonUtils {

    companion object {

        const val ARTICLE_KEY = "article_key"

        fun configureGlideOnline(imageView: ImageView
                                 , article: Article
                                 , context: Context
                                 , width: Int = 50
                                 , height: Int = 50) {
            val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)

            Glide
                    .with(context)
                    .applyDefaultRequestOptions(requestOptions
                            .override(width, height)
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .load(article.urlToImage)
                    .into(imageView)
        }

        fun getNewsAPIKey(): String = NewsAPIKey.getNewsApiKey()

        fun getNewsBaseURL(): String = APIConstants.NEWS_BASE_URL
    }
}