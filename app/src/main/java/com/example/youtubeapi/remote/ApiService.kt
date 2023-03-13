package com.example.youtubeapi.remote

import com.example.youtubeapi.model.ItemsItem
import com.example.youtubeapi.model.Playlists
import com.example.youtubeapi.model.Snippet
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun getPlaylists(
        @Query("part") apiKey:String,
        @Query("channelId") channelId:String,
        @Query("key") key:String,
        @Query("maxResults") maxResults : Int = 20
    ):Call<Playlists>
}