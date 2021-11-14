package com.example.workforfirstsem.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.workforfirstsem.diffutils.CVDiffItemCallback
import com.example.workforfirstsem.model.CVElem

class CVListAdapter: ListAdapter<CVElem, CVElemHolder>(CVDiffItemCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CVElemHolder = CVElemHolder.create(parent)

    override fun onBindViewHolder(holder: CVElemHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: MutableList<CVElem>?) {
        super.submitList(if (list == null) null else ArrayList(list))
    }

}
