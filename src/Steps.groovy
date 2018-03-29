def checkBoxes(mdbciPath, boxes) {
    def helpers = new Helpers()
    def mdbci = new Mdbci()

    def boxesResults = [:]

    for (def box in helpers.entrySet(boxes)) {
        print "${box.key}"
        def boxTemplate = [
            node: [
                hostname: 'mdbcinode',
                box: "${box.key}"
            ]
        ]
        
        helpers.createJSON(
            "${mdbciPath}/vms/${box.key}.json",
            boxTemplate
        )
        mdbci.generate(
            mdbciPath,
            "${mdbciPath}/vms/${box.key}.json",
            "${mdbciPath}/vms/${box.key}"
        )
        def status = mdbci.up(mdbciPath, "${mdbciPath}/vms/${box.key}")
        
        if (status == 0) {            
            boxesResults[box.key] = "success"
        } else {
            boxesResults[box.key] = "error"
        }
        
        mdbci.destroy(mdbciPath, "${mdbciPath}/vms/${box.key}")
    }

    return boxesResults
}

return this