package model.setup

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Input(
    val codeownerPath: String,
)