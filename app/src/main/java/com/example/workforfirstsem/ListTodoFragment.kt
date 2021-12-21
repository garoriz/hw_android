package com.example.workforfirstsem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workforfirstsem.adapter.TodoListAdapter
import com.example.workforfirstsem.model.AppDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListTodoFragment : Fragment(R.layout.fragment_list_todo) {

    private var todoListAdapter: TodoListAdapter? = null

    private lateinit var db: AppDatabase

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase(requireContext())

        val todos = db.todoDao().getAll() as MutableList

        todoListAdapter = TodoListAdapter({
            showEditFragment(it)
            todoListAdapter?.submitList(todos)
        }, {
            deleteTodo(it)
            todoListAdapter?.submitList(todos)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.container, ListTodoFragment())
                ?.commit()
        })

        with(view) {
            findViewById<RecyclerView>(R.id.todos).run {
                adapter = todoListAdapter
            }
            findViewById<FloatingActionButton>(R.id.add_todo).setOnClickListener {
                showBlankEditFragment()
            }
        }

        if (todos.size > 0)
            todoListAdapter?.submitList(todos)
        else
            view.findViewById<TextView>(R.id.textView).visibility = View.VISIBLE
    }

    private fun showEditFragment(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        val editTodoFragment = EditTodoFragment()
        editTodoFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, editTodoFragment)
            ?.commit()
    }

    private fun showBlankEditFragment() {
        val editTodoFragment = EditTodoFragment()
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, editTodoFragment)
            ?.commit()
    }

    private fun deleteTodo(id: Int) {
        val todo = db.todoDao().getById(id)
        db.todoDao().delete(todo)
    }
}
