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

@Composable
fun App() {

    val isDialogOpen = remember { mutableStateOf(false) }
    val path = remember { mutableStateOf("") }

    MaterialTheme {
        OutlinedTextField(
            value = path.value,
            onValueChange = {
                path.value = it
            },
            label = { Text("Select File") },
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
            singleLine = true,
            trailingIcon = {
                FileChooserButton(isDialogOpen)
            }
        )

    }

    if (isDialogOpen.value) {
        FileChooser(isDialogOpen, path)
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
