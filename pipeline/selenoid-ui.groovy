node('android') {

    currentBuild.description = "<p style='color: blue;'>Selenoid UI</p>"

    stage('Checkout') {
        checkout scm
    }

    stage('Start selenoid') {
        dir("${env.WORKSPACE}") {
            catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                sh '''
                ansible-playbook -i playbook/hosts playbook/selenoid_playbook.yaml
                sleep 60
            '''
            }
        }
    }
}