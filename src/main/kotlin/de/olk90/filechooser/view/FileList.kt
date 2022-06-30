package de.olk90.filechooser.view

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.io.File


@Composable
fun FileList(
    parentDirectory: MutableState<File>,
    showHidden: MutableState<Boolean>,
    selectedFilter: MutableState<FileExtensionFilter>,
    mode: FileChooserMode
) {
    val scroll = rememberScrollState()
    FileListBody(scroll, parentDirectory, showHidden, selectedFilter, mode)
}

@Composable
fun FileListBody(
    scroll: ScrollState,
    parentDirectory: MutableState<File>,
    showHidden: MutableState<Boolean>,
    selectedFilter: MutableState<FileExtensionFilter>,
    mode: FileChooserMode
) {
    val file = parentDirectory.value
    var directoryContent = if (file.isDirectory) file.listFiles() else file.parentFile.listFiles()

    if (!directoryContent.isNullOrEmpty()) {
        if (!showHidden.value) {
            directoryContent = directoryContent.filter { !it.name.startsWith(".") }.toTypedArray()
        }
        if (mode == FileChooserMode.FILE) {
            val sf = selectedFilter.value
            if (defaultFilter != sf) {
                directoryContent =
                    directoryContent.filter { it.extension == sf.fileExtension || it.isDirectory }.toTypedArray()
            }
        } else {
            directoryContent = directoryContent.filter { it.isDirectory }.toTypedArray()
        }
        directoryContent.sortBy { it.name }
    }

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.verticalScroll(scroll).fillMaxSize()) {
            directoryContent?.forEach {
                Box(
                    modifier = Modifier.padding(10.dp).fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    FileListItem(it, parentDirectory)
                }
            }
        }
    }
}

@Composable
fun FileListItem(file: File, parentDirectory: MutableState<File>) {
    Card {
        FileCardBody(file, parentDirectory)
    }
}

@Composable
fun FileCardBody(file: File, parentDirectory: MutableState<File>) {
    val isDir = file.isDirectory
    Row(
        modifier = Modifier.padding(10.dp).fillMaxWidth().clickable {
            parentDirectory.value = file
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isDir) {
            Icon(
                Icons.Filled.List,
                contentDescription = "Directory"
            )
        } else {
            Icon(
                Icons.Filled.Lock,
                contentDescription = "Single files"
            )
        }
        Text(file.name)
    }
}