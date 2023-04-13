package com.fgarcia.kotlinstars.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fgarcia.kotlinstars.R
import com.fgarcia.kotlinstars.databinding.ItemRepositoryBinding
import com.fgarcia.kotlinstars.databinding.ItemRepositoryBinding.inflate
import com.fgarcia.kotlinstars.domain.model.ItemStar

class ItemStarsViewHolder(
    private val itemCharacterBinding: ItemRepositoryBinding
) : RecyclerView.ViewHolder(itemCharacterBinding.root) {


    fun bind(itemStar: ItemStar) = with(itemCharacterBinding) {
        textName.text = itemStar.name
        textStars.text = itemStar.totalStars
        textForks.text = itemStar.totalForks
        Glide.with(itemView)
            .load(itemStar.photoUrl)
            .fallback(R.drawable.ic_img_loading_error)
            .into(imageCharacter)
    }

    companion object {
        fun create(parent: ViewGroup): ItemStarsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return ItemStarsViewHolder(inflate(inflater, parent, false))
        }
    }

}