package com.example.youtubeapi.ui.item

import androidx.lifecycle.LiveData
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.core.network.ui.BaseViewModel
import com.example.youtubeapi.data.local.entity.remote.model.Playlists
import com.example.youtubeapi.repository.Repository

class   ItemViewModel(private val repository: Repository): BaseViewModel() {
    fun playlists(playlistId:String) : LiveData<Resource<Playlists>>{
        return repository.getItemList(playlistId)
    }
}