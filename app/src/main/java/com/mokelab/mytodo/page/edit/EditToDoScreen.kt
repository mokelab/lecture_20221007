package com.mokelab.mytodo.page.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mokelab.mytodo.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditToDoScreen(viewModel: EditToDoViewModel, back: () -> Unit) {
    val uiState = viewModel.uiState.observeAsState()
    var title by rememberSaveable { mutableStateOf("") }
    var detail by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(uiState.value) {
        when (val state = uiState.value) {
            EditToDoViewModel.UiState.Initial -> {
                viewModel.load()
            }
            is EditToDoViewModel.UiState.Success -> {
                title = state.todo.title
                detail = state.todo.detail
                viewModel.toIdle(state.todo)
            }
            is EditToDoViewModel.UiState.Idle -> {
                // nop
            }
            EditToDoViewModel.UiState.Saved -> {
                back()
            }
            null -> {}
        }
    }

    Scaffold(topBar = {
        TopAppBar(title = {},
            navigationIcon = {
                IconButton(onClick = back) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            },
            actions = {
                IconButton(onClick = { viewModel.save(title, detail) }) {
                    Icon(
                        imageVector = Icons.Default.Done,
                        contentDescription = stringResource(R.string.save)
                    )
                }
            }
        )
    }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1,
                label = {
                    Text(stringResource(id = R.string.title))
                },
                value = title,
                onValueChange = { title = it }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxSize(),
                label = {
                    Text(stringResource(id = R.string.detail))
                },
                value = detail,
                onValueChange = { detail = it }
            )
        }
    }
}