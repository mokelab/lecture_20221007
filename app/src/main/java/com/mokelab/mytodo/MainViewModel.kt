package com.mokelab.mytodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mokelab.mytodo.repository.todo.ToDoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: ToDoRepository
): ViewModel() {

    val todoList = repo.getAll().asLiveData()
}