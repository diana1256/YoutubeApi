@file:Suppress("FunctionName", "HasPlatformType")

package com.example.youtubeapi.core.network

import com.example.youtubeapi.BuildConfig
import com.example.youtubeapi.data.local.entity.remote.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { ProvideRetrofit(get()) }
    factory { provideOkHttpClient() }
    factory { provideApi(get()) }
}

fun ProvideRetrofit(okHttpClient: OkHttpClient):Retrofit{
     return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}
fun provideOkHttpClient():OkHttpClient{
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder()
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(20, TimeUnit.SECONDS)
        .connectTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}
fun provideApi(retrofit:Retrofit) = retrofit.create(ApiService::class.java)
