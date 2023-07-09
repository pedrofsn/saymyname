package utilities

import model.output.Codeowner
import model.output.Group
import model.setup.template.Header

private const val AT = "@"
private const val EMPTY_SPACE = " "

internal fun String.createGroup(header: Header, entries: List<Codeowner>): Group {
    val description = substringAfterLast(header.start)
    val splitted = description.split(header.separator)
    val groupName = splitted[0].trim()
    val groupDetail = splitted[1].trim()
    return Group(groupName, groupDetail, entries)
}

internal fun String.toCodeowner(): Codeowner {
    val values = split(EMPTY_SPACE)
    val groupBy = values.groupBy { value -> value.startsWith(AT) }
    val usernames = groupBy[true].orEmpty()
    val folder = groupBy[false].orEmpty().first()
    return Codeowner(folder, usernames)
}