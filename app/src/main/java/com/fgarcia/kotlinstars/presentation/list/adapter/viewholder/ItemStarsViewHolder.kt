package com.fgarcia.kotlinstars.presentation.list.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.fgarcia.core.extensions.formatCompactDecimal
import com.fgarcia.kotlinstars.R
import com.fgarcia.kotlinstars.databinding.ItemRepositoryBinding
import com.fgarcia.kotlinstars.databinding.ItemRepositoryBinding.inflate
import com.fgarcia.kotlinstars.domain.model.ItemStar

class ItemStarsViewHolder(
    private val itemCharacterBinding: ItemRepositoryBinding
) : RecyclerView.ViewHolder(itemCharacterBinding.root) {


    fun bind(itemStar: ItemStar) = with(itemCharacterBinding) {
        itemStar.run {
            textName.text = name
            textStars.text = totalStars.formatCompactDecimal()
            textForks.text = totalForks.formatCompactDecimal()
        }
        populatePhotoUrl(itemStar)
    }

    private fun populatePhotoUrl(itemStar: ItemStar) =
        Glide.with(itemView)
            .load(itemStar.photoUrl)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .fallback(R.drawable.ic_img_loading_error)
            .into(itemCharacterBinding.imageCharacter)

    companion object {
        fun create(parent: ViewGroup): ItemStarsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return ItemStarsViewHolder(inflate(inflater, parent, false))
        }
    }

}