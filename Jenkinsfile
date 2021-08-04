pipeline {

    agent {
        node {
            label 'master'
        }
    }

    options {
        buildDiscarder logRotator(
                    daysToKeepStr: '30',
                    numToKeepStr: '20'
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

        // stage('Initialize the container'){
            // steps{
            //     sh """
            //     echo "Start to install maven, switch to /tmp directory"
            //     cd /tmp
            //     if ! command -v mvn &> /dev/null
            //     then
            //         echo "mvn command could not be found and will install maven"
            //         curl -X GET https://mirrors.gigenet.com/apache/maven/maven-3/3.8.1/binaries/apache-maven-3.8.1-bin.tar.gz -o apache-maven-3.8.1-bin.tar.gz
            //         tar -xzvf apache-maven-3.8.1-bin.tar.gz
            //         export PATH=/tmp/apache-maven-3.8.1/bin:$PATH
            //         mvn -v
            //         echo "Maven installation completed."
            //     fi
            //     """
            // }
        // }

        stage('Code Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: 'main']],
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

                fi

                echo "Doing Maven build and execute docker plugin"
                cd /var/jenkins_home/workspace/Build_sample-sb-docker-app_main/
                mvn clean install -Ddocker

                echo "Start to tag the image and upload the image"
                docker tag demo:latest anbclub/cicdsbk8s:latest
                docker image ls
                docker push anbclub/cicdsbk8s:v1
                """

                sh """
                echo "Install helm3"
                curl -X GET "https://get.helm.sh/helm-v3.6.2-linux-amd64.tar.gz" -o helm-v3.6.3.tar.gz
                tar -xzvf helm-v3.6.2-linux-amd64.tar.gz
                mv linux-amd64/helm /usr/local/bin/helm

                helm version
                echo "Installation of helm3 completed..."
                
                echo "Start to install kubectl"
                curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl"
                sudo install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl

                kubectl version --client
                echo "Installation of kubectl completed..."

                echo "Configure the target k8s cluster information..."
                echo "Configure the target k8s cluster completed via vault"
                echo "Check k8s namespace"
                echo "Check the helm chart is in target k8s namespace"
                echo "Uninstall helm chart"
                echo "Install helm chart"

                
                
                """
            }
        }

    }  
}