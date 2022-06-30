package de.olk90.filechooser.logic

import androidx.compose.runtime.MutableState
import de.olk90.filechooser.view.FileChooserMode
import java.io.File
import kotlin.io.path.Path

fun createNewFile(
    parent: File,
    fileName: MutableState<String>,
    mode: FileChooserMode,
    directory: MutableState<File>,
    dialogOpen: MutableState<Boolean>
) {
    val path = Path(parent.path, fileName.value)
    val file = path.toFile()
    if (mode == FileChooserMode.FILE) {
        file.parentFile.mkdirs()
        file.createNewFile()
    } else {
        file.mkdirs()
    }
    directory.value = file
    dialogOpen.value = false
}

fun deleteFile(
    directory: MutableState<File>,
    dialogOpen: MutableState<Boolean>
) {
    val file = directory.value
    directory.value = file.parentFile
    file.deleteRecursively()
    dialogOpen.value = false
}
