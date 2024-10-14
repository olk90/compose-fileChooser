package de.olk90.filechooser.logic

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.InsertDriveFile
import androidx.compose.material.icons.automirrored.filled.TextSnippet
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.AudioFile
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material.icons.filled.VideoFile
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.vector.ImageVector
import de.olk90.filechooser.view.FileChooserMode
import java.io.File
import java.net.URLConnection
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

fun getFileIcon(file: File): ImageVector {
    if (file.isDirectory) return Icons.Default.Folder

    val mimeType = URLConnection.guessContentTypeFromName(file.name) ?: return Icons.AutoMirrored.Filled.InsertDriveFile

    return when {
        mimeType.startsWith("image/") -> Icons.Default.Image
        mimeType.startsWith("video/") -> Icons.Default.VideoFile
        mimeType.startsWith("audio/") -> Icons.Default.AudioFile
        mimeType == "application/pdf" -> Icons.Default.PictureAsPdf
        mimeType.contains("word") -> Icons.Default.Description
        mimeType.contains("excel") || mimeType.contains("spreadsheet") -> Icons.Default.TableChart
        mimeType.contains("powerpoint") || mimeType.contains("presentation") -> Icons.Default.Slideshow
        mimeType.contains("zip") || mimeType.contains("compressed") -> Icons.Default.FolderZip
        mimeType.startsWith("text/") -> Icons.AutoMirrored.Filled.TextSnippet
        else -> Icons.AutoMirrored.Filled.InsertDriveFile
    }
}