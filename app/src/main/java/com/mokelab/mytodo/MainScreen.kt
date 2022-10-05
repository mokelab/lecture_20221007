package com.mokelab.mytodo

import android.text.format.DateFormat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: MainViewModel, toCreate: () -> Unit, toDetail: (Int) -> Unit) {
    val list by viewModel.todoList.observeAsState(emptyList())

    Scaffold(topBar = {},
        floatingActionButton = {
            FloatingActionButton(onClick = toCreate) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(list) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        toDetail(it._id)
                    }) {
                    ListItem(
                        headlineText = {
                            Text(it.title)
                        },
                        supportingText = {
                            Text(
                                DateFormat.format(
                                    "yyyy-MM-dd hh:mm:ss",
                                    it.created
                                ).toString()
                            )
                        }
                    )
                    Divider()
                }
            }
        }
    }
}