package com.example.youtubeapi.di

import com.example.youtubeapi.ui.item.ItemViewModel
import com.example.youtubeapi.ui.playlist.PlaylistsViewModel
import com.example.youtubeapi.ui.videos.VideosViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModules = module {
    viewModel {PlaylistsViewModel(get())}
    viewModel { ItemViewModel(get()) }
    viewModel { VideosViewModel(get()) }
}

