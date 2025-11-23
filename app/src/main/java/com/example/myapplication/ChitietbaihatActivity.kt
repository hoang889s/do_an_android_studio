package com.example.myapplication
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Looper
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.os.Handler
class ChitietbaihatActivity: AppCompatActivity() {
    private lateinit var btn_prev : ImageButton
    private lateinit var btn_random: ImageButton
    private lateinit var btn_play: ImageButton
    private lateinit var btn_next: ImageButton
    private lateinit var btn_loop: ImageButton
    private lateinit var btn_back: ImageButton
    private lateinit var musicManager: MusicManager
    private lateinit var tv: TextView
    private lateinit var iv: ImageView
    private lateinit var seekbar: SeekBar
    private lateinit var dau: TextView
    private lateinit var cuoi: TextView
    private val handler = Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chitietbaihat)
        musicManager = MusicManager(this)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.chi_tiet_bai_hat)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        val layoutImg: View = findViewById<View>(R.id.layout_img)
        val layoutInfo: View = findViewById<View>(R.id.layout_info)

        val title = intent.getStringExtra("song_title")
        val imageRes = intent.getIntExtra("song_image", -1)

        tv = layoutInfo.findViewById<TextView>(R.id.ten_bai_hat)
        iv = layoutImg.findViewById<ImageView>(R.id.img_chi_tiet_song)

        tv.text = musicManager.getCurrentSongName()
        iv.setImageResource(musicManager.getCurrentSongImage())

        btn_prev=findViewById<ImageButton>(R.id.nut_bam_quay_lai_home)
        btn_prev.setOnClickListener {
            finish()
        }

        // khai bao cac thanh phan cua tool music
        val layout_tool_music :View =findViewById<View>(R.id.id_menu_toolmusic)
        btn_loop=layout_tool_music.findViewById<ImageButton>(R.id.nut_loop)
        btn_play=layout_tool_music.findViewById<ImageButton>(R.id.nut_pause)
        btn_back=layout_tool_music.findViewById<ImageButton>(R.id.nut_prev)
        btn_next=layout_tool_music.findViewById<ImageButton>(R.id.nut_next)
        btn_random=layout_tool_music.findViewById<ImageButton>(R.id.nut_random)
        // su kien click
        btn_loop.setOnClickListener {
            musicManager.toggrandom()
        }
        btn_back.setOnClickListener {
            musicManager.previous(this)
            updateUI()
        }
        btn_next.setOnClickListener {
            musicManager.next(this)
            updateUI()
        }
        btn_random.setOnClickListener {
            musicManager.toggrandom()
        }
        btn_play.setOnClickListener {
            if(musicManager.isPlaying()){
                musicManager.pause()
                btn_play.setImageResource(R.drawable.ic_icon_pause)
            }
            else{
                musicManager.resume()
                btn_play.setImageResource(R.drawable.ic_play_fill)
            }
            updateUI()
        }
        // cap nhat seekbar theo thoi gian thuc
        val viewSeekbar:View = findViewById<View>(R.id.id_seekbar)
        seekbar = viewSeekbar.findViewById<SeekBar>(R.id.thanh_thoi_luong)
        dau=viewSeekbar.findViewById<TextView>(R.id.thoi_gian_bat_dau)
        cuoi=viewSeekbar.findViewById<TextView>(R.id.thoi_gian_ket_thuc)
        handler.post(object : Runnable{
            override fun run(){
                if(musicManager.isPlaying()){
                    val current = musicManager.getCurrent()
                    val duration = musicManager.getDuration()
                    if(duration>0){
                        val progress = (current*100/duration)
                        seekbar.progress=progress
                        dau.text = formatTime(current)
                        cuoi.text = formatTime(duration)
                    }
                }
                // cap nhat sau 0.5 giay
                handler.postDelayed(this,500)
            }
        })
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser){
                    val duration =musicManager.getDuration()
                    val newposition = progress*duration/100
                    musicManager.seekTo(newposition)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }
    private fun updateUI() {
        // Cập nhật icon play/pause
        if (musicManager.isPlaying()) {
            btn_play.setImageResource(R.drawable.ic_icon_pause)
        } else {
            btn_play.setImageResource(R.drawable.ic_play_fill)
        }

        // Cập nhật tên bài hát hiện tại
        findViewById<TextView>(R.id.ten_bai_hat).text = musicManager.getCurrentSongName()

        // Cập nhật ảnh bài hát hiện tại
        findViewById<ImageView>(R.id.img_chi_tiet_song).setImageResource(
            musicManager.getCurrentSongImage()
        )
    }
    private fun formatTime(ms: Int): String {
        val totalSeconds = ms / 1000
        val minutes = totalSeconds / 60
        val seconds = totalSeconds % 60
        return String.format("%02d:%02d", minutes, seconds)
    }

}