import model.setup.template.Header
import tools.FileWritter
import tools.JSONBeautify
import tools.ResultMatcher
import tools.codeowners.CodeOwnerJSONFile
import tools.codeowners.CodeOwnerJsonCreator
import tools.intellij.SearchResultsFromIntelliJIdea
import tools.setup.SetupJSONFile

fun main() {
    val setup = SetupJSONFile().getObject()!!

    sayMyName(
        pathname = setup.input.codeownerPath,
        monolithPackage = setup.packageDetails.relative,
        header = setup.template.header,
        codeownerJsonFilename = setup.output.codeowner.filename
    )

    readSearchResultsFromIntelliJIdea(
        targetPackage = setup.packageDetails.full,
        codeownerJsonFilename = setup.output.codeowner.filename,
        filenameOutput = setup.output.results.filename
    )
}

private fun sayMyName(
    pathname: String,
    monolithPackage: String,
    header: Header,
    codeownerJsonFilename: String
) {
    val creator = CodeOwnerJsonCreator(pathname, header)
    val beautify = JSONBeautify()
    val writter = FileWritter()

    val json = creator.generateJSON(monolithPackage)
    val jsonBeautified = beautify.beautifyJSON(json, true)
    writter.writeJSON(filename = codeownerJsonFilename, content = jsonBeautified)
}

fun readSearchResultsFromIntelliJIdea(targetPackage: String, codeownerJsonFilename: String, filenameOutput: String) {
    val lines = SearchResultsFromIntelliJIdea(targetPackage).getLinesFormatted()
    val wrapper = CodeOwnerJSONFile().getWrapper(codeownerJsonFilename)
    if (wrapper != null) {
        val resultMatcher = ResultMatcher(targetPackage, wrapper, filenameOutput)
        resultMatcher.matchWith(lines).print()
    }
}
