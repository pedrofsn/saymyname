package model.output

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Codeowner(
    val filePattern: String,
    private val usernames: List<String>
)