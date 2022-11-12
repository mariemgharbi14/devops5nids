pipeline {
agent any

stages {
 

         stage('Cloning from GitHub') {
                steps {
                    git branch: 'khalil', url: 'https://github.com/mariemgharbi14/devops5nids.git'
                }  
            }
           
               stage('MVN CLEAN') {
                        steps {
                           sh 'mvn clean '
                        }
                    }
          stage('MVN COMPILE') {
            steps {
               sh 'mvn compile'
           }
        }

          stage('mvn Test') {
            steps {
               sh 'mvn test'
            }
        }
          stage('mvn Verify') {
             steps {
               sh 'mvn verify'
          }
       }
       stage ('sonar '){
    steps {
       script {
           withSonarQubeEnv('sonarqube_token'){
               sh "mvn sonar:sonar"
           }
       }
    }
       }
}
}