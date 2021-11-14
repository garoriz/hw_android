package com.example.workforfirstsem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.workforfirstsem.adapter.ElemListAdapter
import com.example.workforfirstsem.model.ElemRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListTitlesFragment : Fragment(R.layout.fragment_list_titles) {

    private var elemListAdapter: ElemListAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        elemListAdapter = ElemListAdapter() {
            ElemRepository.elems.remove(it)
            elemListAdapter?.submitList(ElemRepository.elems)
        }

        val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)

        with(view) {
            findViewById<RecyclerView>(R.id.elems).run {
                adapter = elemListAdapter
                addItemDecoration(decorator)
            }
            findViewById<FloatingActionButton>(R.id.add_elem).setOnClickListener {
                showDialog()
            }
            val itemTouchHelper = ItemTouchHelper(SwipeToDelete(elemListAdapter!!))
            itemTouchHelper.attachToRecyclerView(findViewById(R.id.elems))
        }

        elemListAdapter?.submitList(ElemRepository.elems)
    }

    private fun showDialog() {
        AddingElemDialog.show(childFragmentManager,
            positive = { elemListAdapter?.submitList(ElemRepository.elems) })
    }
}
