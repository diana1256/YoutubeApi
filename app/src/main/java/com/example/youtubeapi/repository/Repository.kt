package com.example.youtubeapi.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.youtubeapi.core.network.result.Resource
import com.example.youtubeapi.data.local.entity.remote.RemoteDataSource
import com.example.youtubeapi.data.local.entity.remote.model.ItemsItem
import kotlinx.coroutines.Dispatchers

class Repository {

   private val remoteDataSource : RemoteDataSource by lazy {
       RemoteDataSource()
   }
    fun getPlaylists() = liveData(Dispatchers.IO) {
            emit(Resource.loading())
          val response = remoteDataSource.getPlaylist()
            emit(response)
        }

  fun getItemList(playlistId: String) = liveData(Dispatchers.IO){
        emit(Resource.loading())
       val response = remoteDataSource.getPlaylistItem(playlistId)
        emit(response)
      }
    fun getVideo(videoId:String): LiveData<Resource<ItemsItem>> = liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val response = remoteDataSource.getVideo(videoId)
        emit(response)
    }
}