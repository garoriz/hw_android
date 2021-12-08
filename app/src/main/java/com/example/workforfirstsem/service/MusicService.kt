package com.example.workforfirstsem.service

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.example.workforfirstsem.model.Song

class MusicService : Service() {

    private var mediaPlayer: MediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
    }

    private var currentSong: Song? = null

    inner class LocaleBinder : Binder() {

        fun play(song: Int, isNewSong: Boolean) {
            playLocaleMusic(song, isNewSong)
        }

        fun pause() = this@MusicService.pause()

        fun getSong(): Song? = currentSong

        fun setCurrentSong(song: Song?) {
            showSong(song)
        }

        fun stop() = this@MusicService.stop()

        fun isPlaying() = this@MusicService.isPlaying()

    }

    override fun onBind(intent: Intent?): IBinder = LocaleBinder()

    private fun pause() = mediaPlayer.pause()

    private fun stop() = mediaPlayer.stop()

    private fun isPlaying() = mediaPlayer.isPlaying

    private fun showSong(song: Song?) {
        currentSong = song
    }

    private fun playLocaleMusic(song: Int, isNewSong: Boolean) {
        if (mediaPlayer.isPlaying) mediaPlayer.stop()
        if (isNewSong) mediaPlayer = MediaPlayer.create(applicationContext, song)
        mediaPlayer.run {
            start()
            setOnCompletionListener {
                stop()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
