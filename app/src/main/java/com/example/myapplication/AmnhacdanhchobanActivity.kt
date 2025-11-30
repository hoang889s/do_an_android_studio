package com.example.myapplication

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.imageview.ShapeableImageView

class AmnhacdanhchobanActivity : AppCompatActivity() {
    private lateinit var btnPrev: ImageButton
    private lateinit var btnFolder: ImageButton
    private lateinit var btnLib: ImageButton
    private lateinit var btnHome: ImageButton
    private lateinit var miniPlayerContainer: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_amnhacchoban)

        val rootView = findViewById<View>(R.id.am_nhac_cho_ban)
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

        val includeV: View = findViewById(R.id.id_menu_amnhacchoban)
        btnHome = includeV.findViewById(R.id.nut_home)
        btnHome.imageTintList = ColorStateList.valueOf(getColor(R.color.mau_cam_thanh_tab))

        btnFolder = includeV.findViewById(R.id.nut_thumuc)
        btnFolder.setOnClickListener {
            val intent = Intent(this, ThuVienActivity::class.java)
            startActivity(intent)
        }

        btnLib = includeV.findViewById(R.id.nut_tim) // Heart Icon
        btnLib.setOnClickListener {
            val intent1 = Intent(this, BaiHatYeuThichActivity::class.java)
            startActivity(intent1)
        }

        btnPrev = findViewById(R.id.nut_bam_quay_lai)
        btnPrev.setOnClickListener {
            finish()
        }

        populateSongList(findViewById(R.id.khung_doc_am_nhac_cho_ban))
        
        updateMiniPlayer()
    }

    private fun populateSongList(container: LinearLayout) {
        val inflater = layoutInflater
        val songLists = container.findViewById<LinearLayout>(R.id.khung_list_bai_hat_noi_bat) 

        for (i in 0 until MusicManager.songList.size) {
            val song = MusicManager.songList[i]
            val itemView = inflater.inflate(R.layout.activity_the_bai_hat, songLists, false)
            val img = itemView.findViewById<ShapeableImageView>(R.id.img_song)
            val name = itemView.findViewById<TextView>(R.id.name_song)

            img.setImageResource(song.imageRes)
            name.text = getString(song.titleResId)

            itemView.setOnClickListener { 
                MusicManager.play(this, i)
                updateMiniPlayer()
            }
            songLists.addView(itemView)
        }
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