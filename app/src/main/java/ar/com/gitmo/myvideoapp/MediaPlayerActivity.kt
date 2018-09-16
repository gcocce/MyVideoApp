package ar.com.gitmo.myvideoapp

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import android.media.AudioManager
import kotlinx.android.synthetic.main.activity_media_player.*


class MediaPlayerActivity : AppCompatActivity(), SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {

    private var vidAddress = "https://ia800903.us.archive.org/15/items/WhatIsBitcoin/What_is_Bitcoin_720p.mp4"
    private val mediaPlayer: MediaPlayer = MediaPlayer()
    private var vidHolder: SurfaceHolder?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)

        vidHolder = surfView.getHolder()
        vidHolder?.addCallback(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
        try {
            //mediaPlayer = MediaPlayer()
            mediaPlayer.setDisplay(vidHolder)
            mediaPlayer.setDataSource(vidAddress)
            mediaPlayer.prepare()
            mediaPlayer.setOnPreparedListener(this)
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    override fun onPrepared(mp: MediaPlayer?) {
        mediaPlayer.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {

    }

    override fun surfaceCreated(holder: SurfaceHolder?) {

    }

}
