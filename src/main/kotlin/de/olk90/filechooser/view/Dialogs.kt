package de.olk90.filechooser.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.olk90.filechooser.actions.*
import java.io.File

enum class FileChooserMode {
    FILE, DIRECTORY
}

@Composable
fun FileChooser(
    isDialogOpen: MutableState<Boolean>,
    path: MutableState<String>,
    filters: List<FileFilter>,
    mode: FileChooserMode
) {

    if (path.value.isEmpty()) {
        path.value = System.getProperty("user.home")
    }

    val showHidden = remember { mutableStateOf(false) }
    val directory = remember { mutableStateOf(File(path.value)) }
    val selectedFilter = remember { mutableStateOf(defaultFilter) }

    Column {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Select File") },
                    actions = {
                        NewDirectoryButton()
                        DeleteDirectoryButton()
                        OpenHomeDirectoryButton(directory)
                        ToggleHiddenFilesButton(showHidden)
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                directory.value = directory.value.parentFile
                            },
                            enabled = directory.value.parentFile != null
                        ) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = "Go to parent folder"
                            )
                        }
                    },
                )
            },
            content = {
                Column(verticalArrangement = Arrangement.SpaceBetween) {
                    Row {
                        Box(Modifier.height(80.dp).fillMaxWidth()) {
                            TextField(
                                value = directory.value.path,
                                label = { Text("Selected Path") },
                                onValueChange = {
                                    directory.value = File(it)
                                },
                                modifier = Modifier.padding(10.dp).fillMaxSize(),
                                singleLine = true
                            )
                        }
                    }
                    Row {
                        Box(Modifier.fillMaxHeight(0.9f)) {
                            FileList(directory, showHidden, selectedFilter, mode)
                        }
                    }
                }
            },
            bottomBar = {
                BottomAppBar {
                    ButtonBar(isDialogOpen, directory, path, filters, selectedFilter, mode)
                }
            }
        )
    }
}
