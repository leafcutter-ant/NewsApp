package sayan.banerjee.newsapp.news.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_article.view.*
import sayan.banerjee.newsapp.R
import sayan.banerjee.newsapp.common.CommonUtils
import sayan.banerjee.newsapp.news.model.common.Article


class ArticleAdapter(val context: Context, private val articles: MutableList<Article>, private val listener: IItemClickListener)
    : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ArticleViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_article, parent, false)
        return ArticleViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(articleViewHolder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        articleViewHolder.bindData(article, position)
    }

    fun setDataOnline(articleList: List<Article>) {
        articles.addAll(articleList)
        notifyDataSetChanged()
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var selectedItem: Article? = null
        private var selectedPosition: Int = -1


        init {
            itemView.textView_more.setOnClickListener {
                if (selectedItem != null) {
                    //context.displayToast("${selectedItem!!.author}: ${selectedPosition + 1}")
                    listener.onItemClicked(adapterPosition + 1, selectedItem!!)
                }
            }
        }

        fun bindData(article: Article?, position: Int) {
            article?.let {
                if (!(it.author.isNullOrEmpty() || it.title.isNullOrEmpty())) {
                    CommonUtils.configureGlideOnline(itemView.imageView_poster, it, context)
                    itemView.textView_author.text = it.author
                    itemView.textView_title.text = it.title

                    this.selectedItem = it
                    this.selectedPosition = position
                }
            }
        }
    }
}