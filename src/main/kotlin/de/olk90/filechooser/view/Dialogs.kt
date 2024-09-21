package de.olk90.filechooser.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.olk90.filechooser.actions.*
import de.olk90.filechooser.logic.createNewFile
import de.olk90.filechooser.logic.deleteFile
import java.io.File
import java.io.FileFilter

enum class FileChooserMode {
    FILE, DIRECTORY
}

val USER_HOME: String = System.getProperty("user.home")

@Composable
fun FileChooser(
    isDialogOpen: MutableState<Boolean>,
    path: MutableState<String>,
    filters: List<FileExtensionFilter>,
    mode: FileChooserMode
) {

    if (path.value.isEmpty()) {
        path.value = USER_HOME
    }

    val showHidden = remember { mutableStateOf(false) }
    val directory = remember { mutableStateOf(File(path.value)) }
    val selectedFilter = remember { mutableStateOf(defaultFilter) }

    val title = if (mode == FileChooserMode.FILE) "Select File" else "Select Directory"

    Column {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(title) },
                    actions = {
                        NewDirectoryButton(directory, mode)
                        DeleteDirectoryButton(directory)
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
                                Icons.AutoMirrored.Filled.ArrowBack,
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

@Composable
fun NewFileDialog(dialogOpen: MutableState<Boolean>, directory: MutableState<File>, mode: FileChooserMode) {
    val fileName = remember { mutableStateOf("") }
    val parent = directory.value
    val listFiles = parent.listFiles(FileFilter { it.name == fileName.value })
    AlertDialog(
        title = { },
        onDismissRequest = { },
        modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentHeight(),
        text = {
            TextField(
                modifier = Modifier.fillMaxWidth().padding(10.dp),
                value = fileName.value,
                label = { Text("Create new file") },
                onValueChange = { fileName.value = it }
            )
        },
        confirmButton = {
            IconButton(
                onClick = {
                    createNewFile(parent, fileName, mode, directory, dialogOpen)
                },
                enabled = listFiles.isEmpty() && fileName.value.isNotBlank() && fileName.value.isNotEmpty()
            ) {
                Icon(
                    Icons.Filled.Check,
                    contentDescription = "Create new file"
                )
            }
        },
        dismissButton = {
            IconButton(
                onClick = { dialogOpen.value = false }
            ) {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = "Cancel creation"
                )
            }
        }
    )
}

@Composable
fun DeleteFileDialog(dialogOpen: MutableState<Boolean>, directory: MutableState<File>) {
    val file = directory.value
    AlertDialog(
        onDismissRequest = { },
        modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentHeight(),
        title = {
            Text("Confirm deletion")
        },
        text = {
            Text("Really delete ${file.path}?")
        },
        confirmButton = {
            IconButton(
                onClick = { deleteFile(directory, dialogOpen) }
            ) {
                Icon(
                    Icons.Filled.Check,
                    contentDescription = "Confirm deletion"
                )
            }
        },
        dismissButton = {
            IconButton(
                onClick = { dialogOpen.value = false }
            ) {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = "Cancel deletion"
                )
            }
        }
    )
}
