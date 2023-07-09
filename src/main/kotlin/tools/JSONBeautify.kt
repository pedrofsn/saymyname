package tools

import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import okio.Buffer

class JSONBeautify {
    fun beautifyJSON(json: String, printContent: Boolean = false): String {
        val source: Buffer = Buffer().writeUtf8(json)
        val reader: JsonReader = JsonReader.of(source)
        val value = reader.readJsonValue()
        val adapterFormatted = Moshi.Builder().build().adapter(Any::class.java).indent("    ")
        val jsonBeautiful = adapterFormatted.toJson(value).orEmpty()
        if(printContent) {
            println(jsonBeautiful)
        }
        return jsonBeautiful
    }
}