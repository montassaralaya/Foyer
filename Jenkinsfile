pipeline {
    agent any
    environment {
        MAVEN_HOME = '/opt/maven'  // Replace with your actual Maven path
        PATH = "${env.PATH}:${MAVEN_HOME}/bin"  // Ensure Maven is in PATH
    }
    stages {
        stage('Check Maven Path') {
            steps {
                script {
                    // Check if Maven is installed and accessible
                    sh 'mvn -v || echo "Maven not found!"'
                }
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('JUnit Test') {
            steps {
                sh 'mvn clean test'

            }
        }
        stage('Nexus Deployment') {
                    steps {
                            script {
                                sh 'mvn deploy'
                        }
                    }
                }
        stage('Build Image') {
                    steps {
                            script {
                                sh 'docker-compose build'
                        }
                    }
                }

         
    }
}
