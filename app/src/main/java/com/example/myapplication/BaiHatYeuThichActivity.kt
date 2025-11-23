package com.example.myapplication

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ImageButton
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BaiHatYeuThichActivity: AppCompatActivity() {
    // data
    private lateinit var datacuibap: MutableList<ObjBaihatyeuthich>
    private lateinit var btn_prev : ImageButton
    private lateinit var btn_home: ImageButton
    private lateinit var btn_lib: ImageButton
    private lateinit var btn_folder: ImageButton
    private lateinit var adapter : AdapterBaiHatYeuThich
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_baihatyeuthich)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.bai_hat_yeu_thich)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        datacuibap=mutableListOf(
            ObjBaihatyeuthich(R.drawable.imgsong1,"ba hoa", nameartist = "hoa ba")
        )
        adapter= AdapterBaiHatYeuThich(this,datacuibap)
        val list_baihatyeuthich = findViewById<ListView>(R.id.listview_baihatyeuthich)
        list_baihatyeuthich.adapter=adapter
        val includeV:View = findViewById<View>(R.id.id_menu_baihatyeuthich)
        btn_folder=includeV.findViewById<ImageButton>(R.id.nut_thumuc)
        btn_folder.imageTintList = ColorStateList.valueOf(getColor(R.color.mau_cam_thanh_tab))
        btn_home = includeV.findViewById<ImageButton>(R.id.nut_home)
        btn_home.setOnClickListener {
            val intent =  Intent(this, AmnhacdanhchobanActivity::class.java)
           // btn_home.imageTintList = ColorStateList.valueOf(getColor(R.color.mau_cam_thanh_tab))
            startActivity(intent)
        }
        btn_lib = includeV.findViewById<ImageButton>(R.id.nut_tim)
        btn_lib.setOnClickListener {
            val intent1 = Intent(this, ThuVienActivity::class.java)
          //  btn_lib.imageTintList = ColorStateList.valueOf(getColor(R.color.mau_cam_thanh_tab))
            startActivity(intent1)
        }
        // vùng quay về
        btn_prev=findViewById<ImageButton>(R.id.nut_bam_quay_lai)
        btn_prev.setOnClickListener {
           // btn_folder.imageTintList = ColorStateList.valueOf(getColor(R.color.mau_cam_thanh_tab))
            btn_home.imageTintList = ColorStateList.valueOf(getColor(R.color.white))
            btn_lib.imageTintList = ColorStateList.valueOf(getColor(R.color.white))
            finish()
        }
    }
}