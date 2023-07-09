package tools.intellij

import java.io.File

class SearchResultsFromIntelliJIdea(private val targetPackage: String) {

    fun getLinesFormatted(): ArrayList<String> {
        val file = File("inputs/file_results.txt")

        if(file.exists().not()) {
            file.createNewFile()
            throw RuntimeException("Por favor popule o arquivo file_results.txt")
        }

        val linesFormatted = arrayListOf<String>()
        var lastPackageName = ""

        file.readLines().forEach { readedLine ->
            val line = readedLine.trim()
            val containsPackageName = line.startsWith(targetPackage)
            val containsCodeFileExtensionExpected = line.contains(".kt") || line.contains(".java")

            val result = when {
                containsPackageName -> {
                    lastPackageName = removeTextFoundUsage(line)
                    return@forEach
                }
                containsCodeFileExtensionExpected -> {
                    "$lastPackageName.${removeTextFoundUsage(line)}"
                }
                else -> return@forEach
            }

            if(result.isNotBlank()) {
                linesFormatted.add(result)
            }
        }

        return linesFormatted
    }

    private fun removeTextFoundUsage(text: String): String {
        return text.removeRange(text.indexOf("("), text.length).trim()
    }

}