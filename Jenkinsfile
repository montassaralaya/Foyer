pipeline {
    agent any
    stages{
     stage('build') {
        steps{
          dir('Foyer'){
            sh 'mvn clean package'
          }
        }
     }
    }

}