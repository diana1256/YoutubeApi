package com.example.youtubeapi.data.local.entity.remote

import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.core.network.BaseDataSource
import org.koin.dsl.module


val remoteDataSourceModule = module {
    factory { RemoteDataSource(get()) }
}
class RemoteDataSource(private val apiService: ApiService):BaseDataSource() {


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
