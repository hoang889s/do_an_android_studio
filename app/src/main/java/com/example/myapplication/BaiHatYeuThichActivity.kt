package com.example.myapplication

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class BaiHatYeuThichActivity : AppCompatActivity() {
    private lateinit var data: MutableList<ObjBaihatyeuthich>
    private lateinit var btnPrev: ImageButton
    private lateinit var btnHome: ImageButton
    private lateinit var btnLib: ImageButton
    private lateinit var btnFolder: ImageButton
    private lateinit var adapter: AdapterBaiHatYeuThich
    private lateinit var miniPlayerContainer: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_baihatyeuthich)

        val rootView = findViewById<View>(R.id.bai_hat_yeu_thich)
        val initialPaddingLeft = rootView.paddingLeft
        val initialPaddingTop = rootView.paddingTop
        val initialPaddingRight = rootView.paddingRight
        val initialPaddingBottom = rootView.paddingBottom

        ViewCompat.setOnApplyWindowInsetsListener(rootView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                initialPaddingLeft + systemBars.left,
                initialPaddingTop + systemBars.top,
                initialPaddingRight + systemBars.right,
                initialPaddingBottom + systemBars.bottom
            )
            insets
        }

        miniPlayerContainer = findViewById(R.id.mini_player_container)

        data = mutableListOf(
            ObjBaihatyeuthich(R.drawable.imgsong1, "ba hoa", nameartist = "hoa ba")
        )

        val recyclerView: RecyclerView = findViewById(R.id.listview_baihatyeuthich)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AdapterBaiHatYeuThich(this, data)
        recyclerView.adapter = adapter

        val includeV: View = findViewById(R.id.id_menu_baihatyeuthich)
        btnFolder = includeV.findViewById(R.id.nut_thumuc)
        btnFolder.setOnClickListener {
            val intent = Intent(this, ThuVienActivity::class.java)
            startActivity(intent)
        }
        btnHome = includeV.findViewById(R.id.nut_home)
        btnHome.setOnClickListener {
            val intent1 = Intent(this, AmnhacdanhchobanActivity::class.java)
            startActivity(intent1)
        }
        btnLib = includeV.findViewById(R.id.nut_tim) // Heart Icon
        btnLib.imageTintList = ColorStateList.valueOf(getColor(R.color.mau_cam_thanh_tab))


        btnPrev = findViewById(R.id.nut_bam_quay_lai)
        btnPrev.setOnClickListener {
            finish()
        }
        updateMiniPlayer()
    }

    override fun onResume() {
        super.onResume()
        updateMiniPlayer()
    }

    private fun updateMiniPlayer() {
        val currentSong = MusicManager.getCurrentSong()
        if (currentSong != null) {
            miniPlayerContainer.visibility = View.VISIBLE
            val miniPlayerImage = miniPlayerContainer.findViewById<ShapeableImageView>(R.id.mini_player_image)
            val miniPlayerTitle = miniPlayerContainer.findViewById<TextView>(R.id.mini_player_title)
            val miniPlayerPlayPause = miniPlayerContainer.findViewById<ImageButton>(R.id.mini_player_play_pause)

            miniPlayerImage.setImageResource(currentSong.imageRes)
            miniPlayerTitle.text = getString(currentSong.titleResId)

            if (MusicManager.isPlaying()) {
                miniPlayerPlayPause.setImageResource(R.drawable.ic_icon_pause)
            } else {
                miniPlayerPlayPause.setImageResource(R.drawable.ic_play_fill)
            }

            miniPlayerPlayPause.setOnClickListener {
                if (MusicManager.isPlaying()) {
                    MusicManager.pause()
                } else {
                    MusicManager.resume()
                }
                updateMiniPlayer()
            }

            miniPlayerContainer.setOnClickListener {
                val intent = Intent(this, ChitietbaihatActivity::class.java)
                startActivity(intent)
            }
        } else {
            miniPlayerContainer.visibility = View.GONE
        }
    }
}