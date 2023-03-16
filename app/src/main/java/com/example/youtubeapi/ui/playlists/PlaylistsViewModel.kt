package com.example.youtubeapi.ui.playlists

import androidx.lifecycle.LiveData
import com.example.youtubeapi.App.Companion.repository
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.core.network.ui.BaseViewModel
import com.example.youtubeapi.data.local.entity.remote.model.Playlists

class   PlaylistsViewModel: BaseViewModel() {

    fun playlists() : LiveData<Resource<Playlists>>{
        return repository.getPlaylists()
    }
}