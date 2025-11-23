package com.example.myapplication

import android.content.Context
import android.media.MediaPlayer

class MusicManager (private val context: Context){
    // bien cong cu tool music
    private var mediaplayer: MediaPlayer?=null
    private  var isLoop =false
    private var isRandom = false
    // chi so so mang cua bai hat
    var currentIndex=0
    data class Song(
        val title: String,
        val imageRes: Int,
        val audioRes: Int
    )

    val songList = listOf(
        Song("Song 1", R.drawable.imgsong1, R.raw.song1),
        Song("Song 2", R.drawable.imgsong1, R.raw.song2),
        Song("Song 3", R.drawable.imgsong1, R.raw.song3)
    )
    // mang list song

    // ham choi nhac
    fun play(context : Context,index: Int){
        currentIndex = index
        mediaplayer?.release()
        val song = songList[currentIndex]
        mediaplayer = MediaPlayer.create(context, song.audioRes)
        mediaplayer?.start()
    }
    // ham lap
    fun toggloop(){isLoop=!isLoop}
    fun isLooping () = isLoop
    // ham ngau nhien
    fun toggrandom(){isRandom=!isRandom}
    fun isRandoming () = isRandom
    // ham dung
    fun pause(){
        mediaplayer?.pause()
    }
    // ham tiep tuc
    fun resume (){
        mediaplayer?.start()
    }
    // ham tiep theo
    fun next(context: Context){
        val nextIndex = when {
            isRandom -> (songList.indices).random()
            isLoop -> currentIndex
            else -> (currentIndex + 1) % songList.size
        }
        play(context, nextIndex)
    }
    // ham quay ve
    fun previous(context: Context){
        val prevIndex = when {
            isRandom -> (songList.indices).random()
            isLoop -> currentIndex
            else -> if (currentIndex - 1 < 0) songList.size - 1 else currentIndex - 1
        }
        play(context, prevIndex)
    }
    // lay thoi gian tuy chinh
    fun seekTo(ms:Int){
        mediaplayer?.seekTo(ms)
    }
    private fun getCurrentSong() = songList[currentIndex]

    fun getCurrentSongName(): String = getCurrentSong().title

    fun getCurrentSongImage(): Int = getCurrentSong().imageRes
    // thoi gian
    fun getDuration():Int=mediaplayer?.duration?:0
    fun getCurrent():Int=mediaplayer?.currentPosition?:0
    fun isPlaying():Boolean=mediaplayer?.isPlaying?:false

}