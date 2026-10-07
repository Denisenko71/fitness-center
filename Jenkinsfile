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
        stage('Project Information') {
            steps {
                echo '=== Информация о Maven-проекте ==='
                bat 'mvn help:evaluate -Dexpression=project.groupId -q -DforceStdout'
                bat 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout'
                bat 'mvn help:evaluate -Dexpression=maven.compiler.source -q -DforceStdout'
            }
        }
        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }
    }
}