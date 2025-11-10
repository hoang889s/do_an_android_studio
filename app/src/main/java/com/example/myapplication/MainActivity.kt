package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.content.Intent
class MainActivity : AppCompatActivity() {
    // ơwe đây khai báo một nut sẽ khai báo đến bắt đầu sang trang âm nhạc dành bạn
    private lateinit var btnStarthomePage:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        btnStarthomePage = findViewById<Button>(R.id.nut_bam_bat_dau)
        btnStarthomePage.setOnClickListener {
            val intent = Intent(this, AmnhacdanhchobanActivity::class.java)
            startActivity(intent)
        }
    }
}