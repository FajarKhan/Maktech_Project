package com.fajarkhan.maktechproject

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var videoListAdapter: VideoListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoListAdapter = VideoListAdapter()
        val layoutManager = LinearLayoutManager(applicationContext)
        rvPictureList.layoutManager = layoutManager
        rvPictureList.itemAnimator = DefaultItemAnimator()
        rvPictureList.adapter = videoListAdapter
//        videoListAdapter.notifyDataSetChanged()



    }
}