pipeline {
    
    agent any
    
      environment {
        PATH = "$PATH:/usr/share/maven"
    }

    stages {
        stage('Initial_cleanup'){
            steps {

                dir ("${WORKSPACE}"){
                    deleteDir()
                }
                
            }
        }
        stage('checkout'){
            steps {

                script {
                    sh "pwd && ls"
                    sh "git clone https://github.com/madeniji017/bankapp_project_backend.git"
                }
                
            }
        }
        stage("Build"){
            steps {
                script{
                    
                    sh "cd bankapp_project_backend && chmod +x mvnw && ./mvnw -B clean package -Dmaven.test.skip=true --file pom.xml"
                   
                    
                }
            }
        }
        stage("Sonar Analysis"){
            steps{
                withSonarQubeEnv('sonarqube-8.9.10.61524'){
                    sh "mvn sonar:sonar"
                }
            }
        }
        stage("Build image"){
            steps{
                script{
                    sh "cd bankapp_project_backend && docker build -t mlarry/backend_app:2.0 ."
                    
                }
            }

        }
        stage("Push image"){
            steps{
                script{
                    sh "docker login -u ${env.user} -p ${env.passwd}"
                    sh "docker push mlarry/backend_app:2.0"
                }
            }

        }
         stage("Docker logout"){
            steps{
                script{
                    sh "docker logout"
                }
            }

        }

    }
}
