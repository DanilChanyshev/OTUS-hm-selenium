node('ui_runner') {

    currentBuild.description = "<p style='color: blue;'>UI-selenoid tests</p>"

    stage('Checkout') {
        checkout scm
    }

//    stage('Build selenoid-UI') {
//        dir("${env.WORKSPACE}") {
//            sh "ansible-playbook -i playbook/hosts playbook/selenoid_playbook.yaml"
//        }
//    }

    stage('Run UI tests') {
        dir("${env.WORKSPACE}") {
            catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                sh "ansible-playbook -i playbook/hosts playbook/ui_run_playbook.yaml -e \"workspace=\$(pwd)\""
            }
        }
    }

    stage('Publish results') {
        junit 'target/surefire-reports/*.xml'
    }

    stage('allure publish') {
        allure ([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'target/allure-results']]
        ])
    }

    stage('Archive allure results') {
        archiveArtifacts artifacts: 'target/allure-results/**',
                allowEmptyArchive: true,
                fingerprint: true
    }
}