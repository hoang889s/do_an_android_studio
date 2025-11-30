package com.example.myapplication

import android.content.Context
import android.media.MediaPlayer

object MusicManager {
    private var mediaPlayer: MediaPlayer? = null
    private var isLoop = false
    private var isRandom = false
    var currentIndex = -1

    data class Song(
        val titleResId: Int,
        val imageRes: Int,
        val audioRes: Int
    )

    val songList = listOf(
        Song(R.string.name_song1, R.drawable.imgsong1, R.raw.song1),
        Song(R.string.name_song2, R.drawable.imgsong1, R.raw.song2),
        Song(R.string.name_song3, R.drawable.imgsong1, R.raw.song3)
    )

    fun play(context: Context, index: Int) {
        if (index < 0 || index >= songList.size) return
        currentIndex = index
        mediaPlayer?.release()
        val song = songList[currentIndex]
        mediaPlayer = MediaPlayer.create(context.applicationContext, song.audioRes)
        mediaPlayer?.start()
    }

    fun toggloop() {
        isLoop = !isLoop
        mediaPlayer?.isLooping = isLoop
    }

    fun isLooping() = isLoop

    fun toggrandom() {
        isRandom = !isRandom
    }

    fun isRandoming() = isRandom

    fun pause() {
        mediaPlayer?.pause()
    }

    fun resume() {
        mediaPlayer?.start()
    }

    fun next(context: Context) {
        val nextIndex = when {
            isRandom -> (songList.indices).random()
            else -> (currentIndex + 1) % songList.size
        }
        play(context, nextIndex)
    }

    fun previous(context: Context) {
        val prevIndex = when {
            isRandom -> (songList.indices).random()
            else -> if (currentIndex - 1 < 0) songList.size - 1 else currentIndex - 1
        }
        play(context, prevIndex)
    }

    fun seekTo(ms: Int) {
        mediaPlayer?.seekTo(ms)
    }

    fun getCurrentSong(): Song? {
        return if (currentIndex != -1) songList[currentIndex] else null
    }

    fun getDuration(): Int = mediaPlayer?.duration ?: 0
    fun getCurrent(): Int = mediaPlayer?.currentPosition ?: 0
    fun isPlaying(): Boolean = mediaPlayer?.isPlaying ?: false
}