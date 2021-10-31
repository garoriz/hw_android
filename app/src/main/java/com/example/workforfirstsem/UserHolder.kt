package com.example.workforfirstsem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workforfirstsem.databinding.ItemUserBinding

class UserHolder(
    private val binding : ItemUserBinding,
    private val action : (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root){

    private var user : User? = null

    init {
        itemView.setOnClickListener {
            user?.id?.also(action)
        }
    }

fun bind(item : User) {
    this.user = item
    with(binding) {
        id.text = item.id.toString()
        name.text = item.name
        status.text = item.status
        photo.setImageResource(item.photo)

        itemView.setOnClickListener {
            action(item.id)
        }
    }
}

    companion object {
        fun create (
            parent: ViewGroup,
            action : (Int) -> Unit
        ) = UserHolder (
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), action
        )
    }
}
