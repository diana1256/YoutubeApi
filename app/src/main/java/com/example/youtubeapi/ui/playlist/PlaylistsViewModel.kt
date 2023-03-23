package com.example.youtubeapi.ui.playlist

import androidx.lifecycle.LiveData
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.core.network.ui.BaseViewModel
import com.example.youtubeapi.data.local.entity.remote.model.Playlists
import com.example.youtubeapi.repository.Repository

class   PlaylistsViewModel(private val repository: Repository) : BaseViewModel() {

    fun playlists() : LiveData<Resource<Playlists>>{
        return repository.getPlaylists()
    }
}