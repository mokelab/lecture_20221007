package com.mokelab.mytodo.page.edit

import androidx.lifecycle.*
import com.mokelab.mytodo.model.todo.ToDo
import com.mokelab.mytodo.repository.todo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class EditToDoViewModel @Inject constructor(
    private val repo: ToDoRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    sealed interface UiState {
        object Initial : UiState
        data class Success(val todo: ToDo) : UiState
        data class Idle(val todo: ToDo) : UiState
        object Saved : UiState
    }

    private val todoId = savedStateHandle.get<Int>("id")
    val errorMessage = MutableLiveData<String>()
    val done = MutableLiveData<ToDo>()
    private val _uiState = MutableLiveData<UiState>(UiState.Initial)
    val uiState: LiveData<UiState> = _uiState

    fun load() {
        viewModelScope.launch {
            val todoId = todoId ?: return@launch
            val todo = repo.getById(todoId)
            _uiState.postValue(UiState.Success(todo))
        }
    }

    fun toIdle(todo: ToDo) {
        _uiState.postValue(UiState.Idle(todo))
    }

    fun save(title: String, detail: String) {
        val currentState = _uiState.value
        if (currentState !is UiState.Idle) return

        if (title.trim().isEmpty()) {
            errorMessage.value = "Please input title"
            return
        }

        viewModelScope.launch {
            try {
                repo.update(currentState.todo, title, detail)
                _uiState.postValue(UiState.Saved)
            } catch (e: Exception) {
                errorMessage.value = e.toString()
            }
        }
    }

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