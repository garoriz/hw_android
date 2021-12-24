package com.example.workforfirstsem.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.workforfirstsem.diffutils.TodoDiffItemCallback
import com.example.workforfirstsem.model.entity.Todo

class TodoListAdapter(
    private val action: (Int) -> Unit,
    private val delete: (Int) -> Unit,
) : ListAdapter<Todo, TodoHolder>(TodoDiffItemCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoHolder = TodoHolder.create(parent, action, delete)

    override fun onBindViewHolder(holder: TodoHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: MutableList<Todo>?) {
        super.submitList(if (list == null) null else ArrayList(list))
    }
}
