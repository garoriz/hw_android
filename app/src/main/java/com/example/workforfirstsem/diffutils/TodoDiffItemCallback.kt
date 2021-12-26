package com.example.workforfirstsem.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.workforfirstsem.model.entity.Todo

class TodoDiffItemCallback : DiffUtil.ItemCallback<Todo>() {

    override fun areItemsTheSame(
        oldItem: Todo,
        newItem: Todo
    ): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(
        oldItem: Todo,
        newItem: Todo
    ): Boolean = oldItem == newItem
}
