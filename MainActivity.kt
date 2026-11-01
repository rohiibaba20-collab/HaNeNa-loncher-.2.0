package com.example.classiclauncher

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var wheelView: WheelView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wheelView = findViewById(R.id.wheelView)
        wheelView.wheelColor = Prefs.getWheelColor(this)
        wheelView.cameraRingSize = Prefs.getCameraRingSize(this)

        val btnVideoPlayer = findViewById<ImageButton>(R.id.btnVideoPlayer)
        btnVideoPlayer?.setOnClickListener {
            startActivity(Intent(this, VideoPlayerActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        if (::wheelView.isInitialized) {
            wheelView.wheelColor = Prefs.getWheelColor(this)
            wheelView.cameraRingSize = Prefs.getCameraRingSize(this)
        }
    }
}
