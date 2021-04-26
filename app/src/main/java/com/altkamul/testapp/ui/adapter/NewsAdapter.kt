package com.altkamul.testapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.altkamul.testapp.R
import com.altkamul.testapp.model.Articles

import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_news.view.*

/*************************
 * Created by AZAT SAYAN *
 *                       *
 * Contact: @theazat     *
 *                       *
 * 23/08/2020 - 2:42 PM  *
 ************************/
class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val differCallBack = object : DiffUtil.ItemCallback<Articles>() {

        override fun areItemsTheSame(oldItem: Articles, newItem: Articles) =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: Articles, newItem: Articles) = oldItem == newItem

    }

    val differ = AsyncListDiffer(this, differCallBack)
    private var onItemClickListener: ((Articles) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_news,
                parent,
                false
            )
        )

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            article?.let {
                Glide.with(this).load(it.urlToImage).into(ivNewsImage)
                tvNewsSource.text = it.source?.name
                tvNewsTitle.text = it.title
                tvNewsDate.text = it.publishedAt

                setOnClickListener {
                    onItemClickListener?.let {
                        it(article)
                    }
                }
            }
        }
    }

    fun setOnItemClickListener(listener: (Articles) -> Unit) {
        onItemClickListener = listener
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}