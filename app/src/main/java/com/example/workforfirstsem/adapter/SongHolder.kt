package com.example.workforfirstsem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workforfirstsem.databinding.ItemSongBinding
import com.example.workforfirstsem.model.Song

class SongHolder(
    private val binding: ItemSongBinding,
    private val action: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var song: Song? = null

    init {
        itemView.setOnClickListener {
            song?.id?.also(action)
        }
    }

    fun bind(item: Song) {
        this.song = item
        with(binding) {
            if (item.title.length > 21) {
                val text = item.title.subSequence(0, 21).toString() + "..."
                tvTitle.text = text
            } else
                tvTitle.text = item.title
            ivCover.setImageResource(item.cover)
            if (item.singer.length > 32) {
                val text = item.singer.subSequence(0, 32).toString() + "..."
                tvSinger.text = text
            } else
                tvSinger.text = item.singer
            tvDuration.text = item.duration
        }

        itemView.setOnClickListener {
            action(item.id)
        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
            action: (Int) -> Unit
        ) = SongHolder(
            ItemSongBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), action
        )
    }
}
