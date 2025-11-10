package com.example.myapplication
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class ChitietbaihatActivity: AppCompatActivity() {
    private lateinit var btn_prev : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chitietbaihat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.chi_tiet_bai_hat)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        val layoutImg = findViewById<View>(R.id.layout_img)
        val layoutInfo = findViewById<View>(R.id.layout_info)

        val title = intent.getStringExtra("song_title")
        val imageRes = intent.getIntExtra("song_image", -1)

        val tv = layoutInfo.findViewById<TextView>(R.id.ten_bai_hat)
        val iv = layoutImg.findViewById<ImageView>(R.id.img_chi_tiet_song)

        tv.text = title ?: "Không có tên"
        if (imageRes != -1) iv.setImageResource(imageRes)

        btn_prev=findViewById<ImageButton>(R.id.nut_bam_quay_lai_home)
        btn_prev.setOnClickListener {
            finish()
        }
    }
}