package com.example.youtubeapi.data.local.entity.remote

import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.core.network.BaseDataSource
import com.example.youtubeapi.core.network.RetrofitClient


class RemoteDataSource:BaseDataSource() {

    private val apiService: ApiService by lazy {
        RetrofitClient.create()
    }

    suspend fun getPlaylist() = getResult {
            apiService.getPlaylists(
                "snippet,contentDetails",
                "UCNoN7dpdAlglcQWUn2pFjDA",
                BuildConfig.API_KEY
            )
        }

    suspend fun getPlaylistItem(playlistId:String) = getResult {
           apiService.getPlaylistItems(BuildConfig.API_KEY,"snippet,contentDetails",playlistId)
        }

    suspend fun getVideo(videoId:String) = getResult {
        apiService.getVideo(BuildConfig.API_KEY,"snippet,contentDetails",videoId)
    }
    }
