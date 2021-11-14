package com.example.workforfirstsem.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.workforfirstsem.model.Elem

class ElemDiffItemCallback : DiffUtil.ItemCallback<Elem>() {
    override fun areItemsTheSame(
        oldItem: Elem,
        newItem: Elem
    ): Boolean = oldItem.title == newItem.title

    override fun areContentsTheSame(
        oldItem: Elem,
        newItem: Elem
    ): Boolean = oldItem == newItem
}
