package com.example.workforfirstsem.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.workforfirstsem.model.CVElem

class CVDiffItemCallback : DiffUtil.ItemCallback<CVElem>(){

    override fun areItemsTheSame(
        oldItem: CVElem,
        newItem: CVElem
    ): Boolean = oldItem.images == newItem.images

    override fun areContentsTheSame(
        oldItem: CVElem,
        newItem: CVElem
    ): Boolean = oldItem == newItem
}
