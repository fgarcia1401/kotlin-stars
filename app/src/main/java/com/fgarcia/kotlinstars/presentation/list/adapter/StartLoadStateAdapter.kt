package com.fgarcia.kotlinstars.presentation.list.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.fgarcia.kotlinstars.presentation.list.adapter.viewholder.StarsLoadMoreStateViewHolder

class StartLoadStateAdapter(
    private val retry: () -> Unit
) :LoadStateAdapter<StarsLoadMoreStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = StarsLoadMoreStateViewHolder.create(parent, retry)

    override fun onBindViewHolder(
        holder: StarsLoadMoreStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)

}