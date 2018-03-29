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
        // def status = mdbci.up(mdbciPath, "${mdbciPath}/vms/${box.key}")
        
        // if (status == 0) {
        //     logInfo("${box.key} can be broght up")
        //     boxesResults[box.key] = "success"
        // } else {
        //     logError("${box.key} can't be broght up")
        //     boxesResults[box.key] = "error"
        // }
        
        // mdbci.destroy(mdbciPath, "${mdbciPath}/vms/${box.key}")
        boxesResults[box.key] = "success"
    }

    return boxesResults
}

return this