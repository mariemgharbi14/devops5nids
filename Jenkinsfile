pipeline {
agent any

stages {
 

         stage('Cloning from GitHub') {
                steps {
                    git branch: 'heditrigui', url: 'https://github.com/mariemgharbi14/devops5nids.git'
                }  
            }
           
               stage('maven clean') {
                        steps {
                           sh 'mvn clean '
                        }
                    }
          stage('maven compile') {
            steps {
               sh 'mvn compile'
           }
        }

          stage('maven test / MOCKITO') {
            steps {
               sh 'mvn test'
            }
        }
          stage('maven verify') {
             steps {
               sh 'mvn verify'
          }
       }
       stage("docker build") {
                       steps{

                           sh 'docker build -t heditrigui/achat .'
                       }
               }
           stage("DockerHub login ") {
                       steps{
                           sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u heditrigui -p dockerpass'
                       }
               }
           stage("DockerHub push") {
                       steps{
                        sh 'docker push heditrigui/achat'
                   }
              }
       stage('Docker-compose file') {

                          steps {
                               sh 'docker-compose up -d';
                               sh 'sleep 300'
                               
                                 }  }
       stage ('sonar test '){
    steps {
       script {
           withSonarQubeEnv('sonarqube_token'){
               sh "mvn sonar:sonar"
           }
       }
    }
       }

           stage('deploy to Nexus') {
      steps {
        sh 'mvn clean deploy -Dmaven.test.skip=true'
      }
    }
    stage('get from Nexus') {
      steps {
        sh 'wget --user=admin --password=8425 http://192.168.1.13:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar'
      }
    }

        



}
   post { 
    success { 
        mail to: "hedi.trigui@esprit.tn", 
        subject: "Welcome to DevOps project Front-End : Pipeline Success", 
        body: "success on job ${env.JOB_NAME}, Build Number: ${env.BUILD_NUMBER}, Build URL: ${env.BUILD_URL}" } 
    failure { mail to: "hedi.trigui@esprit.tn", 
    subject: "Pipeline Failure", 
    body: "Welcome to DevOps project Front-End :Failure on job ${env.JOB_NAME}, Build Number: ${env.BUILD_NUMBER}, Build URL: ${env.BUILD_URL} " } 
}

}
