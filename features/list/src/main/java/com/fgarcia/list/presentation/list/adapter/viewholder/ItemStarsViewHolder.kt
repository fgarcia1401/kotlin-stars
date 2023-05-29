package com.fgarcia.list.presentation.list.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fgarcia.common.extensions.formatCompactDecimal
import com.fgarcia.common.imageloader.ImageLoader
import com.fgarcia.list.databinding.ItemStarBinding.inflate
import com.fgarcia.list.databinding.ItemStarBinding
import com.fgarcia.list.domain.model.ItemStar

class ItemStarsViewHolder(
    private val itemCharacterBinding: ItemStarBinding,
    private val imageLoader: ImageLoader
) : RecyclerView.ViewHolder(itemCharacterBinding.root) {

    fun bind(itemStar: ItemStar) = with(itemCharacterBinding) {
        itemStar.run {
            textName.text = name
            textStars.text = totalStars.formatCompactDecimal()
            textForks.text = totalForks.formatCompactDecimal()
        }
        populatePhotoUrl(itemStar)
    }

    private fun populatePhotoUrl(itemStar: ItemStar) = imageLoader.load(
        image = itemCharacterBinding.imageCharacter,
        imageUrl = itemStar.photoUrl
    )

    companion object {
        fun create(parent: ViewGroup, imageLoader: ImageLoader): ItemStarsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return ItemStarsViewHolder(
                itemCharacterBinding = inflate(inflater, parent, false),
                imageLoader =imageLoader
            )
        }
    }

}
