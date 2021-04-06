package com.muratguc.roofstack.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.muratguc.roofstack.R
import com.muratguc.roofstack.data.model.Article
import com.muratguc.roofstack.databinding.ItemNewsListBinding

/**
 * Created by Murat Güç on 2/1/2021.
 */
class NewsListRVA :
    PagingDataAdapter<Article, NewsListRVA.RepoViewHolder>(RepoComparator) {

    var onItemClick: ((Article) -> Unit)? = null
    var goWebPage: ((String) -> Unit)? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.context),
                R.layout.item_news_list,
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val uiModel = getItem(position)
        uiModel.let {
            holder.bind(uiModel!!)
        }
    }

    inner class RepoViewHolder(private val binding: ItemNewsListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Article) {
            binding.article = item
            binding.root.setOnClickListener {
                onItemClick?.invoke(item)
            }

            binding.goWebPage.setOnClickListener {
                goWebPage?.invoke(item.url)
            }
        }
    }
}

object RepoComparator : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}