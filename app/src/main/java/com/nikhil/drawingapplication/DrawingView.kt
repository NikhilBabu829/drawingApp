package com.nikhil.drawingapplication

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.nio.file.Path

class DrawingView(context:Context,attrs:AttributeSet):View(context,attrs) {
    private var mDrawPath: Custompath? = null
    private var mCanvasBitmap : Bitmap? = null
    private var mDrawPaint: Paint? = null //the pain holds the color information and how to draw
    private var mCanvasPaint : Paint? = null
    private var mBrushSize : Float = 0.toFloat()
    private var color = Color.BLACK
    private var canvas:Canvas? = null
    init {
        setUpDrawing()
    }
    private fun setUpDrawing(){
        mDrawPaint = Paint()
        mDrawPath = Custompath(color,mBrushSize)
        mDrawPaint!!.color = color
        mDrawPaint!!.style = Paint.Style.STROKE
        mDrawPaint!!.strokeJoin = Paint.Join.ROUND
        mDrawPaint!!.strokeCap = Paint.Cap.ROUND
        mCanvasPaint = Paint(Paint.DITHER_FLAG)
        mBrushSize = 20.toFloat()
    }
    internal inner class Custompath(var color:Int,var brushThickness:Float) : Path {

    }
}