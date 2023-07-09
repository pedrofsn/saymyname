package model.setup.output

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Output(
    val results: SearchResults,
    val codeowner: CodeownerResults,
)
