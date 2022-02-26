package com.alireza.quiztowandroid

import com.alireza.hw13.Photo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrService {
    @GET("rest")
    fun loadImages(@Query("api_key") api_key:String ,
                   @Query("user_id") user_id:String ,
                   @Query("method") method:String ,
                   @Query("extras") extras:String ,
                   @Query("format") format:String ,
                   @Query("nojsoncallback") nojsoncallback:String ,
                   @Query("per_page") per_page:String ,
                   @Query("page") page:String ):Call<Photo>
    @GET("rest")
    fun loadSearches(@Query("api_key") api_key:String ,
                   @Query("user_id") user_id:String ,
                   @Query("method") method:String ,
                   @Query("extras") extras:String ,
                   @Query("format") format:String ,
                   @Query("nojsoncallback") nojsoncallback:String ,
                   @Query("per_page") per_page:String ,
                   @Query("page") page:String ):Call<Photo>
}