package tools

import java.io.File

class FileWritter {

    fun writeJSON(foldername: String = "results", filename: String, content: String) {
        val pathname = mkdirs(foldername)
        File("$pathname/$filename")
            .printWriter()
            .use { out -> out.println(content) }
    }

    private fun mkdirs(foldername: String): String {
        val pathname = "build/outputs/$foldername"
        val folder = File(pathname)
        if (folder.exists().not()) {
            folder.mkdirs()
        }
        return pathname
    }
}