package com.example.classiclauncher

import android.content.Context
import android.graphics.Color

object IconTint {
    fun updateWheelColorOnly(context: Context, colorHex: String) {
        val color = Color.parseColor(colorHex)
        Prefs.saveWheelColor(context, color)
    }
}
