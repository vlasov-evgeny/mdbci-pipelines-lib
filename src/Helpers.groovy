import groovy.json.JsonOutput

@NonCPS def entrySet(m) {m.collect {k, v -> [key: k, value: v]}}

@NonCPS
def createJSON(filename, array) {
    def json = JsonOutput.toJson(array)
    json = JsonOutput.prettyPrint(json)
    writeFile file: filename, text: json
}

def logError(message) {
    echo "ERROR: ${message}"    
}

def logInfo(message) {
    echo "INFO: ${message}"    
}

return this