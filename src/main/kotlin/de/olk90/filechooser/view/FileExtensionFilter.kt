package de.olk90.filechooser.view

class FileExtensionFilter(
    val fileExtension: String,
    private val description: String
) {
    override fun toString(): String {
        return "$description (*.$fileExtension)"
    }
}

val defaultFilter = FileExtensionFilter("*", "All files")

val textFiles = listOf(
    defaultFilter,
    FileExtensionFilter("csv", "CSV file"),
    FileExtensionFilter("log", "Log file"),
    FileExtensionFilter("txt", "Text file")
)