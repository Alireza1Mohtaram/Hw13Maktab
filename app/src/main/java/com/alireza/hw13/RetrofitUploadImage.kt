package com.alireza.hw13

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitUploadImage {

    private val client = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("http://51.195.19.222/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(UploadService::class.java)
}