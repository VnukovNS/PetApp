package com.example.petapp.newsListScreen.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.petapp.core.domain.Mapper
import com.example.petapp.core.presentation.adapter.GenericViewHolder
import com.example.petapp.databinding.CustomNewsPreviewBinding

interface NewsAdapter : Mapper.Unit<List<NewsPreviewUi>> {

    class Base(private val clickListener: NewsListener) : RecyclerView.Adapter<NewsViewHolder>(),
        NewsAdapter {

        private val list = mutableListOf<NewsPreviewUi>()


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewsViewHolder(
            CustomNewsPreviewBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            clickListener
        )

        override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
            holder.bind(list[position])
        }

        override fun getItemCount() = list.size

        override fun map(source: List<NewsPreviewUi>) {
            val diff = DiffCallBack(list, source)
            val result = DiffUtil.calculateDiff(diff)
            list.clear()
            list.addAll(source)
            result.dispatchUpdatesTo(this)
        }
    }

}

class NewsViewHolder(
    private val binding: CustomNewsPreviewBinding,
    private val clickListener: NewsListener
) :
    GenericViewHolder<NewsPreviewUi>(binding.root) {

    override fun bind(item: NewsPreviewUi) {
        with(binding) {
//            item.map(newsImage, newsTitle, newsDescription)
            item.map(newsTitle, newsDescription)
            newsImage.setOnClickListener {
                clickListener.openFullNews(item, adapterPosition)
            }
        }
    }
}

class DiffCallBack(
    private val oldList: List<NewsPreviewUi>,
    private val newList: List<NewsPreviewUi>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].title() == newList[newItemPosition].title()

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}
