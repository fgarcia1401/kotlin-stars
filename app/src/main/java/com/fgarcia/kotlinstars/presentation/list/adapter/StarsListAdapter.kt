package com.fgarcia.kotlinstars.presentation.list.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.fgarcia.common.imageloader.ImageLoader
import com.fgarcia.kotlinstars.domain.model.ItemStar
import com.fgarcia.kotlinstars.presentation.list.adapter.viewholder.ItemStarsViewHolder
import javax.inject.Inject

class StarsListAdapter @Inject constructor(
    private val imageLoader: ImageLoader
): PagingDataAdapter<ItemStar, ItemStarsViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemStarsViewHolder.create(parent, imageLoader)

    override fun onBindViewHolder(holder: ItemStarsViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ItemStar>() {

            override fun areItemsTheSame(
                oldItem: ItemStar,
                newItem: ItemStar
            ) = oldItem.name == newItem.name

            override fun areContentsTheSame(
                oldItem: ItemStar,
                newItem: ItemStar
            ) = oldItem == newItem
        }
    }

}