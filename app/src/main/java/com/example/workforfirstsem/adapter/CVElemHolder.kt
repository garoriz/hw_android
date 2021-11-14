package com.example.workforfirstsem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.workforfirstsem.R
import com.example.workforfirstsem.databinding.ItemCvBinding
import com.example.workforfirstsem.model.CVElem
import com.example.workforfirstsem.model.CVRepository
import com.example.workforfirstsem.model.Elem

class CVElemHolder(
    private val binding: ItemCvBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: CVElem) {
        with(binding) {
            viewPager2.adapter = ViewPagerAdapter(item.images)
        }
    }

    companion object {

        fun create(
            parent: ViewGroup
        ) = CVElemHolder(
            ItemCvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}
