# My Video App

This repo has two examples of how to play video inside an Android App with Kotlin.

  - Using VideoView widget (android.widget.VideoView).
  - Using Media Player (android.media.MediaPlayer).

# VideoView

You just need an Activity with the VideoView widget:

```xml
    <VideoView
        android:id="@+id/vidView"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:layout_centerInParent="true" />
```

And the code could be something like this:
```kotlin
    private val vidAddress = "https://somedomain.org/yourvideo.mp4"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videoview)
        
        // The importanto code starts here
        val vidUri = Uri.parse(vidAddress)
        val vidControl = MediaController(this)
        // vidView is the id of the VideoView widget in the Activity xml
        vidControl.setAnchorView(vidView)
        vidView.setMediaController(vidControl)
        vidView.setVideoURI(vidUri)
        vidView.requestFocus()
        vidView.setOnPreparedListener(object : OnPreparedListener {
            override fun onPrepared(mp: MediaPlayer) {
                this@VideoViewActivity.runOnUiThread(java.lang.Runnable {
                    progressBar.visibility = View.GONE
                })
                vidView.start()
            }
        })
    }
```


# MediaPlayer

In this case we need an Activity with a SurfaceView component:

```xml
    <SurfaceView
        android:id="@+id/surfView"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent" />
```

And the activity has to implement some interfaces:

```kotlin
class MediaPlayerActivity : AppCompatActivity(), SurfaceHolder.Callback, MediaPlayer.OnPreparedListener {

    private var vidAddress = "https://somedomain.org/yourvideo.mp4"
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
```


