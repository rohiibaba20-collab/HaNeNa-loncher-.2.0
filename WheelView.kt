package com.example.classiclauncher

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class WheelView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    var cameraRingSize: Float = 0f
        set(value) {
            field = value.coerceAtLeast(0f)
            invalidate()
        }

    var wheelColor: Int = Color.parseColor("#FFD700")
        set(value) {
            field = value
            snakePaint.color = value
            headPaint.color = value
            invalidate()
        }

    private val backgroundTrackPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 6f
        color = Color.parseColor("#22FFFFFF")
    }

    private val snakePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 14f
        strokeCap = Paint.Cap.ROUND
        color = wheelColor
    }

    private val headPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = wheelColor
    }

    private val cameraRingPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 6f
        color = Color.CYAN
    }

    private var startAngle = 0f
    private val sweepAngle = 90f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val cx = width / 2f
        val cy = height / 2f
        val radius = (Math.min(width, height) / 2f) - 30f

        if (radius > 0) {
            val oval = RectF(cx - radius, cy - radius, cx + radius, cy + radius)
            
            canvas.drawCircle(cx, cy, radius, backgroundTrackPaint)
            canvas.drawArc(oval, startAngle, sweepAngle, false, snakePaint)

            val headAngleRad = Math.toRadians((startAngle + sweepAngle).toDouble())
            val headX = (cx + radius * Math.cos(headAngleRad)).toFloat()
            val headY = (cy + radius * Math.sin(headAngleRad)).toFloat()
            canvas.drawCircle(headX, headY, 12f, headPaint)

            startAngle = (startAngle + 3f) % 360f
            postInvalidateDelayed(16)
        }

        if (cameraRingSize > 0f) {
            canvas.drawCircle(cx, cameraRingSize, cameraRingSize, cameraRingPaint)
        }
    }
}
