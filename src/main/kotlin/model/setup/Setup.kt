package model.setup

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import model.setup.output.Output
import model.setup.template.Template

@JsonClass(generateAdapter = true)
data class Setup(
    val input: Input,

    @Json(name = "package")
    val packageDetails: PackageDetails,

    val template: Template,
    val output: Output,
)
