package com.example.classiclauncher

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val seekCameraRing = findViewById<SeekBar>(R.id.seekCameraRing)
        seekCameraRing?.progress = Prefs.getCameraRingSize(this).toInt()
        seekCameraRing?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Prefs.saveCameraRingSize(this@SettingsActivity, progress.toFloat())
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        setupThemeButton(R.id.btnThemeGold, Prefs.THEME_CLASSIC_GOLD, "Classic Gold")
        setupThemeButton(R.id.btnThemeNeonBlue, Prefs.THEME_NEON_BLUE, "Neon Blue")
        setupThemeButton(R.id.btnThemePurple, Prefs.THEME_VIBRANT_PURPLE, "Vibrant Purple")
        setupThemeButton(R.id.btnThemeOrange, Prefs.THEME_SUNSET_ORANGE, "Sunset Orange")
        setupThemeButton(R.id.btnThemeGreen, Prefs.THEME_CYBER_GREEN, "Cyber Green")
        setupThemeButton(R.id.btnThemePink, Prefs.THEME_HOT_PINK, "Hot Pink")
    }

    private fun setupThemeButton(buttonId: Int, colorHex: String, themeName: String) {
        findViewById<Button>(buttonId)?.setOnClickListener {
            val colorInt = Color.parseColor(colorHex)
            Prefs.saveWheelColor(this, colorInt)
            Prefs.saveTheme(this, themeName)
            Toast.makeText(this, "Theme applied: $themeName", Toast.LENGTH_SHORT).show()
        }
    }
}
