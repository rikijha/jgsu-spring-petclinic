pipeline {
    agent any


    stages {
        stage('Checkout'){
            steps {
                 git url: 'https://github.com/rikijha/jgsu-spring-petclinic.git', branch: 'main'
            }
        }
        
        stage('Build') {
            steps {
                bat "./mvnw.cmd clean package"
            }
            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
