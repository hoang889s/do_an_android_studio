package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class AdapterBaiHatYeuThich(
    private val context: Context,
    private val dataSongs: MutableList<ObjBaihatyeuthich>
) : RecyclerView.Adapter<AdapterBaiHatYeuThich.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.activity_item_baihatyeuthich, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = dataSongs[position]
        holder.imgSong.setImageResource(song.imgsong)
        holder.nameSong.text = song.namesong
        holder.nameArtist.text = song.nameartist
    }

    override fun getItemCount(): Int = dataSongs.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgSong: ShapeableImageView = view.findViewById(R.id.img_song)
        val nameSong: TextView = view.findViewById(R.id.name_song_baihatyeuthich)
        val nameArtist: TextView = view.findViewById(R.id.name_tacgia_baihatyeuthich)
    }
}