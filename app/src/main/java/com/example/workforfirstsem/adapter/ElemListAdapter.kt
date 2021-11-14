package com.example.workforfirstsem.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.workforfirstsem.diffutils.ElemDiffItemCallback
import com.example.workforfirstsem.model.Elem
import com.example.workforfirstsem.model.ElemRepository

class ElemListAdapter(
    private val action : (Elem) -> Unit
) : ListAdapter<Elem, ElemHolder>(ElemDiffItemCallback()){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ElemHolder = ElemHolder.create(parent, action)

    override fun onBindViewHolder(holder: ElemHolder, position: Int) =
        holder.bind(getItem(position))

    override fun submitList(list: MutableList<Elem>?) {
        super.submitList(if (list == null) null else ArrayList(list))
    }

    fun deleteItem(pos: Int) {
        ElemRepository.elems.removeAt(pos)
        submitList(ElemRepository.elems)
    }
}
