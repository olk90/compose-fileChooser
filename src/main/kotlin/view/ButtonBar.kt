package view

import CancelButton
import SelectFileButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import java.io.File

@Composable
fun ButtonBar(isDialogOpen: MutableState<Boolean>, directory: MutableState<File>, path: MutableState<String>) {
    Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.End) {
        SelectFileButton(isDialogOpen, directory, path)
        CancelButton(isDialogOpen)
    }
}