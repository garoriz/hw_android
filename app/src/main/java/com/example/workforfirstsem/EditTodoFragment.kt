package com.example.workforfirstsem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workforfirstsem.adapter.TodoListAdapter
import com.example.workforfirstsem.databinding.FragmentEditTodoBinding
import com.example.workforfirstsem.model.AppDatabase
import com.example.workforfirstsem.model.entity.Todo
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar

class EditTodoFragment : Fragment(R.layout.fragment_edit_todo) {

    private lateinit var db: AppDatabase

    private lateinit var binding: FragmentEditTodoBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentEditTodoBinding.bind(view)

        db = AppDatabase(requireContext())

        with(binding) {
            val id = arguments?.getInt("id")
            if (id != null) {
                val todo = db.todoDao().getById(id)
                etTitle.editText?.setText(todo.title)
                etDesc.editText?.setText(todo.desc)
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

    fun save(view: View) {
        with (binding) {
            val title = etTitle.editText?.text.toString()
            val desc = etDesc.editText?.text.toString()
            if (title == "" || desc == "") {
                Snackbar.make(view, "Поля не могут быть пустыми", LENGTH_LONG).show()
            } else {
                db.todoDao().save(Todo(
                    title,
                    desc)
                )
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, ListTodoFragment())
                    ?.commit()
            }
        }
    }

    fun update(view: View, id: Int) {
        with (binding) {
            val title = etTitle.editText?.text.toString()
            val desc = etDesc.editText?.text.toString()
            if (title == "" || desc == "") {
                Snackbar.make(view, "Поля не могут быть пустыми", LENGTH_LONG).show()
            } else {
                db.todoDao().update(
                    Todo(id, title, desc)
                )
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.container, ListTodoFragment())
                    ?.commit()
            }
        }
    }
}
