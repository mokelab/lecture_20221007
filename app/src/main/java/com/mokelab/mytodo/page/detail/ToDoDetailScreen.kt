package com.mokelab.mytodo.page.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mokelab.mytodo.model.todo.ToDo

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun ToDoDetailScreen(viewModel: ToDoDetailViewModel, back: () -> Unit) {
    val todo by viewModel.todo2.collectAsStateWithLifecycle(initialValue = ToDo.empty)

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(todo.title)
            },
            navigationIcon = {
                IconButton(onClick = back) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            })
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 8.dp)
                .fillMaxSize()
        ) {
            Text(
                todo.title,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineLarge,
            )
            Text(
                todo.detail, modifier = Modifier.fillMaxSize(),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}