package com.fajarkhan.maktechproject

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.annotation.NonNull
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import kotlinx.android.synthetic.main.activity_video.*

internal class VideoListAdapter() :
    RecyclerView.Adapter<VideoListAdapter.MyViewHolder>() {

    var videoUrl = "https://content.jwplatform.com/manifests/yp34SRmf.m3u8"
    lateinit var player: SimpleExoPlayer

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var playerView: PlayerView = view.findViewById(R.id.playerView)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val context = holder.itemView.context




        player = ExoPlayerFactory.newSimpleInstance(context, DefaultTrackSelector())

        holder.playerView.player = player

        val dataSourceFactory = DefaultDataSourceFactory(
            context,
            Util.getUserAgent(context, context.applicationInfo.loadLabel(context.packageManager).toString())
        )

        holder.playerView.videoSurfaceView?.setOnClickListener {   val intent = Intent(context, VideoActivity::class.java)
            intent.putExtra(VideoActivity.ARG_VIDEO_URL, videoUrl)
            context.startActivity(intent) }


        when (Util.inferContentType(Uri.parse(videoUrl))) {
            C.TYPE_HLS -> {
                val mediaSource =
                    HlsMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(videoUrl))
                player.prepare(mediaSource)
            }

            C.TYPE_OTHER -> {
                val mediaSource = ExtractorMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(Uri.parse(videoUrl))
                player.prepare(mediaSource)
            }

            else -> {
                //This is to catch SmoothStreaming and DASH types which are not supported currently

            }
        }

    }

    override fun getItemCount(): Int {
        return 20
    }
}
