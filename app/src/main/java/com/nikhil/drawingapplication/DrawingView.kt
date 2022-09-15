package com.nikhil.drawingapplication

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
class DrawingView(context:Context,attrs:AttributeSet):View(context,attrs) {
    private var mDrawPath: Custompath? = null
    private var mCanvasBitmap : Bitmap? = null
    private var mDrawPaint: Paint? = null //the pain holds the color information and how to draw
    private var mCanvasPaint : Paint? = null
    private var mBrushSize : Float = 0.toFloat()
    private var color = Color.BLACK
    private var canvas:Canvas? = null
    private var mPaths = ArrayList<Custompath>() //
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
    //each time the size of the screen is changed
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mCanvasBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888)
        canvas = Canvas(mCanvasBitmap!!)
    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(mCanvasBitmap!!,0f,0f,mCanvasPaint)
        for (path in mPaths){
            mDrawPaint!!.strokeWidth = path.brushThickness
            mDrawPaint!!.color = path.color
            canvas.drawPath(path,mDrawPaint!!)
        }
        if (!mDrawPath!!.isEmpty){
            mDrawPaint!!.strokeWidth = mDrawPath!!.brushThickness
            mDrawPaint!!.color = mDrawPath!!.color
            canvas.drawPath(mDrawPath!!,mDrawPaint!!)
        }
    }
    //the below method is used to see what happens when we touch the screen
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var touchX = event?.x
        val touchY = event?.y
        //the below when method is where we are deciding on what is going to happen when we touch or drag the screen
        when(event?.action){
            MotionEvent.ACTION_DOWN->{
                mDrawPath!!.color = color
                mDrawPath!!.brushThickness = mBrushSize
                mDrawPath!!.reset()
                mDrawPath!!.moveTo(touchX!!,touchY!!)
            }
            MotionEvent.ACTION_MOVE->{
                mDrawPath!!.lineTo(touchX!!,touchY!!)
            }
            MotionEvent.ACTION_UP->{
                mPaths.add(mDrawPath!!)
                mDrawPath = Custompath(color,mBrushSize)
            }
            else -> return false
        }
        //
        invalidate()
        return true
    }
    internal inner class Custompath(var color:Int,var brushThickness:Float) : Path(){

    }
}