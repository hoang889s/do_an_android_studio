package com.example.myapplication
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import android.widget.FrameLayout

class ThebaihatActivity: AppCompatActivity() {
    private lateinit var btn_song: FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_the_bai_hat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.the_nhac)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
       /* btn_song=findViewById<FrameLayout>(R.id.khung_img_song)
        // ngăn thẻ mẹ bắt sự kiện
        btn_song.setOnTouchListener { v, event ->
            v.parent.requestDisallowInterceptTouchEvent(true)
            false
        }
        // quay về lại trang chủ

        btn_song.setOnClickListener {
            val intent=Intent(this,ChitietbaihatActivity::class.java)
            startActivity(intent)
        }*/
    }
}