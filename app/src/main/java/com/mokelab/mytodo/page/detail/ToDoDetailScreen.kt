package com.mokelab.mytodo.page.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mokelab.mytodo.R
import com.mokelab.mytodo.model.todo.ToDo

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun ToDoDetailScreen(viewModel: ToDoDetailViewModel, back: () -> Unit, toEdit: () -> Unit) {
    val todo by viewModel.todo2.collectAsStateWithLifecycle(initialValue = ToDo.empty)
    var expanded by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    val deleted by viewModel.deleted.observeAsState()

    LaunchedEffect(deleted) {
        if (deleted == true) back()
    }

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(todo.title)
            },
            navigationIcon = {
                IconButton(onClick = back) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            actions = {
                IconButton(onClick = toEdit) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                }
                IconButton(onClick = { expanded = !expanded }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                }
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    DropdownMenuItem(
                        text = {
                            Text(stringResource(R.string.delete))
                        }, onClick = {
                            expanded = false
                            showDeleteDialog = true
                        })
                }
            }
        )
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

    if (showDeleteDialog) {
        AlertDialog(
            text = { Text(stringResource(R.string.delete_message)) },
            onDismissRequest = { showDeleteDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    showDeleteDialog = false
                    viewModel.delete2()
                }) {
                    Text(stringResource(android.R.string.ok))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text(stringResource(android.R.string.cancel))
                }
            }
        )
    }
}