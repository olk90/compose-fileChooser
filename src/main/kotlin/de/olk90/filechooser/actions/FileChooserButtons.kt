package de.olk90.filechooser.actions

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

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
