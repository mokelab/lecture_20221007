package com.mokelab.mytodo.page.detail

import androidx.lifecycle.*
import com.mokelab.mytodo.model.todo.ToDo
import com.mokelab.mytodo.repository.todo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoDetailViewModel @Inject constructor(
    private val toDoRepository: ToDoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val todoId = savedStateHandle.getLiveData<Int>("id")
    val todo = savedStateHandle.getLiveData<ToDo>("todo")
    val errorMessage = MutableLiveData<String>()
    val deleted = MutableLiveData<Boolean>()

    val todo2 = todoId.asFlow().map {
        toDoRepository.getById(it)
    }

    fun delete() {
        viewModelScope.launch {
            try {
                val todo = this@ToDoDetailViewModel.todo.value ?: return@launch
                toDoRepository.delete(todo)
                deleted.value = true
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }

}