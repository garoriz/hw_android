package com.example.workforfirstsem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.workforfirstsem.R
import com.example.workforfirstsem.databinding.ItemTodoBinding
import com.example.workforfirstsem.model.entity.Todo

class TodoHolder(
    private val binding: ItemTodoBinding,
    private val action: (Int) -> Unit,
    private val delete: (Int) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private var todo: Todo? = null

    init {
        itemView.setOnClickListener {
            todo?.id?.also(action)
        }
        itemView.findViewById<ImageView>(R.id.delete_button).setOnClickListener {
            todo?.id?.also(delete)
        }
    }

    fun bind(item: Todo) {
        this.todo = item
        with(binding) {
            tvTitle.text = item.title
            tvDesc.text = item.desc
        }
    }

    companion object {

        fun create(
            parent: ViewGroup,
            action: (Int) -> Unit,
            delete: (Int) -> Unit,
        ) = TodoHolder(
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), action, delete
        )
    }
}
