pipeline {

    agent {
        node {
            label 'master'
        }
    }

    options {
        buildDiscarder logRotator(
                    daysToKeepStr: '16',
                    numToKeepStr: '10'
            )
    }

    stages {
        
        stage('Cleanup Workspace') {
            steps {
                cleanWs()
                sh """
                echo "Cleaned Up Workspace For Project"
                """
            }
        }

        stage('Code Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/main']],
                    userRemoteConfigs: [[url: 'https://github.com/HuangMarco/sample-sb-docker-app.git']]
                ])
            }
        }

        stage('Unit Testing') {
            steps {
                sh """
                echo "Running Unit Tests"
                """
            }
        }

        stage('Code Analysis') {
            steps {
                sh """
                echo "Running Code Analysis"
                """
            }
        }

        stage('Build code and build the docker image') {
            when {
                branch 'main'
            }
            steps {
                sh """
                echo "Doing Maven build and execute docker plugin"
                cd /var/jenkins_home/workspace/Build_sample-sb-docker-app_main
                mvn clean install -Ddocker
                """

                sh """
                echo "Deploying Code"
                """
            }
        }

    }  
}