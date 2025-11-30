package com.example.myapplication

import android.os.Bundle
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.os.Handler
import android.view.View
import java.util.Locale

class ChitietbaihatActivity : AppCompatActivity() {
    private lateinit var btnBack: ImageButton
    private lateinit var btnRandom: ImageButton
    private lateinit var btnPlay: ImageButton
    private lateinit var btnPrev: ImageButton
    private lateinit var btnNext: ImageButton
    private lateinit var btnLoop: ImageButton
    private lateinit var tv: TextView
    private lateinit var iv: ImageView
    private lateinit var seekbar: SeekBar
    private lateinit var dau: TextView
    private lateinit var cuoi: TextView
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var seekBarUpdateRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chitietbaihat)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.chi_tiet_bai_hat)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Step 1: Find the container views for the included layouts
        val layoutImg: View = findViewById(R.id.layout_img)
        val layoutInfo: View = findViewById(R.id.layout_info)
        val layoutToolMusic: View = findViewById(R.id.id_menu_toolmusic)
        val viewSeekbar: View = findViewById(R.id.id_seekbar)

        // Step 2: Initialize all views from their respective containers
        tv = layoutInfo.findViewById(R.id.ten_bai_hat) 
        iv = layoutImg.findViewById(R.id.img_chi_tiet_song)
        btnBack = findViewById(R.id.nut_bam_quay_lai_home) // This one is in the main layout
        btnLoop = layoutToolMusic.findViewById(R.id.nut_loop)
        btnPlay = layoutToolMusic.findViewById(R.id.nut_pause)
        btnPrev = layoutToolMusic.findViewById(R.id.nut_prev)
        btnNext = layoutToolMusic.findViewById(R.id.nut_next)
        btnRandom = layoutToolMusic.findViewById(R.id.nut_random)
        seekbar = viewSeekbar.findViewById(R.id.thanh_thoi_luong)
        dau = viewSeekbar.findViewById(R.id.thoi_gian_bat_dau)
        cuoi = viewSeekbar.findViewById(R.id.thoi_gian_ket_thuc)

        // Step 3: Set listeners now that all views are guaranteed to be initialized
        btnBack.setOnClickListener {
            finish()
        }
        btnLoop.setOnClickListener {
            MusicManager.toggloop()
        }
        btnPrev.setOnClickListener {
            MusicManager.previous(this)
            updateUI()
        }
        btnNext.setOnClickListener {
            MusicManager.next(this)
            updateUI()
        }
        btnRandom.setOnClickListener {
            MusicManager.toggrandom()
        }
        btnPlay.setOnClickListener {
            if (MusicManager.isPlaying()) {
                MusicManager.pause()
            } else {
                MusicManager.resume()
            }
            updateUI()
        }
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    val duration = MusicManager.getDuration()
                    if (duration > 0) {
                        val newPosition = progress * duration / 100
                        MusicManager.seekTo(newPosition)
                    }
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Step 4: Define the seekbar update mechanism
        seekBarUpdateRunnable = object : Runnable {
            override fun run() {
                if (handler.looper.thread.isAlive) {
                    val current = MusicManager.getCurrent()
                    val duration = MusicManager.getDuration()
                    if (duration > 0) {
                        seekbar.progress = current * 100 / duration
                        dau.text = formatTime(current)
                        cuoi.text = formatTime(duration)
                    }
                    handler.postDelayed(this, 500)
                }
            }
        }
        
        // Step 5: Update the UI as the last step in onCreate, after everything is initialized
        updateUI()
    }

    override fun onResume() {
        super.onResume()
        // Start updating UI and seekbar
        handler.post(seekBarUpdateRunnable)
        updateUI()
    }

    override fun onPause() {
        super.onPause()
        // Stop the seekbar handler to prevent memory leaks and unnecessary work
        handler.removeCallbacks(seekBarUpdateRunnable)
    }

    private fun updateUI() {
        // This is now safe to call because all views are initialized
        MusicManager.getCurrentSong()?.let {
            tv.text = getString(it.titleResId)
            iv.setImageResource(it.imageRes)
        }
        if (MusicManager.isPlaying()) {
            btnPlay.setImageResource(R.drawable.ic_icon_pause)
        } else {
            btnPlay.setImageResource(R.drawable.ic_play_fill)
        }
    }

    private fun formatTime(ms: Int): String {
        val totalSeconds = ms / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format(Locale.US, "%02d:%02d", minutes, seconds)
    }
}