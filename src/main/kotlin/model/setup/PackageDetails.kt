package model.setup

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PackageDetails(
    val full: String,
    val relative: String,
)