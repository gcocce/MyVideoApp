package ar.com.gitmo.myvideoapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_init.*

class InitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState:Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)

        btVideoView.setOnClickListener{
            val intent = Intent(this, VideoViewActivity::class.java)
            startActivity(intent);
        }

        btMediaPlayer.setOnClickListener{
            val intent = Intent(this, MediaPlayerActivity::class.java)
            startActivity(intent);
        }




    }
}
