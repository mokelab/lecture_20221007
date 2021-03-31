package com.mokelab.mytodo.repository.todo

interface ToDoRepository {
    suspend fun create(title: String, detail: String)
}