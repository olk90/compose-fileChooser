package de.olk90.filechooser.view

class FileFilter(
    val fileExtension: String,
    private val description: String
) {
    override fun toString(): String {
        return "$description (*.$fileExtension)"
    }
}

val defaultFilter = FileFilter("*", "All files")

val textFiles = listOf(
    defaultFilter,
    FileFilter("csv", "CSV file"),
    FileFilter("log", "Log file"),
    FileFilter("txt", "Text file")
)