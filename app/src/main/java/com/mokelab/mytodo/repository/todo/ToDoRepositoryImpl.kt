package com.mokelab.mytodo.repository.todo

import com.mokelab.mytodo.model.todo.ToDo
import com.mokelab.mytodo.model.todo.ToDoDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(
    private val dao: ToDoDAO
): ToDoRepository {
    override fun getAll(): Flow<List<ToDo>> {
        return dao.getAll()
    }

    override suspend fun create(title: String, detail: String) {
        val now = System.currentTimeMillis()
        val todo = ToDo(title = title, detail = detail, created = now, modified = now)
        withContext(Dispatchers.IO) {
            dao.create(todo)
        }
    }
}