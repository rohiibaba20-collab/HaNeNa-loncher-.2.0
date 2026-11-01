package com.example.classiclauncher

import android.content.Context
import android.graphics.Color

object Prefs {
    private const val PREF_NAME = "classic_launcher_prefs"
    private const val KEY_WHEEL_COLOR = "wheel_color"
    private const val KEY_RING_SIZE = "camera_ring_size"
    private const val KEY_THEME = "current_theme"

    val THEME_CLASSIC_GOLD = "#FFD700"
    val THEME_NEON_BLUE = "#00E5FF"
    val THEME_VIBRANT_PURPLE = "#AA00FF"
    val THEME_SUNSET_ORANGE = "#FF3D00"
    val THEME_CYBER_GREEN = "#00E676"
    val THEME_HOT_PINK = "#FF007F"

    fun saveWheelColor(context: Context, color: Int) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit().putInt(KEY_WHEEL_COLOR, color).apply()
    }

    fun getWheelColor(context: Context): Int {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getInt(KEY_WHEEL_COLOR, Color.parseColor(THEME_CLASSIC_GOLD))
    }

    fun saveCameraRingSize(context: Context, size: Float) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit().putFloat(KEY_RING_SIZE, size.coerceAtLeast(0f)).apply()
    }

    fun getCameraRingSize(context: Context): Float {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getFloat(KEY_RING_SIZE, 0f)
    }

    fun saveTheme(context: Context, themeName: String) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit().putString(KEY_THEME, themeName).apply()
    }

    fun getTheme(context: Context): String {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getString(KEY_THEME, "Classic Gold") ?: "Classic Gold"
    }
}
