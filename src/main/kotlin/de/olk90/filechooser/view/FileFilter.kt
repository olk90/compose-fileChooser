package de.olk90.filechooser.view

class FileFilter(
    private val fileEnding: String,
    private val description: String
) {
    override fun toString(): String {
        return "$description (*.$fileEnding)"
    }
}

val textFiles = listOf(
    FileFilter("csv", "CSV file"),
    FileFilter("log", "Log file"),
    FileFilter("txt", "Text file")
)