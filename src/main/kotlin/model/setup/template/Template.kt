package model.setup.template

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Template(
    val header: Header,
)