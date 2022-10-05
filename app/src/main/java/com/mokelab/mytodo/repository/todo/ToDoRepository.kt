package com.mokelab.mytodo.repository.todo

import com.mokelab.mytodo.model.todo.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    fun getAll(): Flow<List<ToDo>>
    suspend fun getById(id: Int): ToDo
    suspend fun create(title: String, detail: String)
    suspend fun update(todo: ToDo, title: String, detail: String): ToDo
    suspend fun delete(todo: ToDo)
}