package com.alireza.hw13

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Multipart
import java.io.DataInputStream
import java.io.FileReader
import java.io.InputStream

class ServerVm :ViewModel(){
    

    private val _list = MutableLiveData<List<PhotoX>>()
    val list:LiveData<List<PhotoX>> = _list

    init {
        getDataFromServer()
    }


    private fun getDataFromServer() : List<PhotoX>? {

         val apiKey = "1c04e05bce6e626247758d120b372a73"
         val method = "flickr.photos.search"
         val userId = "34427466731@N01"
         val extras = "url_s"
         val format = "json"
         val nojsoncallback = "1"
         val perPage = "100"
         val page = "1"

        var lists:List<PhotoX>? = null
        RetrofitFlickr.service.loadSearches(
            apiKey,
            userId,
            method,
            extras,
            format,
            nojsoncallback,
            perPage,
            page
        ).enqueue(object : Callback<Photo> {
            override fun onResponse(call: Call<Photo>, response: Response<Photo>
            ) {
                lists = response.body()?.photos?.photo!!
                _list.postValue(lists!!)
            }

            override fun onFailure(call: Call<Photo>, t: Throwable) {
                t.message?.let { Log.d("Response", "onFailure$it") }
            }

        })

        return lists
    }

   }
    
    
