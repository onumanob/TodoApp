package com.ashik.todoapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ashik.todoapp.R
import com.ashik.todoapp.databinding.TodoRowBinding
import com.ashik.todoapp.entities.TodoModel
import com.ashik.todoapp.utils.todo_delete
import com.ashik.todoapp.utils.todo_edit
import com.ashik.todoapp.utils.todo_edit_completed

class TodoAdapter(val actionCallback: (TodoModel, String) -> Unit) : ListAdapter<TodoModel, TodoAdapter.TodoViewHolder>(TodoDiffCallback()) {

    class TodoViewHolder(
        private val binding: TodoRowBinding,
        val actionCallback: (TodoModel, String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todoModel: TodoModel) {
            binding.todo = todoModel
            binding.rowCompleteCB.setOnClickListener {
                todoModel.completed = !todoModel.completed
                actionCallback(todoModel, todo_edit_completed)
            }

            binding.rowMenu.setOnClickListener {
                val popupMenu = PopupMenu(it.context, it)
                val inflater = popupMenu.menuInflater
                inflater.inflate(R.menu.row_item_menu, popupMenu.menu)
                popupMenu.show()
                popupMenu.setOnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.item_delete -> {
                            actionCallback(todoModel, todo_delete)
                            true
                        }
                        R.id.item_edit -> {
                            actionCallback(todoModel, todo_edit)
                            true
                        }
                        else -> false
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = TodoRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding, actionCallback)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val todoModel = getItem(position)
        holder.bind(todoModel)
    }
}

class TodoDiffCallback : DiffUtil.ItemCallback<TodoModel>() {
    override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
        return oldItem.todoId == newItem.todoId
    }

    override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
        return oldItem == newItem
    }

}