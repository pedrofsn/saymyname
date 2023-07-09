package tools.codeowners

import adapters.moshiAdapterWrapper
import model.output.Codeowner
import model.output.Group
import model.output.Wrapper
import model.setup.template.Header
import utilities.createGroup
import utilities.toCodeowner
import java.io.File

class CodeOwnerJsonCreator(private val pathname: String, private val header: Header) {

    fun generateJSON(monolithPackage: String): String {
        val wrapper = Wrapper(monolithicPackage = monolithPackage, list = populateGroups())
        return moshiAdapterWrapper.toJson(wrapper)
    }

    private fun populateGroups(): MutableList<Group> {
        val groups = mutableListOf<Group>()

        val file = File(pathname)
        file.useLines { lines ->
            var groupName = ""
            var codeOwnerEntries = mutableListOf<Codeowner>()

            lines.forEach { entireLine ->
                val line = entireLine.trim()
                if (isLineValid(line)) {
                    if (isLineGroupName(line)) {
                        groupName = line
                    } else {
                        if (isEndOfGroupDeclarationBlock(line).not()) {
                            val codeOwnerEntry = line.toCodeowner()
                            codeOwnerEntries.add(codeOwnerEntry)
                        } else {
                            if (groupName.isNotBlank()) {
                                val group = groupName.createGroup(
                                    header = header,
                                    entries = codeOwnerEntries
                                )
                                groups.add(group)

                                // clear variables
                                groupName = ""
                                codeOwnerEntries = mutableListOf()
                            }
                        }
                    }
                }
            }
        }

        return groups
    }

    private fun isEndOfGroupDeclarationBlock(line: String) = line.isBlank()
    private fun isLineGroupName(line: String) = line.startsWith(header.start)
    private fun isLineValid(line: String) = line.contains(header.ignore).not()
}