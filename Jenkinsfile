ansibleDeploy(){
    sh 'ANSIBLE_HOST_CHECKING_KEY=false ansible-playbook --timeout 60 -i host.ini ansible/playbook.yml'

}


pipeline {
    
    environment {
        APP_NAME='bankapp project'
        IMG_TAG='bankapp'
        CONTAINER_PORT='5000'
        HOST_PORT='6666'
        APP_LOGS='usr/logs'
    }

    agent any


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
        stage("test and build"){
            steps {
                script{
                    
                    sh "cd bankapp_project_backend && chmod +x mvnw && ./mvnw -B clean package -Dmaven.test.skip=true --file pom.xml"
                   
                    
                }
            }
        }
        stage("Sonar Analysis"){
            steps{
                withSonarQubeEnv('sonarqube-8.9.10'){
                    sh "cd bankapp_project_backend && mvn sonar:sonar"
                }
            }
        
        
        
        }
        stage("Build image"){
            steps{
                script{
                    sh "cd bankapp_project_backend && docker build -t mlarry/backend_app:1.0 ."
                    
                }
            }

        }
        stage("Push image"){
            steps{
                script{
                    sh "docker login -u ${env.user} -p ${env.passwd}"
                    sh "docker push mlarry/backend_app"

                }
            }
       }
       stage("Deploy"){
            steps {
                sh ''' ${ansibleDeploy()} '''        
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
