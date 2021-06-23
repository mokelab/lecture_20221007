package com.mokelab.mytodo.page.edit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mokelab.mytodo.model.todo.ToDo
import com.mokelab.mytodo.repository.todo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class EditToDoViewModel @Inject constructor(
    private val repo: ToDoRepository
): ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val done = MutableLiveData<ToDo>()

    fun save(todo: ToDo, title: String, detail: String) {
        if (title.trim().isEmpty()) {
            errorMessage.value = "Please input title"
            return
        }

        viewModelScope.launch {
            try {
                val updatedToDo = repo.update(todo, title, detail)
                done.value = updatedToDo
            } catch (e: Exception) {
                errorMessage.value = e.toString()
            }
        }
    }
}