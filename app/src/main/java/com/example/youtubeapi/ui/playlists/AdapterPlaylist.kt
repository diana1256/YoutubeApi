package com.example.youtubeapi.ui.playlists

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtubeapi.databinding.ItemPlaylistBinding
import com.example.youtubeapi.model.ItemsItem
import com.example.youtubeapi.model.Playlists
import kotlin.reflect.KFunction1

class AdapterPlaylist(private val playlists: Playlists, private val click:(item:ItemsItem)->Unit):RecyclerView.Adapter<AdapterPlaylist.ViewHolder>() {

    inner class ViewHolder(val binding: ItemPlaylistBinding):RecyclerView. ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(item: ItemsItem) {
            binding.tvItemPlaylists.text = item.snippet.title
            binding.tvDesk.text = item.contentDetails.itemCount.toString()+ " video series"
            Glide.with(binding.ivImage).load(item.snippet.thumbnails.default.url).into(binding.ivImage)
            itemView.setOnClickListener {
                click(item)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = playlists.items?.size!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(playlists.items!![position])
    }
}