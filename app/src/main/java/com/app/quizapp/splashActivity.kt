package com.app.quizapp

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.*
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var surfaceView: SurfaceView
    private lateinit var placeholderImage: ImageView
    private lateinit var videoPlayer: MediaPlayer
    private lateinit var soundPlayer: MediaPlayer

    private val splashDurationMillis = 6000L
    private val handler = Handler(Looper.getMainLooper())
    private var hasNavigated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        surfaceView = findViewById(R.id.surfaceView)
        placeholderImage = findViewById(R.id.placeholderImage)

        // Ensure SurfaceView is behind the ImageView
        surfaceView.setZOrderMediaOverlay(false)

        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                playVideo(holder)
                playSound()
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
            override fun surfaceDestroyed(holder: SurfaceHolder) {}
        })
    }

    private fun playVideo(holder: SurfaceHolder) {
        val videoUri = Uri.parse("android.resource://$packageName/${R.raw.vedio}")
        videoPlayer = MediaPlayer()

        try {
            videoPlayer.setDataSource(this, videoUri)
            videoPlayer.setDisplay(holder)

            videoPlayer.setOnPreparedListener { mediaPlayer ->
                // Fade out the placeholder image after a short delay to prevent blackout
                handler.postDelayed({
                    placeholderImage.animate()
                        .alpha(0f)
                        .setDuration(300)
                        .withEndAction { placeholderImage.visibility = ImageView.GONE }
                        .start()
                }, 100)

                mediaPlayer.start()
                handler.postDelayed({ navigateToMain() }, splashDurationMillis)
            }

            videoPlayer.setOnCompletionListener {
                navigateToMain()
            }

            videoPlayer.setOnErrorListener { _, _, _ ->
                navigateToMain()
                true
            }

            videoPlayer.prepareAsync()
        } catch (e: Exception) {
            navigateToMain()
        }
    }

    private fun playSound() {
        soundPlayer = MediaPlayer.create(this, R.raw.sound)
        soundPlayer.start()
        soundPlayer.setOnCompletionListener {
            it.release()
        }
    }

    private fun navigateToMain() {
        if (hasNavigated) return
        hasNavigated = true

        try {
            if (::videoPlayer.isInitialized) {
                videoPlayer.stop()
                videoPlayer.release()
            }
        } catch (_: Exception) {}

        try {
            if (::soundPlayer.isInitialized) {
                soundPlayer.stop()
                soundPlayer.release()
            }
        } catch (_: Exception) {}

        handler.removeCallbacksAndMessages(null)

        startActivity(Intent(this, loginActivity::class.java))
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            if (::videoPlayer.isInitialized) videoPlayer.release()
            if (::soundPlayer.isInitialized) soundPlayer.release()
        } catch (_: Exception) {}
        handler.removeCallbacksAndMessages(null)
    }
}
