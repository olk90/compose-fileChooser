package de.olk90.filechooser.actions

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun FileChooserButton(isDialogOpen: MutableState<Boolean>, icon: ImageVector = Icons.Filled.Home) {
    IconButton(
        onClick = {
            isDialogOpen.value = true
        }) {
        Icon(
            icon,
            contentDescription = "Add new directory"
        )
    }
}
