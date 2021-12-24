package com.example.workforfirstsem

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.workforfirstsem.databinding.FragmentEditTodoBinding
import com.example.workforfirstsem.model.AppDatabase
import com.example.workforfirstsem.model.entity.Todo
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class EditTodoFragment : Fragment(R.layout.fragment_edit_todo) {

    private lateinit var db: AppDatabase

    private lateinit var binding: FragmentEditTodoBinding

    private lateinit var scope: CoroutineScope

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentEditTodoBinding.bind(view)

        db = AppDatabase(requireContext())

        scope = CoroutineScope(Dispatchers.Main + Job())

        with(binding) {
            val id = arguments?.getInt("id")
            if (id != null) {
                scope.launch {
                    val todo = db.todoDao().getById(id)
                    etTitle.editText?.setText(todo.title)
                    etDesc.editText?.setText(todo.desc)
                }
                btnSave.setOnClickListener {
                    update(view, id)
                }
            } else {
                btnSave.setOnClickListener {
                    save(view)
                }
            }
        }
    }

    private fun save(view: View) {
        with(binding) {
            val title = etTitle.editText?.text.toString()
            val desc = etDesc.editText?.text.toString()
            if (title == "" || desc == "") {
                Snackbar.make(view, "Поля не могут быть пустыми", LENGTH_LONG).show()
            } else {
                scope.launch {
                    db.todoDao().save(
                        Todo(
                            title,
                            desc
                        )
                    )
                }
                activity?.supportFragmentManager?.popBackStack()
            }
        }
    }

    private fun update(view: View, id: Int) {
        with(binding) {
            val title = etTitle.editText?.text.toString()
            val desc = etDesc.editText?.text.toString()
            if (title == "" || desc == "") {
                Snackbar.make(view, "Поля не могут быть пустыми", LENGTH_LONG).show()
            } else {
                scope.launch {
                    db.todoDao().update(
                        Todo(id, title, desc)
                    )
                }
                activity?.supportFragmentManager?.popBackStack()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        scope.cancel()
    }
}
