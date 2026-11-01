package com.example.classiclauncher

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class FingerprintAnimView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val printPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#00E676")
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    private val glowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#3300E676")
        style = Paint.Style.FILL
    }

    private var pulseRadius = 10f
    private var isAnimating = false

    fun startUnlockAnimation() {
        isAnimating = true
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val cx = width - 30f
        val cy = height / 2f

        canvas.drawCircle(cx, cy, 40f + pulseRadius, glowPaint)

        for (i in 1..4) {
            canvas.drawCircle(cx, cy, i * 10f, printPaint)
        }

        if (isAnimating) {
            pulseRadius += 2f
            if (pulseRadius > 30f) pulseRadius = 0f
            postInvalidateDelayed(20)
        }
    }
}
