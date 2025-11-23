package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.imageview.ShapeableImageView

class AdapterBaiHatYeuThich(private val context: Context,private val datasongs: MutableList<ObjBaihatyeuthich>): BaseAdapter() {
    // lay so luong datasongs
    override fun getCount():Int= datasongs.size
    // lay chi so hien tai cua datasongs
    override fun getItem(position:Int):Any =datasongs[position]
    // tao id cho datasongs
    override fun getItemId(position: Int):Long = position.toLong()
    // tao view
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view :View =convertView?: LayoutInflater.from(context).inflate(R.layout.activity_item_baihatyeuthich,parent,false)
        val songs = datasongs[position]
        val imgsong = view.findViewById<ShapeableImageView>(R.id.img_song)
        val namesong = view.findViewById<TextView>(R.id.name_song_baihatyeuthich)
        val nameartist = view.findViewById<TextView>(R.id.name_tacgia_baihatyeuthich)


        // gan du lieu
        imgsong.setImageResource(songs.imgsong)
        namesong.text=songs.namesong
        nameartist.text=songs.nameartist

        return view

    }
}