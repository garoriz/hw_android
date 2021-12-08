package com.example.workforfirstsem.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.workforfirstsem.diffutils.SongDiffItemCallback
import com.example.workforfirstsem.model.Song

class SongListAdapter (
    private val action : (Int) -> Unit
) : ListAdapter<Song, SongHolder>(SongDiffItemCallback()){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SongHolder = SongHolder.create(parent, action)

    override fun onBindViewHolder(holder: SongHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: MutableList<Song>?) {
        super.submitList(if (list == null) null else ArrayList(list))
    }
}
