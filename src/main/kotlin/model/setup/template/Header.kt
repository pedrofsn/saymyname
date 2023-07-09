package model.setup.template

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Header(
    val start: String,
    val separator: String,
    val ignore: String,
)