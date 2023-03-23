package com.example.youtubeapi.ui.item


import androidx.core.view.isVisible
import com.example.youtubeapi.data.local.entity.remote.model.ItemsItem
import com.example.youtubeapi.databinding.ItemPlaylistBinding
import com.example.youtubeapi.core.network.ext.loadImage
import com.livermor.delegateadapter.delegate.ViewBindingDelegateAdapter


class AdapterItem(private val onClick:(ItemsItem)->Unit):
    ViewBindingDelegateAdapter<ItemsItem, ItemPlaylistBinding>(ItemPlaylistBinding::inflate) {

    override fun isForViewType(item: Any): Boolean = item is ItemsItem

    override fun ItemPlaylistBinding.onBind(item: ItemsItem) {
        tvTitle.text = item.snippet.title
        tvDesk.text = item.snippet.publishedAt
        ivItem.loadImage(item.snippet.thumbnails.default.url)
        constBar.isVisible = false
        itemView.setOnClickListener{
            onClick(item)
        }

    }
    override fun ItemsItem.getItemId(): Any = snippet

}
