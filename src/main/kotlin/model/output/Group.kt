package model.output

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Group(
    val name: String,
    val detail: String,
    val codeowners: List<Codeowner>
)