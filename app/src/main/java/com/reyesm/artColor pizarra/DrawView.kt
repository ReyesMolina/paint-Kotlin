package com.reyesm.pizaint
import android.content.Context
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.reyesm.pizaint.MainActivity.Companion.path

//class DrawView(context: Context, attrs: AttributeSet) : View(context, attrs){

class DrawView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var mPaint = Paint()
    private var mPath = Path()
    private var mX: Float = 0.toFloat()
    private var mY: Float = 0.toFloat()
    private var mDrawBitmap: Bitmap? = null
    private var mDrawCanvas: Canvas? = null
    private var mBrushSize = 10f


    init {
        mPaint.color = colorBrush
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeJoin = Paint.Join.ROUND
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeWidth = mBrushSize

    }

    //imagen
    fun setImage(bitmap: Bitmap) {
        mDrawBitmap = bitmap
        mDrawCanvas = Canvas(mDrawBitmap!!)
        invalidate()  // Redibuja la vista.
    }

    //aerografo
    fun activarAerografo() {
        mPaint.maskFilter = BlurMaskFilter(10f, BlurMaskFilter.Blur.NORMAL)
    }

    //lapiz
    fun desactivarAerografo() {
        mPaint.maskFilter = null
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mDrawBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        mDrawCanvas = Canvas(mDrawBitmap!!)
    }

    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)
        canvas.drawBitmap(mDrawBitmap!!, 0f, 0f, mPaint)
        canvas.drawPath(mPath, mPaint)

        for(i in pathList.indices){
            mPaint.setColor(colorList[i])
            canvas?.drawPath(pathList[i], mPaint)
            invalidate()
        }
    }

    private fun touchStart(x: Float, y: Float){
        mPath.reset()
        mPath.moveTo(x, y)
        mX = x
        mY = y
    }

    private fun touchMove(x: Float, y: Float){
        val dx = Math.abs(x - mX)
        val dy = Math.abs(y - mY)

        if(dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE){
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2)
            mX = x
            mY = y
        }
        invalidate()
    }

    private fun touchUp(){
        mPath.lineTo(mX, mY)
        mDrawCanvas!!.drawPath(mPath, mPaint)
        mPath.reset()
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                touchStart(x, y)
                invalidate()
            }
            MotionEvent.ACTION_MOVE -> {
                touchMove(x, y)
                colorList.add(colorBrush)
                pathList.add(path)
            }
            MotionEvent.ACTION_UP -> {
                touchUp()
                invalidate()
            }
        }
        return true
    }  // colorBrush = currentBrush
    // mPaint = paintBrush

    fun setBrushSize(size: Float){
        mBrushSize = size
        mPaint.strokeWidth = size
    }

    fun clearScreen() {
        mDrawBitmap?.eraseColor(Color.TRANSPARENT)
        path.reset()
        invalidate()
    }

    private var bitmap: Bitmap? = null
    fun setImageBitmap(bitmap: Bitmap) {
        this.bitmap = bitmap
        invalidate() // Redraw the view
    }

    companion object {
        private const val TOUCH_TOLERANCE = 4f
        var colorBrush = Color.BLACK
        var colorList = ArrayList<Int>()
        var pathList = ArrayList<Path>()
    }
}
