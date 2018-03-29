def generate(mdbciPath, template, name) {
    sh "${mdbciPath}/mdbci --template ${template} generate ${name}"
}

def up(mdbciPath, name) {
    return sh(returnStatus: true, script: "${mdbciPath}/mdbci up ${name}")
}

def destroy(mdbciPath, name) {
    sh "${mdbciPath}/mdbci destroy ${name}"
}

return this
