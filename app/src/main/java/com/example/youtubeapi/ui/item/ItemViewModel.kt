package com.example.youtubeapi.ui.item

import androidx.lifecycle.LiveData
import com.example.youtubeapi.App
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.core.network.ui.BaseViewModel
import com.example.youtubeapi.data.local.entity.remote.model.Playlists

class   ItemViewModel: BaseViewModel() {
    fun playlists(playlistId:String) : LiveData<Resource<Playlists>>{
        return App.repository.getItemList(playlistId)
    }
}