package de.olk90.filechooser.actions

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import de.olk90.filechooser.view.DeleteFileDialog
import de.olk90.filechooser.view.FileChooserMode
import de.olk90.filechooser.view.NewFileDialog
import de.olk90.filechooser.view.USER_HOME
import java.io.File

@Composable
fun NewDirectoryButton(directory: MutableState<File>, mode: FileChooserMode) {

    val newFileDialogOpen = remember { mutableStateOf(false) }

    IconButton(
        onClick = {
            newFileDialogOpen.value = true
        },
        enabled = directory.value.canWrite()
    ) {
        Icon(
            Icons.Filled.Add,
            contentDescription = "Add new directory"
        )
    }

    if (newFileDialogOpen.value) {
        NewFileDialog(newFileDialogOpen, directory, mode)
    }
}

@Composable
fun DeleteDirectoryButton(directory: MutableState<File>) {

    val deleteDialogOpen = remember { mutableStateOf(false) }

    IconButton(
        onClick = {
            deleteDialogOpen.value = true
        },
        enabled = directory.value.canWrite() && directory.value.path != USER_HOME
    ) {
        Icon(
            Icons.Filled.Delete,
            contentDescription = "Delete directory"
        )
    }

    if (deleteDialogOpen.value) {
        DeleteFileDialog(deleteDialogOpen, directory)
    }
}

@Composable
fun OpenHomeDirectoryButton(directory: MutableState<File>) {
    IconButton(
        onClick = {
            directory.value = File(System.getProperty("user.home"))
        }
    ) {
        Icon(
            Icons.Filled.Home,
            contentDescription = "Go to user home directory"
        )
    }
}

@Composable
fun ToggleHiddenFilesButton(showHidden: MutableState<Boolean>) {
    IconButton(
        onClick = {
            showHidden.value = !showHidden.value
        }
    ) {
        Icon(
            Icons.Filled.Search,
            contentDescription = "Show hidden files"
        )
    }
}
