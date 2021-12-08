package com.example.workforfirstsem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.workforfirstsem.adapter.SongListAdapter
import com.example.workforfirstsem.model.SongRepository

class ListSongsFragment : Fragment(R.layout.fragment_list_songs) {

    private var songListAdapter: SongListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        songListAdapter = SongListAdapter {
            showSong(it)
            songListAdapter?.submitList(SongRepository.songsList)
        }

        with(view) {
            findViewById<RecyclerView>(R.id.songs).run {
                adapter = songListAdapter
            }
        }

        songListAdapter?.submitList(SongRepository.songsList)
    }

    private fun showSong(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        val songFragment = SongFragment()
        songFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, songFragment)
            ?.commit()
    }

}
