package com.example.youtubeapi.di

import com.example.youtubeapi.core.network.networkModule
import com.example.youtubeapi.data.local.entity.remote.remoteDataSourceModule

val koinModules = listOf(
    repoModules,
    viewModules,
    networkModule,
    remoteDataSourceModule
)