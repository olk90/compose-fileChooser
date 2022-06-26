package de.olk90.filechooser

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import de.olk90.filechooser.actions.FileChooserButton
import de.olk90.filechooser.view.FileChooser
import de.olk90.filechooser.view.FileChooserMode
import de.olk90.filechooser.view.textFiles

@Composable
fun App() {

    val isFileChooserOpen = remember { mutableStateOf(false) }
    val isDirectoryChooserOpen = remember { mutableStateOf(false) }
    val filePath = remember { mutableStateOf("") }
    val directoryPath = remember { mutableStateOf("") }

    MaterialTheme {
        Column {
            OutlinedTextField(
                value = filePath.value,
                onValueChange = {
                    filePath.value = it
                },
                label = { Text("Select File") },
                modifier = Modifier.padding(10.dp).fillMaxWidth(),
                singleLine = true,
                trailingIcon = {
                    FileChooserButton(isFileChooserOpen)
                }
            )

            OutlinedTextField(
                value = directoryPath.value,
                onValueChange = {
                    directoryPath.value = it
                },
                label = { Text("Select Directory") },
                modifier = Modifier.padding(10.dp).fillMaxWidth(),
                singleLine = true,
                trailingIcon = {
                    FileChooserButton(isDirectoryChooserOpen)
                }
            )
        }

    }

    if (isFileChooserOpen.value) {
        FileChooser(isFileChooserOpen, filePath, textFiles, FileChooserMode.FILE)
    }
    if (isDirectoryChooserOpen.value) {
        FileChooser(isDirectoryChooserOpen, directoryPath, textFiles, FileChooserMode.DIRECTORY)
    }
}

fun main() = application {
    Window(title = "FileChooser Example", onCloseRequest = ::exitApplication) {
        App()
    }
}
