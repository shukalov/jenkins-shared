def buildInfo(String to='') {
    emailext (
        attachLog: true,
        subject: "Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}",
        body: """Job ${env.JOB_NAME} build ${env.BUILD_NUMBER}: ${currentBuild.currentResult}  \nMore info at: ${env.BUILD_URL}\nLog is in attachment""",
        to: to
    )
}