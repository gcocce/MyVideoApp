package ar.com.gitmo.myvideoapp

import android.content.res.Configuration
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.net.Uri
import android.view.View
import android.widget.MediaController
import android.media.MediaPlayer.OnPreparedListener
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_videoview.*


class VideoViewActivity : AppCompatActivity() {

    private val vidAddress = "https://ia800903.us.archive.org/15/items/WhatIsBitcoin/What_is_Bitcoin_720p.mp4"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videoview)

        val progressBar: ProgressBar = this.progressBar1

        // display the indefinite progressbar
        this@VideoViewActivity.runOnUiThread(java.lang.Runnable {
            progressBar.visibility = View.VISIBLE
        })

        // Important code starts here
        val vidUri = Uri.parse(vidAddress)
        val vidControl = MediaController(this)


        vidControl.setAnchorView(vidView)
        vidView.setMediaController(vidControl)

        vidView.setVideoURI(vidUri)

        vidView.requestFocus()
        vidView.setOnPreparedListener(object : OnPreparedListener {
            override fun onPrepared(mp: MediaPlayer) {
                // when the task is completed, make progressBar gone
                this@VideoViewActivity.runOnUiThread(java.lang.Runnable {
                    progressBar.visibility = View.GONE
                })
                vidView.start()
            }
        })
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }
}
