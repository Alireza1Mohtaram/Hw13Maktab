package com.alireza.hw13

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface UploadService {

    @Multipart
    @POST("users/{username}")
    fun uploadProfile(
        @Path("username") username :String ,
        @Part part:MultipartBody.Part
    ):Call<String>

    @GET("uploads/{photoname}")
    fun downloadProfile(
        @Path("photoname") photoname :String ,
    ):Call<ResponseBody>
}