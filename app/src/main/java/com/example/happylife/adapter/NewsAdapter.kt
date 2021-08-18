package com.example.happylife.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.happylife.bean.SimpleNews
import com.example.happylife.databinding.ItemNewsBinding

/**
 * @author by syt
 * on 2021/8/13
 * desc:稿件适配器
 * PagedListAdapter, 与RecyclerView.Adapter的使用区别不大，
 * 只是对getItemCount与getItem进行了重写，因为它使用到了DiffUtil，避免对数据的无用更新。
 */
// 1
class NewsAdapter : PagingDataAdapter<SimpleNews, RecyclerView.ViewHolder>(COMPARATOR) {
    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<SimpleNews>() {
            override fun areItemsTheSame(oldItem: SimpleNews, newItem: SimpleNews): Boolean {
                return oldItem.Id == newItem.Id
            }

            override fun areContentsTheSame(oldItem: SimpleNews, newItem: SimpleNews): Boolean {
                return oldItem == newItem
            }
        }
    }

    // 2
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return NewsListItemHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    // 3
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val simpleNews = getItem(position)
        simpleNews?.let { (holder as NewsListItemHolder).bindSimpleNews(it) }
    }


    // 5
    class NewsListItemHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var simpleNews: SimpleNews? = null

        // 6
        fun bindSimpleNews(item: SimpleNews) {
            simpleNews = item

            binding.tvTitle.text = item.Title
            binding.tvTime.text = item.IssueTime
            // 7
            Glide.with(binding.root.context).load(item.ImgUrl).into(binding.ivImg)
        }
    }
}

