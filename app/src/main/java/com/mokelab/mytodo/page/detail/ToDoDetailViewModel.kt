package com.mokelab.mytodo.page.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mokelab.mytodo.model.todo.ToDo

class ToDoDetailViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    val todo = savedStateHandle.getLiveData<ToDo>("todo")
}