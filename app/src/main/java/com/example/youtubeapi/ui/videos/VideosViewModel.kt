package com.example.youtubeapi.ui.videos

import androidx.lifecycle.LiveData
import com.example.youtubeapi.App
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.core.network.ui.BaseViewModel
import com.example.youtubeapi.data.local.entity.remote.model.ItemsItem

class VideosViewModel:BaseViewModel() {

    fun getVideo(videoId :String) : LiveData<Resource<ItemsItem>> {
        return App.repository.getVideo(videoId)
    }
}