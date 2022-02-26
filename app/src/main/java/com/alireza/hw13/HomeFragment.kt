package com.alireza.hw13

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val serverVm: ServerVm by viewModels()

    lateinit var photoList:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoList = view.findViewById(R.id.recyclerView)
        serverVm.list.observe(viewLifecycleOwner, Observer {
            photoList.adapter =  PhotoRecyclerAdapter(it)
            photoList.layoutManager = LinearLayoutManager(requireContext())
        })
    }

}