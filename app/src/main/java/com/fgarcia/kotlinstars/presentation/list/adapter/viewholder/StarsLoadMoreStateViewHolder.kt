package com.fgarcia.kotlinstars.presentation.list.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadState.Error
import androidx.paging.LoadState.Loading
import androidx.recyclerview.widget.RecyclerView
import com.fgarcia.kotlinstars.databinding.ItemStarLoadingMoreStateBinding

class StarsLoadMoreStateViewHolder (
    itemBinding: ItemStarLoadingMoreStateBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(itemBinding.root) {

    private val binding = ItemStarLoadingMoreStateBinding.bind(itemView)
    private val progressBarLoadingMore = binding.progressLoadingMore
    private val textTryAgain = binding.textTryAgain.also {
        it.setOnClickListener {
            retry()
        }
    }

    fun bind(loadState: LoadState) {
        progressBarLoadingMore.isVisible = loadState is Loading
        textTryAgain.isVisible = loadState is Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): StarsLoadMoreStateViewHolder {
            val itemBinding = ItemStarLoadingMoreStateBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return StarsLoadMoreStateViewHolder(itemBinding, retry)
        }
    }



}