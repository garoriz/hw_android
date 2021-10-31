package com.example.workforfirstsem

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(
    private val list : List<User>,
    private val action : (Int) -> Unit
) : RecyclerView.Adapter<UserHolder>() {
    override fun onCreateViewHolder (parent: ViewGroup, viewType: Int) : UserHolder =
        UserHolder.create(parent, action)

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
