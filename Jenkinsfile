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
                    sh 'docker compose build'
                    sh 'docker images'
                }
            }
        }
        stage('Tag Image') {
            steps {
                script {
                    // Tag the application image for Docker Hub
                    sh 'docker tag mponta/foyer:1.0.0 mponta/foyer:latest'  // Tag as latest
                }
            }
        }
        stage('Push to Docker Hub') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: '36352b67-8f51-4554-ad97-c5a5855875af', passwordVariable: 'DOCKER_PASSWORD', usernameVariable: 'DOCKER_USERNAME')]) {
                        // Log in to Docker Hub
                        sh "echo \$DOCKER_PASSWORD | docker login -u \$DOCKER_USERNAME --password-stdin"

                        // Push the image to Docker Hub
                        sh 'docker push mponta/foyer:latest'  // Push the latest tag
                    }
                }
            }
        }
        stage('Deploy with Docker Compose') {
            steps {
                script {
                    // Use Docker Compose to start the services
                    sh 'docker compose up -d'  // Use -d to run in detached mode
                }
            }
        }
    }
}
