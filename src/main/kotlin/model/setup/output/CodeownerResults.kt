package model.setup.output

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CodeownerResults(
    val filename: String,
)