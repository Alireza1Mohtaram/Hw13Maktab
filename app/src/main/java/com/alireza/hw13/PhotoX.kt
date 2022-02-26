package com.alireza.hw13

import com.google.gson.annotations.SerializedName

data class PhotoX(
    val farm: Int,
    val height_s: Int,
    val id: String,
    @SerializedName("isfamily")
    val isFamily: Int,
    @SerializedName("isfriend")
    val isFriend: Int,
    @SerializedName("ispublic")
    val isPublic: Int,
    val owner: String,
    val secret: String,
    val server: String,
    val title: String,
    val url_s: String,
    val width_s: Int
)