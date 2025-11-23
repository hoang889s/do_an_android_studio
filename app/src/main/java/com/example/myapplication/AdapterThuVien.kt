package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.google.android.material.imageview.ShapeableImageView

class AdapterThuVien (private val context: Context,private val datasongs: MutableList<ObjBaihatyeuthich>): BaseAdapter() {
    // lay so luong datasongs
    override fun getCount():Int= datasongs.size
    // lay chi so hien tai cua datasongs
    override fun getItem(position:Int):Any =datasongs[position]
    // tao id cho datasongs
    override fun getItemId(position: Int):Long = position.toLong()
    // tao view
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view :View =convertView?: LayoutInflater.from(context).inflate(R.layout.activity_the_bai_hat,parent,false)
        val songs = datasongs[position]
        val imgsong = view.findViewById<ShapeableImageView>(R.id.img_song)
        val namesong = view.findViewById<TextView>(R.id.name_song)


        // gan du lieu
        imgsong.setImageResource(songs.imgsong)
        namesong.text=songs.namesong

        return view

    }
}