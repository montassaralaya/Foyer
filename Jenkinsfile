pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                dir('Foyer') {
                    sh 'mvn clean package'
                }
            }
        }
    }
}
