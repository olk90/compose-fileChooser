package de.olk90.filechooser.actions

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import de.olk90.filechooser.view.FileFilter
import java.io.File


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


@Composable
fun FileFilterSelection(
    items: List<FileFilter>,
    selectedFilter: MutableState<FileFilter>,
    expanded: MutableState<Boolean>
) {
    var selectedIndex by remember { mutableStateOf(0) }
    Box(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = items[selectedIndex].toString(),
            onValueChange = {},
            readOnly = true,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(
                    onClick = {
                        expanded.value = true
                    }) {
                    if (expanded.value) {
                        Icon(
                            Icons.Filled.KeyboardArrowUp,
                            contentDescription = "Expanded menu"
                        )
                    } else {
                        Icon(
                            Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Collapsed menu"
                        )
                    }
                }
            }
        )

        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded.value = false
                }) {
                    Text(text = s.toString())
                }
            }
        }
    }

}

@Composable
fun ButtonBar(
    isDialogOpen: MutableState<Boolean>,
    directory: MutableState<File>,
    path: MutableState<String>,
    filters: List<FileFilter>,
    selectedFilter: MutableState<FileFilter>
) {
    val expanded = remember { mutableStateOf(false) }
    Row(Modifier.padding(10.dp)) {
        Column(Modifier.fillMaxWidth(0.3f)) {
            FileFilterSelection(filters, selectedFilter, expanded)
        }
        Column {
            Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.End) {
                SelectFileButton(isDialogOpen, directory, path)
                CancelButton(isDialogOpen)
            }
        }
    }
}