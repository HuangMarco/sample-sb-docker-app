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

        stage('Initialize the container'){
            steps{
                sh """
                echo "Start to install maven, switch to /tmp directory"
                cd /tmp
                if ! command -v mvn &> /dev/null
                then
                    echo "mvn command could not be found and will install maven"
                    curl -X GET https://mirrors.gigenet.com/apache/maven/maven-3/3.8.1/binaries/apache-maven-3.8.1-bin.tar.gz -o apache-maven-3.8.1-bin.tar.gz
                    tar -xzvf apache-maven-3.8.1-bin.tar.gz
                    export PATH=/tmp/apache-maven-3.8.1/bin:$PATH
                    mvn -v
                    echo "Maven installation completed."
                    exit
                fi
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
                echo "Start to install maven, switch to /tmp directory"
                cd /tmp
                if ! command -v mvn &> /dev/null
                then
                    echo "mvn command could not be found and will install maven"
                    curl -X GET https://mirrors.gigenet.com/apache/maven/maven-3/3.8.1/binaries/apache-maven-3.8.1-bin.tar.gz -o apache-maven-3.8.1-bin.tar.gz
                    tar -xzvf apache-maven-3.8.1-bin.tar.gz
                    export PATH=/tmp/apache-maven-3.8.1/bin:$PATH
                    mvn -v
                    echo "Maven installation completed."
                    exit
                fi

                echo "Doing Maven build and execute docker plugin"
                cd /var/jenkins_home/workspace/Build_sample-sb-docker-app_main/
                mvn clean install -Ddocker
                """

                sh """
                echo "Deploying Code"
                """
            }
        }

    }  
}