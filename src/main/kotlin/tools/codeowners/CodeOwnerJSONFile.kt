package tools.codeowners

import model.output.Wrapper
import adapters.moshiAdapterWrapper
import java.io.File
import java.io.InputStream

class CodeOwnerJSONFile {

    fun getWrapper(filename: String): Wrapper? {
        val file = File("$outputPath$filename")

        if(file.exists().not()) {
            throw RuntimeException("Não foi possível localizar o arquivo $filename!")
        }

        val inputStream: InputStream = file.inputStream()
        val inputString = inputStream.bufferedReader().use { it.readText() }

        return moshiAdapterWrapper.fromJson(inputString)
    }

    companion object {
        private const val outputPath = "build/outputs/results/"
    }
}