package tools.setup

import model.setup.Setup
import adapters.moshiAdapterSetup
import java.io.File
import java.io.InputStream

class SetupJSONFile {

    fun getObject(filename: String = "setup.json"): Setup? {
        val file = File("inputs/setup.json")

        if (file.exists().not()) {
            throw RuntimeException("Não foi possível localizar o arquivo $filename!")
        }

        val inputStream: InputStream = file.inputStream()
        val inputString = inputStream.bufferedReader().use { it.readText() }

        return moshiAdapterSetup.fromJson(inputString)
    }
}