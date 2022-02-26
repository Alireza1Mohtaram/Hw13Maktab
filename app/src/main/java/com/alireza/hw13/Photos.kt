package com.alireza.hw13

data class Photos(
    val page: Int,
    val pages: Int,
    val perpage: Int,
    val photo: List<PhotoX>,
    val total: Int
)