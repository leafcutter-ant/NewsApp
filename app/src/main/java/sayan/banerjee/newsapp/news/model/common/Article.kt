package sayan.banerjee.newsapp.news.model.common

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "article_table")
data class Article (

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "articleId")
        var articleId: Int,

        @SerializedName("author")
        @Expose
        @ColumnInfo(name = "author")
        var author: String,

        @SerializedName("title")
        @Expose
        @ColumnInfo(name = "title")
        var title: String,

        @SerializedName("urlToImage")
        @Expose
        @ColumnInfo(name = "urlToImage")
        var urlToImage: String,

        @SerializedName("url")
        @Expose
        @ColumnInfo(name = "urlToNews")
        var urlToNews: String,

        @SerializedName("description")
        @Expose
        @ColumnInfo(name = "description")
        var description: String,

        @SerializedName("publishedAt")
        @Expose
        @ColumnInfo(name = "publishedAt")
        var publishedAt: String,

        @SerializedName("content")
        @Expose
        @ColumnInfo(name = "content")
        var content: String

):Parcelable {

        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString()) {
        }

        @Ignore
        constructor(author: String, title: String, urlToImage: String, urlToNews: String, description: String, publishedAt: String, content: String)
                : this(0, author, title, urlToImage, urlToNews, description, publishedAt, content)

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeInt(articleId)
                parcel.writeString(author)
                parcel.writeString(title)
                parcel.writeString(urlToImage)
                parcel.writeString(urlToNews)
                parcel.writeString(description)
                parcel.writeString(publishedAt)
                parcel.writeString(content)
        }

        override fun describeContents(): Int {
                return 0
        }

        companion object CREATOR : Parcelable.Creator<Article> {
                override fun createFromParcel(parcel: Parcel): Article {
                        return Article(parcel)
                }

                override fun newArray(size: Int): Array<Article?> {
                        return arrayOfNulls(size)
                }
        }
}
