package com.example.workforfirstsem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.workforfirstsem.adapter.CVListAdapter
import com.example.workforfirstsem.model.CVRepository

class ListCardViewsFragment : Fragment(R.layout.fragment_list_card_views) {

    private var cvListAdapter: CVListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cvListAdapter = CVListAdapter()

        val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)

        with(view) {
            findViewById<RecyclerView>(R.id.card_views).run {
                adapter = cvListAdapter
                addItemDecoration(decorator)
            }
        }

        cvListAdapter?.submitList(CVRepository.cardViews)
    }
}
