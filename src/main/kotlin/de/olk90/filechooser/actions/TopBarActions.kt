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
import java.io.File

@Composable
fun NewDirectoryButton() {
    IconButton(
        onClick = {
            // TODO
        }
    ) {
        Icon(
            Icons.Filled.Add,
            contentDescription = "Add new directory"
        )
    }
}

@Composable
fun DeleteDirectoryButton() {
    IconButton(
        onClick = {
            // TODO
        }
    ) {
        Icon(
            Icons.Filled.Delete,
            contentDescription = "Delete directory"
        )
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
