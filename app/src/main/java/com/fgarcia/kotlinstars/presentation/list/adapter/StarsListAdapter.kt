package com.fgarcia.kotlinstars.presentation.list.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.fgarcia.kotlinstars.domain.model.ItemStar

class StarsListAdapter: PagingDataAdapter<ItemStar, ItemStarsViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemStarsViewHolder.create(parent)

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