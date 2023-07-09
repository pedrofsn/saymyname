package tools

import model.output.Wrapper

class ResultMatcher(
    private val targetPackage: String,
    private val wrapper: Wrapper,
    private val filenameOutput: String
) {
    private val hashMap = hashMapOf<String, ArrayList<String>>()

    private fun String.toRealPackageName(monolithicPackage: String, targetPackage: String): String {
        return replace(monolithicPackage, targetPackage)
            .replace("/", ".")
            .dropLast(1)
    }

    fun matchWith(filesPath: List<String>): ResultMatcher {
        hashMap.clear()
        wrapper.list.forEach { group ->
            group.codeowners.forEach { codeOwnerEntry ->
                val packageNameFromCodeowner = codeOwnerEntry.filePattern

                if (packageNameFromCodeowner.startsWith("app/")) {
                    val sanitized = codeOwnerEntry.filePattern.toRealPackageName(
                        monolithicPackage = wrapper.monolithicPackage,
                        targetPackage = targetPackage
                    )

                    filesPath.forEach { line ->
                        if (line.isNotBlank() && line.contains(sanitized)) {
                            val data = line.trim()

                            val key = "${group.name} - ${group.detail}"
                            if (hashMap.contains(key)) {
                                hashMap[key]?.add(data)
                            } else {
                                hashMap[key] = arrayListOf(data)
                            }
                        }
                    }
                }
            }
        }
        return this
    }

    fun print() {
        val sb = StringBuffer()
        val breakLine = "\n"
        var totalFiles = 0
        hashMap.forEach { (key, value) ->
            totalFiles += value.size
            val group = "[${key}][${getQuantityText(value.size)}]"
            println(group)
            if (sb.isNotBlank()) {
                sb.append(breakLine)
            }
            sb.append(group)
            value.forEach { item ->
                println(item)
                sb.append(breakLine)
                sb.append(item)
            }
            println()
            sb.append(breakLine)
        }

        sb.append(breakLine)
        sb.append(breakLine)
        sb.insert(0, breakLine)
        sb.insert(0, breakLine)
        sb.insert(0, "Total de arquivos identificados: $totalFiles")

        FileWritter().writeJSON(filename = filenameOutput, content = sb.toString())
    }

    private fun getQuantityText(size: Int): String {
        return if (size >= 2) "$size encontrados"
        else "$size encontrado"
    }
}