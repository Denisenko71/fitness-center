pipeline {
    agent any

    tools {
        maven 'Maven-3.10'
        jdk 'JDK-21'
    }

    stages {
        stage('Build') {
            steps {
                bat 'mvn compile'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }
    }
}