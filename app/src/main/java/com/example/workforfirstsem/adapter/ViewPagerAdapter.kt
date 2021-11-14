package com.example.workforfirstsem.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workforfirstsem.R
import com.example.workforfirstsem.model.CVElem

class ViewPagerAdapter(
    private val list: ArrayList<Int>
) : RecyclerView.Adapter<PagerVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(
            LayoutInflater.from(parent.context).inflate(
            R.layout.item_page,
            parent,
            false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) = holder.itemView.run {
        findViewById<ImageView>(R.id.imageView).setImageResource(list[position])
    }
}

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)
