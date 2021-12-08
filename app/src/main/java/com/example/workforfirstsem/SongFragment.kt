package com.example.workforfirstsem

import android.content.ComponentName
import android.content.Context.BIND_AUTO_CREATE
import android.content.ServiceConnection
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import androidx.fragment.app.Fragment
import android.view.View
import com.example.workforfirstsem.databinding.FragmentSongBinding
import com.example.workforfirstsem.model.Song
import com.example.workforfirstsem.model.SongRepository
import com.example.workforfirstsem.service.MusicService

class SongFragment : Fragment(R.layout.fragment_song) {

    private lateinit var binding: FragmentSongBinding

    private var binder: MusicService.LocaleBinder? = null

    private var song: Song? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(
            name: ComponentName?,
            service: IBinder?
        ) {
            binder = service as? MusicService.LocaleBinder

            if (binder != null) {
                setMusicAndButtons()
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            binder = null
        }

    }

    private fun setMusicAndButtons() {
        song = getSongFromRepo()
        with(binding) {
            tvTitle.text = song!!.title
            tvSinger.text = song!!.singer
            ivCover.setImageResource(song!!.cover)

            if (binder?.getSong() != song) {
                binder?.play(song!!.song, true)
                binder?.setCurrentSong(song)
            } else if (binder?.isPlaying() == false) {
                btnPlay.visibility = View.VISIBLE
                btnPause.visibility = View.GONE
            }

            btnPlay.setOnClickListener {
                var isNewSong = true
                if (song!! == binder?.getSong())
                    isNewSong = false
                else
                    binder?.setCurrentSong(song)
                binder?.play(song!!.song, isNewSong)
                btnPlay.visibility = View.GONE
                btnPause.visibility = View.VISIBLE
            }
            btnPause.setOnClickListener {
                binder?.pause()
                btnPlay.visibility = View.VISIBLE
                btnPause.visibility = View.GONE
            }
            btnStop.setOnClickListener {
                binder?.stop()
                binder?.setCurrentSong(null)
                btnPlay.visibility = View.VISIBLE
                btnPause.visibility = View.GONE
            }
            btnPrev.setOnClickListener {
                val bundle = Bundle()
                val newId = getPrevSongId(song!!)
                bundle.putInt("id", newId)
                val songFragment = SongFragment()
                songFragment.arguments = bundle
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, songFragment)
                    ?.commit()
            }
            btnNext.setOnClickListener {
                val bundle = Bundle()
                val newId = getNextSongId(song!!)
                bundle.putInt("id", newId)
                val songFragment = SongFragment()
                songFragment.arguments = bundle
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, songFragment)
                    ?.commit()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSongBinding.bind(view)

        activity?.bindService(
            Intent(this.context, MusicService::class.java),
            connection,
            BIND_AUTO_CREATE
        )

        setMusicAndButtons()
    }

    private fun getNextSongId(song: Song): Int {
        val songs = SongRepository.songsList
        for (i in 0 until songs.size) {
            if (songs[i].id == song.id && i != songs.size - 1)
                return songs[i + 1].id
        }
        return songs[0].id
    }

    private fun getPrevSongId(song: Song): Int {
        val songs = SongRepository.songsList
        for (i in 0 until songs.size) {
            if (songs[i].id == song.id && i != 0)
                return songs[i - 1].id
        }
        return songs[songs.size - 1].id
    }

    private fun getSongFromRepo(): Song? {
        val songs = SongRepository.songsList
        for (s in songs) {
            if (s.id == arguments?.getInt("id")) {
                return s
            }
        }
        return null
    }
}
