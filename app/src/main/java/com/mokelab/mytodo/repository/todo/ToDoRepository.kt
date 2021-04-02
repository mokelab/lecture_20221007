package com.mokelab.mytodo.repository.todo

import com.mokelab.mytodo.model.todo.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    fun getAll(): Flow<List<ToDo>>
    suspend fun create(title: String, detail: String)
}