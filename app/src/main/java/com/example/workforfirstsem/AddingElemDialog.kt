package com.example.workforfirstsem

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.workforfirstsem.adapter.ElemListAdapter
import com.example.workforfirstsem.databinding.DialogAddingElemBinding
import com.example.workforfirstsem.model.Elem
import com.example.workforfirstsem.model.ElemRepository

class AddingElemDialog : DialogFragment() {

    private lateinit var binding: DialogAddingElemBinding

    var positiveCallback: ((Unit) -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setView(DialogAddingElemBinding.inflate(layoutInflater).let {
                binding = it
                it.root
            })
            .setPositiveButton("OK") { dialog, _ ->
                with(binding) {
                    var pos = etPos.text.toString()
                    val sizeRepository = ElemRepository.elems.size
                    if (pos.equals("")) {
                        pos = sizeRepository.toString()
                    }
                    if (pos.toInt() > sizeRepository) {
                        pos = sizeRepository.toString()
                    }
                    ElemRepository.elems.add(
                        pos.toInt(),
                        Elem(etHeader.text.toString(), etDesc.text.toString())
                    )
                }
                positiveCallback?.invoke(Unit)
            }
            .setNegativeButton("ОТМЕНА") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

    companion object {
        fun show(
            fragmentManager: FragmentManager,
            positive: (Unit) -> Unit,
        ) {
            AddingElemDialog().apply {
                positiveCallback = positive
                show(fragmentManager, AddingElemDialog::class.java.name)
            }
        }
    }
}
