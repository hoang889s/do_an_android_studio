package com.example.myapplication
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.imageview.ShapeableImageView

class AmnhacdanhchobanActivity: AppCompatActivity() {
    private lateinit var btn_prev : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_amnhacchoban)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.am_nhac_cho_ban)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        btn_prev=findViewById<ImageButton>(R.id.nut_bam_quay_lai)
        // quay về lại trang chủ
        btn_prev.setOnClickListener {
            finish()
        }
        // khai bai view khung danh sách
        val khungDanhsach=findViewById<LinearLayout>(R.id.khung_doc_am_nhac_cho_ban)
        val inflater = layoutInflater
        //Dữ liệu mẫu
        val songs = listOf(
            Pair("bai hat 1",R.drawable.imgsong1),
            Pair("bai hat 2",R.drawable.imgsong1),
        )
        for((title,drawableRes)in songs){
            val itemView = inflater.inflate(R.layout.activity_the_bai_hat,khungDanhsach,false)
            val frame = itemView.findViewById<FrameLayout>(R.id.khung_img_song)
            val img=itemView.findViewById<ShapeableImageView>(R.id.img_song)
            val name= itemView.findViewById<TextView>(R.id.name_song)

            img.setImageResource(drawableRes)
            name.text=title

            frame.isClickable=true
            frame.isFocusable=true

            frame.setOnTouchListener { v,event->
                if(event.action== MotionEvent.ACTION_DOWN){
                    v.parent?.requestDisallowInterceptTouchEvent(true)

                }else if(event.action==MotionEvent.ACTION_UP||event.action == MotionEvent.ACTION_CANCEL){
                    v.parent?.requestDisallowInterceptTouchEvent(false)
                }
                false
            }
            frame.setOnClickListener {
                val intent = Intent(this, ChitietbaihatActivity::class.java).apply{
                    putExtra("song_title",title)
                    putExtra("song_image",drawableRes)

                }
                startActivity(intent)
            }
            val params = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(12, 8, 12, 8)
            itemView.layoutParams = params
            khungDanhsach.addView(itemView)
        }
    }
}