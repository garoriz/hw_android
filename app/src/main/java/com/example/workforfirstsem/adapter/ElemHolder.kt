package com.example.workforfirstsem.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.workforfirstsem.R
import com.example.workforfirstsem.databinding.ItemElemBinding
import com.example.workforfirstsem.model.Elem

class ElemHolder(
    private val binding: ItemElemBinding,
    private val action : (Elem) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var elem : Elem? = null

    init {
        itemView.findViewById<ImageButton>(R.id.delete_button).setOnClickListener {
            elem?.also(action)
        }
    }

    fun bind(item: Elem) {
        this.elem = item
        with(binding) {
            title.text = item.title
            desc.text = item.desc
        }

        itemView.setOnClickListener {
            elem?.let { it1 -> action(it1) }
        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
            action : (Elem) -> Unit
            ) = ElemHolder(
            ItemElemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), action
        )
    }
}
