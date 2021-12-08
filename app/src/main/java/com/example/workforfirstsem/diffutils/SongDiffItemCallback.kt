package com.example.workforfirstsem.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.workforfirstsem.model.Song

class SongDiffItemCallback : DiffUtil.ItemCallback<Song>() {
    override fun areItemsTheSame(
        oldItem: Song,
        newItem: Song
    ): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(
        oldItem: Song,
        newItem: Song
    ): Boolean = oldItem == newItem
}
