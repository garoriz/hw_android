package com.example.workforfirstsem

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.workforfirstsem.adapter.TodoListAdapter
import com.example.workforfirstsem.model.AppDatabase
import com.example.workforfirstsem.model.entity.Todo
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*


class ListTodoFragment : Fragment(R.layout.fragment_list_todo) {

    private var todoListAdapter: TodoListAdapter? = null

    private lateinit var db: AppDatabase

    private lateinit var scope: CoroutineScope

    private lateinit var todos: MutableList<Todo>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase(requireContext())

        scope = CoroutineScope(Dispatchers.Main + Job())

        scope.launch {
            todos = db.todoDao().getAll() as MutableList<Todo>

            todoListAdapter = TodoListAdapter({
                showEditFragment(it)
                todoListAdapter?.submitList(todos)
            }, {
                scope.launch {
                    deleteTodo(it)
                    todos = db.todoDao().getAll() as MutableList<Todo>
                    todoListAdapter?.submitList(todos)
                    if (todos.isEmpty()) {
                        view.findViewById<TextView>(R.id.textView).visibility = View.VISIBLE
                    }
                }
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
    }

    private fun showEditFragment(id: Int) {
        val bundle = Bundle()
        bundle.putInt("id", id)
        val editTodoFragment = EditTodoFragment()
        editTodoFragment.arguments = bundle
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, editTodoFragment)
            ?.addToBackStack("edit_todo")
            ?.commit()
    }

    private fun showBlankEditFragment() {
        val editTodoFragment = EditTodoFragment()
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, editTodoFragment)
            ?.addToBackStack("edit_todo")
            ?.commit()
    }

    private suspend fun deleteTodo(id: Int) {
        val todo = db.todoDao().getById(id)
        db.todoDao().delete(todo)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        scope.cancel()
    }
}
