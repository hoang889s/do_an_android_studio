package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class SongAdapter(
    private val songs: List<MusicManager.Song>,
    private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_the_bai_hat, parent, false)
        return SongViewHolder(view)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = songs[position]
        holder.bind(song, position)
    }

    override fun getItemCount(): Int = songs.size

    inner class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.name_song)
        private val imageView: ShapeableImageView = itemView.findViewById(R.id.img_song)

        fun bind(song: MusicManager.Song, position: Int) {
            nameTextView.text = itemView.context.getString(song.titleResId)
            imageView.setImageResource(song.imageRes)
            itemView.setOnClickListener {
                onItemClick(position)
            }
        }
    }
}