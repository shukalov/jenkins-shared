def clone(String repo, String dirname='.') {

    dir(dirname) {
            git(
                url: repo,
            )
    }
}


def commitPwd(Map args, String message) {
    defaultMap = [dirname:'.', repo:'',credentialsId:'']

    args = (defaultMap << args)
    echo "Commit dir '${args.dirname}' with message '${message}' to '${args.repo}' with credentialsId '${args.credentialsId}''"

    dir(args.dirname) {
            sh "git commit -am ${message}"
            if (args.credentialsId?.trim() && args.repo?.trim()) {
                withCredentials([usernamePassword(credentialsId: args.credentialsId, passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
                    args.repo = "${args.repo}".replaceFirst('(https*://)', "\$1${GIT_USERNAME}:${GIT_PASSWORD}@")
                    sh "git push ${args.repo}"
                }
            }
    }
}
