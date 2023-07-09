package model.output

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Wrapper(
    val monolithicPackage: String,
    @Json(name = "content") val list: List<Group>
)