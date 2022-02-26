package com.alireza.hw13

import com.alireza.quiztowandroid.FlickrService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFlickr {

    private val client = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://api.flickr.com/services/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(FlickrService::class.java)
}