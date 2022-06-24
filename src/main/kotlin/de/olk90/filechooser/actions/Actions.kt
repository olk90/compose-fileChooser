package de.olk90.filechooser.actions

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import java.io.File

@Composable
fun FileChooserButton(isDialogOpen: MutableState<Boolean>) {
    IconButton(
        onClick = {
            isDialogOpen.value = true
        }) {
        Icon(
            Icons.Filled.Home,
            contentDescription = "Add new directory"
        )
    }
}

@Composable
fun SelectFileButton(isDialogOpen: MutableState<Boolean>, directory: MutableState<File>, path: MutableState<String>) {
    IconButton(
        onClick = {
            path.value = directory.value.path
            isDialogOpen.value = false
        }) {
        Icon(
            Icons.Filled.Check,
            contentDescription = "Accept selection"
        )
    }
}

@Composable
fun CancelButton(isDialogOpen: MutableState<Boolean>) {
    IconButton(
        onClick = {
            isDialogOpen.value = false
        }) {
        Icon(
            Icons.Filled.ExitToApp,
            contentDescription = "Cancel selection"
        )
    }
}
