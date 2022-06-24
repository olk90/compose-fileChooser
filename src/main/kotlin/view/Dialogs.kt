import actions.DeleteDirectoryButton
import actions.NewDirectoryButton
import actions.OpenHomeDirectoryButton
import actions.ToggleHiddenFilesButton
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import view.ButtonBar
import view.FileList
import java.io.File

@Composable
fun FileChooser(isDialogOpen: MutableState<Boolean>, path: MutableState<String>) {

    if (path.value.isEmpty()) {
        path.value = System.getProperty("user.home")
    }

    val showHidden = remember { mutableStateOf(false) }
    val directory = remember { mutableStateOf(File(path.value)) }

    Column {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Select File") },
                    actions = {
                        NewDirectoryButton()
                        DeleteDirectoryButton()
                        OpenHomeDirectoryButton(directory)
                        ToggleHiddenFilesButton(showHidden)
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                directory.value = directory.value.parentFile
                            }
                        ) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = "Go to parent folder"
                            )
                        }
                    },
                )
            },
            content = {
                Column(verticalArrangement = Arrangement.SpaceBetween) {
                    Row {
                        Box(Modifier.fillMaxHeight(0.9f)) {
                            Column(Modifier.fillMaxSize()) {
                                FileList(directory, showHidden)
                            }
                        }
                    }
                    Row {
                        ButtonBar(isDialogOpen, directory, path)
                    }
                }
            }
        )
    }
}