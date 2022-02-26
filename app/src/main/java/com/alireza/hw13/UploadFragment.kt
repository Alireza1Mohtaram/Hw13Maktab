package com.alireza.hw13

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.fragment.app.Fragment
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

class UploadFragment : Fragment(R.layout.fragment_upload) {

    lateinit var imgProfile: ImageView
    lateinit var btnUP: Button
    lateinit var btnDL: Button
    lateinit var handler: Handler
    var luncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        getUi(it)
        uploadInServer(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imgProfile = view.findViewById(R.id.img_profile)
        btnDL = view.findViewById(R.id.btnDownload)
        btnUP = view.findViewById(R.id.btnUpload)


        imgProfile.setOnClickListener {
            luncher.launch()
        }
        btnDL.setOnClickListener{
            downloadFromServer("profile.jpg")
        }
    }

    private fun getUi(src: Bitmap) {
        handler = Handler(Looper.getMainLooper())
        handler.post {
            imgProfile.setImageBitmap(src)
        }
    }
    private fun getUiForDownload(src: Bitmap) {
        handler = Handler(Looper.getMainLooper())
        handler.post {
            imgProfile.setImageBitmap(src)
        }
    }
    private fun uploadInServer(src: Bitmap) {

        val stream = ByteArrayOutputStream()
        src.compress(Bitmap.CompressFormat.JPEG,100,stream)

        val part = MultipartBody.Part.createFormData(
            "image", "profile.jpg",
            RequestBody.create("image/*".toMediaTypeOrNull(), stream.toByteArray())
        )
        RetrofitUploadImage.service.uploadProfile("mohsenafshar", part)
            .enqueue(object : Callback<String?> {
                override fun onResponse(call: Call<String?>, response: Response<String?>) {
                    response.body()?.let { Log.d("response", it) }
                }

                override fun onFailure(call: Call<String?>, t: Throwable) {
                    Log.d("response", "Fail")
                }
            })
    }
    private fun downloadFromServer(imageName: String) {


        RetrofitUploadImage.service.downloadProfile(imageName)
            .enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                    Log.d("response", response.body().toString())
                    val stream = response.body()?.byteStream()
                    stream?.let { val bitmap:Bitmap =
                        BitmapFactory.decodeStream(stream)
                        getUiForDownload(bitmap)
                    }
                }
                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Log.d("response", "Fail on Dp")
                }
            })


    }

}
