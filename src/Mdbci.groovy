import groovy.json.JsonOutput
import hudson.FilePath

@NonCPS def entrySet(m) {m.collect {k, v -> [key: k, value: v]}}

@NonCPS
def createJSON(filename, array) {
    def json = JsonOutput.toJson(array)
    json = JsonOutput.prettyPrint(json)
    writeFile file: filename, text: json
}

def mdbciGenerate(template, name) {
    sh "./mdbci --template ${template} generate ${name}"
}

def mdbciUp(name) {
    return sh(returnStatus: true, script: "./mdbci up ${name}")
}

def mdbciDestroy(name) {
    sh "./mdbci destroy ${name}"
}

def logError(message) {
    echo "ERROR: ${message}"    
}

def logInfo(message) {
    echo "INFO: ${message}"    
}

return this