package com.reyesm.pizaint

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BlurMaskFilter
import android.graphics.Color
import android.graphics.ImageDecoder
import android.graphics.Paint
import android.graphics.Path
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.toColor
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.reyesm.pizaint.DrawView.Companion.colorBrush


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    companion object{
        var mPaint = Paint()
        var path = Path()
    }

    private lateinit var mDrawView: DrawView
    private lateinit var imageUri: Uri
    private val SELECT_PICTURE = 1   //

    override fun onCreate(savedInstanceState: Bundle?) {

        val screenSplash = installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        screenSplash.setKeepOnScreenCondition{ false }

        //la pantalla siempre sera blanca
        getWindow().getDecorView().setBackgroundColor(Color.WHITE)

        mDrawView = findViewById(R.id.draw_view)

        val smallBtn = findViewById<Button>(R.id.small_brush_btn)
        smallBtn.setOnClickListener {
            Toast.makeText(this, "10 px", Toast.LENGTH_SHORT).show()
            mDrawView.setBrushSize(10f) }

        val mediumBtn = findViewById<Button>(R.id.medium_brush_btn)
        mediumBtn.setOnClickListener {
            Toast.makeText(this, "20 px", Toast.LENGTH_SHORT).show()
            mDrawView.setBrushSize(20f) }

        val largeBtn = findViewById<Button>(R.id.large_brush_btn)
        largeBtn.setOnClickListener {
            Toast.makeText(this, "30 px", Toast.LENGTH_SHORT).show()
            mDrawView.setBrushSize(30f) }

        val xlargeBtn = findViewById<Button>(R.id.ex_large_brush_btn)
        xlargeBtn.setOnClickListener {
            Toast.makeText(this, "40 px", Toast.LENGTH_SHORT).show()
            mDrawView.setBrushSize(40f) }

//colors
        val blackBtn = findViewById<ImageButton>(R.id.black)
        blackBtn.setOnClickListener {
            Toast.makeText(this, "Black", Toast.LENGTH_SHORT).show()
            mPaint.setColor(Color.BLACK)
            currentColor(mPaint.color)
        }

        val whiteBtn = findViewById<ImageButton>(R.id.white)
        whiteBtn.setOnClickListener {
            Toast.makeText(this, "White", Toast.LENGTH_SHORT).show()
            mPaint.setColor(Color.WHITE)
            currentColor(mPaint.color)
        }

        val LSALMON = Color.parseColor("#FFA07A")
        val lightSalmonBtn = findViewById<ImageButton>(R.id.light_salmon)
        lightSalmonBtn.setOnClickListener {
            Toast.makeText(this, "Light Salmon", Toast.LENGTH_SHORT).show()
            mPaint.setColor(LSALMON)
            currentColor(mPaint.color)
        }

        val SALMON = Color.parseColor("#FA8072")
        val salmonBtn = findViewById<ImageButton>(R.id.salmon)
        salmonBtn.setOnClickListener {
            Toast.makeText(this, "Salmon", Toast.LENGTH_SHORT).show()
            mPaint.setColor(SALMON)
            currentColor(mPaint.color)
        }

        val DSALMON = Color.parseColor("#E9967A")
        val darkSalmonBtn = findViewById<ImageButton>(R.id.dark_salmon)
        darkSalmonBtn.setOnClickListener {
            Toast.makeText(this, "Dark Salmon", Toast.LENGTH_SHORT).show()
            mPaint.setColor(DSALMON)
            currentColor(mPaint.color)
        }

        val IRED = Color.parseColor("#CD5C5C")
        val indianRedBtn = findViewById<ImageButton>(R.id.red_indian)
        indianRedBtn.setOnClickListener {
            Toast.makeText(this, "Red Indian", Toast.LENGTH_SHORT).show()
            mPaint.setColor(IRED)
            currentColor(mPaint.color)
        }

        val RED = Color.parseColor("#FF0000")
        val redBtn = findViewById<ImageButton>(R.id.red)
        redBtn.setOnClickListener {
            Toast.makeText(this, "Red", Toast.LENGTH_SHORT).show()
            mPaint.setColor(RED)
            currentColor(mPaint.color)
        }

        val DRED = Color.parseColor("#8B0000")
        val darkRedBtn = findViewById<ImageButton>(R.id.red_dark)
        darkRedBtn.setOnClickListener {
            Toast.makeText(this, "Dark Red", Toast.LENGTH_SHORT).show()
            mPaint.setColor(DRED)
            currentColor(mPaint.color)
        }

        val PINK = Color.parseColor("#FFC0C0")
        val pinkBtn = findViewById<ImageButton>(R.id.pink)
        pinkBtn.setOnClickListener {
            Toast.makeText(this, "Pink", Toast.LENGTH_SHORT).show()
            mPaint.setColor(PINK)
            currentColor(mPaint.color)
        }

        val PINKH = Color.parseColor("#FF69B4")
        val pinkHotBtn = findViewById<ImageButton>(R.id.pink_hot)
        pinkHotBtn.setOnClickListener {
            Toast.makeText(this, "Hot Pink", Toast.LENGTH_SHORT).show()
            mPaint.setColor(PINKH)
            currentColor(mPaint.color)
        }

        val REDV = Color.parseColor("#C71585")
        val redVioletBtn = findViewById<ImageButton>(R.id.red_violet)
        redVioletBtn.setOnClickListener {
            Toast.makeText(this, "Red Violet", Toast.LENGTH_SHORT).show()
            mPaint.setColor(REDV)
            currentColor(mPaint.color)
        }

        val CORAL = Color.parseColor("#FF7F50")
        val coralBtn = findViewById<ImageButton>(R.id.coral)
        coralBtn.setOnClickListener {
            Toast.makeText(this, "Coral", Toast.LENGTH_SHORT).show()
            mPaint.setColor(CORAL)
            currentColor(mPaint.color)
        }

        val ORANGER = Color.parseColor("#FF4500")
        val orangeRedBtn = findViewById<ImageButton>(R.id.orange_red)
        orangeRedBtn.setOnClickListener {
            Toast.makeText(this, "Orange Red", Toast.LENGTH_SHORT).show()
            mPaint.setColor(ORANGER)
            currentColor(mPaint.color)
        }

        val ORANGE = Color.parseColor("#FFA500")
        val orangeBtn = findViewById<ImageButton>(R.id.orange)
        orangeBtn.setOnClickListener {
            Toast.makeText(this, "Orange", Toast.LENGTH_SHORT).show()
            mPaint.setColor(ORANGE)
            currentColor(mPaint.color)
        }

        val GOLD = Color.parseColor("#FFD700")
        val goldBtn = findViewById<ImageButton>(R.id.gold)
        goldBtn.setOnClickListener {
            Toast.makeText(this, "Gold", Toast.LENGTH_SHORT).show()
            mPaint.setColor(GOLD)
            currentColor(mPaint.color)
        }

        val YELLOW = Color.parseColor("#FFFF00")
        val yellowBtn = findViewById<ImageButton>(R.id.yellow)
        yellowBtn.setOnClickListener {
            Toast.makeText(this, "Yellow", Toast.LENGTH_SHORT).show()
            mPaint.setColor(YELLOW)
            currentColor(mPaint.color)
        }

        val LYELLOW = Color.parseColor("#EAEA73")
        val lYellowBtn = findViewById<ImageButton>(R.id.light_yellow)
        lYellowBtn.setOnClickListener {
            Toast.makeText(this, "Light Yellow", Toast.LENGTH_SHORT).show()
            mPaint.setColor(LYELLOW)
            currentColor(mPaint.color)
        }

        val VIOLET = Color.parseColor("#EE82EE")
        val violetBtn = findViewById<ImageButton>(R.id.violet)
        violetBtn.setOnClickListener {
            Toast.makeText(this, "Violet", Toast.LENGTH_SHORT).show()
            mPaint.setColor(VIOLET)
            currentColor(mPaint.color)
        }

        val FUCHSIA = Color.parseColor("#FF00FF")
        val fuchsiaBtn = findViewById<ImageButton>(R.id.fuchia)
        fuchsiaBtn.setOnClickListener {
            Toast.makeText(this, "Fuchsia", Toast.LENGTH_SHORT).show()
            mPaint.setColor(FUCHSIA)
            currentColor(mPaint.color)
        }

        val BLUEV = Color.parseColor("#9400D3")
        val blueVioletBtn = findViewById<ImageButton>(R.id.blue_violet)
        blueVioletBtn.setOnClickListener {
            Toast.makeText(this, "Blue Violet", Toast.LENGTH_SHORT).show()
            mPaint.setColor(BLUEV)
            currentColor(mPaint.color)
        }

        val PURPLE = Color.parseColor("#800080")
        val purpleBtn = findViewById<ImageButton>(R.id.purple)
        purpleBtn.setOnClickListener {
            Toast.makeText(this, "Purple", Toast.LENGTH_SHORT).show()
            mPaint.setColor(PURPLE)
            currentColor(mPaint.color)
        }

        val INDIGO = Color.parseColor("#4B0082")
        val indigoBtn = findViewById<ImageButton>(R.id.indigo)
        indigoBtn.setOnClickListener {
            Toast.makeText(this, "Indigo", Toast.LENGTH_SHORT).show()
            mPaint.setColor(INDIGO)
            currentColor(mPaint.color)
        }

        val SLATEBLUE = Color.parseColor("#6A5ACD")
        val slateBlueBtn = findViewById<ImageButton>(R.id.slate_blue)
        slateBlueBtn.setOnClickListener {
            Toast.makeText(this, "Slate Blue", Toast.LENGTH_SHORT).show()
            mPaint.setColor(SLATEBLUE)
            currentColor(mPaint.color)
        }

        val GREENY = Color.parseColor("#ADFF2F")
        val gYellowBtn = findViewById<ImageButton>(R.id.green_yellow)
        gYellowBtn.setOnClickListener {
            Toast.makeText(this, "Green Yellow", Toast.LENGTH_SHORT).show()
            mPaint.setColor(GREENY)
            currentColor(mPaint.color)
        }

        val LIMA = Color.parseColor("#00ff00")
        val limaBtn = findViewById<ImageButton>(R.id.lima)
        limaBtn.setOnClickListener {
            Toast.makeText(this, "Lima", Toast.LENGTH_SHORT).show()
            mPaint.setColor(LIMA)
            currentColor(mPaint.color)
        }

        val LGREEN = Color.parseColor("#90EE90")
        val lGreenBtn = findViewById<ImageButton>(R.id.light_green)
        lGreenBtn.setOnClickListener {
            Toast.makeText(this, "Light Green", Toast.LENGTH_SHORT).show()
            mPaint.setColor(LGREEN)
            currentColor(mPaint.color)
        }

        val PEAR = Color.parseColor("#74B72E")
        val pearBtn = findViewById<ImageButton>(R.id.pear)
        pearBtn.setOnClickListener {
            Toast.makeText(this, "Pear", Toast.LENGTH_SHORT).show()
            mPaint.setColor(PEAR)
            currentColor(mPaint.color)
        }

        val GREEN = Color.parseColor("#008000")
        val greenBtn = findViewById<ImageButton>(R.id.green)
        greenBtn.setOnClickListener {
            Toast.makeText(this, "Green", Toast.LENGTH_SHORT).show()
            mPaint.setColor(GREEN)
            currentColor(mPaint.color)
        }

        val OLIVE = Color.parseColor("#808000")
        val oliveBtn = findViewById<ImageButton>(R.id.olive)
        oliveBtn.setOnClickListener {
            Toast.makeText(this, "Olive", Toast.LENGTH_SHORT).show()
            mPaint.setColor(OLIVE)
            currentColor(mPaint.color)
        }

        val AQUAMARINE = Color.parseColor("#7FFFD4")
        val aquamarineBtn = findViewById<ImageButton>(R.id.aquamarine)
        aquamarineBtn.setOnClickListener {
            Toast.makeText(this, "Aquamarine", Toast.LENGTH_SHORT).show()
            mPaint.setColor(AQUAMARINE)
            currentColor(mPaint.color)
        }

        val TURQUESE = Color.parseColor("#00ffff")
        val turqueseBtn = findViewById<ImageButton>(R.id.turquese)
        turqueseBtn.setOnClickListener {
            Toast.makeText(this, "Turquese", Toast.LENGTH_SHORT).show()
            mPaint.setColor(TURQUESE)
            currentColor(mPaint.color)
        }

        val SBLUE = Color.parseColor("#4682B4")
        val steelBlueBtn = findViewById<ImageButton>(R.id.steel_blue)
        steelBlueBtn.setOnClickListener {
            Toast.makeText(this, "Steel Blue", Toast.LENGTH_SHORT).show()
            mPaint.setColor(SBLUE)
            currentColor(mPaint.color)
        }

        val BLUE = Color.parseColor("#0000FF")
        val blueBtn = findViewById<ImageButton>(R.id.blue)
        blueBtn.setOnClickListener {
            Toast.makeText(this, "Blue", Toast.LENGTH_SHORT).show()
            mPaint.setColor(BLUE)
            currentColor(mPaint.color)
        }

        val BLUED = Color.parseColor("#00008B")
        val blueDarkBtn = findViewById<ImageButton>(R.id.dark_blue)
        blueDarkBtn.setOnClickListener {
            Toast.makeText(this, "Dark Blue", Toast.LENGTH_SHORT).show()
            mPaint.setColor(BLUED)
            currentColor(mPaint.color)
        }

        val BLUEM = Color.parseColor("#191970")
        val MblueBtn = findViewById<ImageButton>(R.id.midnight_blue)
        MblueBtn.setOnClickListener {
            Toast.makeText(this, "Midnight Blue", Toast.LENGTH_SHORT).show()
            mPaint.setColor(BLUEM)
            currentColor(mPaint.color)
        }

        val BISQUE = Color.parseColor("#FFe4C4")
        val bisqueBtn = findViewById<ImageButton>(R.id.bisque)
        bisqueBtn.setOnClickListener {
            Toast.makeText(this, "Bisque", Toast.LENGTH_SHORT).show()
            mPaint.setColor(BISQUE)
            currentColor(mPaint.color)
        }

        val BURLYW = Color.parseColor("#DEB887")
        val burlyBtn = findViewById<ImageButton>(R.id.burly_wood)
        burlyBtn.setOnClickListener {
            Toast.makeText(this, "Burly Wood", Toast.LENGTH_SHORT).show()
            mPaint.setColor(BURLYW)
            currentColor(mPaint.color)
        }

        val PERU = Color.parseColor("#CD853F")
        val peruBtn = findViewById<ImageButton>(R.id.peru)
        peruBtn.setOnClickListener {
            Toast.makeText(this, "Peru", Toast.LENGTH_SHORT).show()
            mPaint.setColor(PERU)
            currentColor(mPaint.color)
        }

        val CHOCOLATE = Color.parseColor("#D2691E")
        val chocolateBtn = findViewById<ImageButton>(R.id.chocolate)
        chocolateBtn.setOnClickListener {
            Toast.makeText(this, "Chocolate", Toast.LENGTH_SHORT).show()
            mPaint.setColor(CHOCOLATE)
            currentColor(mPaint.color)
        }

        val SIENNA = Color.parseColor("#A0522D")
        val siennaBtn = findViewById<ImageButton>(R.id.sienna)
        siennaBtn.setOnClickListener {
            Toast.makeText(this, "Sienna", Toast.LENGTH_SHORT).show()
            mPaint.setColor(SIENNA)
            currentColor(mPaint.color)
        }

        val BROWN = Color.parseColor("#800000")
        val brownBtn = findViewById<ImageButton>(R.id.brown)
        brownBtn.setOnClickListener {
            Toast.makeText(this, "Brown", Toast.LENGTH_SHORT).show()
            mPaint.setColor(BROWN)
            currentColor(mPaint.color)
        }

        val GRAYL = Color.parseColor("#D3D3D3")
        val lGrayBtn = findViewById<ImageButton>(R.id.light_gray)
        lGrayBtn.setOnClickListener {
            Toast.makeText(this, "Light Gray", Toast.LENGTH_SHORT).show()
            mPaint.setColor(GRAYL)
            currentColor(mPaint.color)
        }

        val SILVER = Color.parseColor("#C0C0C0")
        val silverBtn = findViewById<ImageButton>(R.id.silver)
        silverBtn.setOnClickListener {
            Toast.makeText(this, "Silver", Toast.LENGTH_SHORT).show()
            mPaint.setColor(SILVER)
            currentColor(mPaint.color)
        }

        val GRAY = Color.parseColor("#808080")
        val grayBtn = findViewById<ImageButton>(R.id.gray)
        grayBtn.setOnClickListener {
            Toast.makeText(this, "Gray", Toast.LENGTH_SHORT).show()
            mPaint.setColor(GRAY)
            currentColor(mPaint.color)
        }

        val GRAYS = Color.parseColor("#708090")
        val sGrayBtn = findViewById<ImageButton>(R.id.slate_gray)
        sGrayBtn.setOnClickListener {
            Toast.makeText(this, "Slate Gray", Toast.LENGTH_SHORT).show()
            mPaint.setColor(GRAYS)
            currentColor(mPaint.color)
        }

        val paintAllBtn = findViewById<ImageButton>(R.id.paintAll)
        paintAllBtn.setOnClickListener {
            Toast.makeText(this, "Borrar Todo", Toast.LENGTH_SHORT).show()
            mDrawView.setBackgroundColor(mPaint.color)
        }

        val eraseBtn = findViewById<ImageButton>(R.id.eraser)
        eraseBtn.setOnClickListener {
            Toast.makeText(this, "Borrar", Toast.LENGTH_SHORT).show()
            mPaint.setColor(Color.WHITE)
            currentColor(mPaint.color)
        }

        val eraseAllBtn = findViewById<ImageButton>(R.id.eraseAll)
        val imageView = findViewById<ImageView>(R.id.imageView)
        eraseAllBtn.setOnClickListener {
            Toast.makeText(this, "Borrar Todo", Toast.LENGTH_SHORT).show()
            //mPaint.setColor(Color.WHITE)
            //mDrawView.setBackgroundColor(mPaint.color)
            imageView.setImageDrawable(null)
            mDrawView.clearScreen()

        }

        val saveBtn = findViewById<ImageButton>(R.id.saveAll)
        saveBtn.setOnClickListener {
            mDrawView.isDrawingCacheEnabled = true
            val savedImage = mDrawView.drawingCache
            val savedImagePath = MediaStore.Images.Media.insertImage(
                contentResolver, savedImage, "Dibujo", "Imagen de dibujo")
            Toast.makeText(this, "Imagen guardada en la galería", Toast.LENGTH_SHORT).show()
        }

        val button = findViewById<ImageButton>(R.id.photo)
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, SELECT_PICTURE)
        }

        //aerografo
        val btnAirbrush: ImageButton = findViewById(R.id.btnAirbrush)
        val dibujoView: DrawView = findViewById(R.id.draw_view)

        btnAirbrush.setOnClickListener {
            dibujoView.activarAerografo()
            Toast.makeText(this, "Aerografo", Toast.LENGTH_SHORT).show()
        }

        //lapiz normal
        val btnNormal: ImageButton = findViewById(R.id.btnNormal)

        btnNormal.setOnClickListener {
            dibujoView.desactivarAerografo()
            Toast.makeText(this, "Lapiz", Toast.LENGTH_SHORT).show()
        }

    }

    private fun currentColor(color: Int) {
        colorBrush = color
        path = Path()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK && data != null) {
            val selectedImage = data.data
            val imageView: ImageView = findViewById(R.id.imageView)
            //imageView.setImageURI(selectedImage)

            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImage)

            imageView.setImageBitmap(bitmap)
        }
    }
}