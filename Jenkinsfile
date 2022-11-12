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
                stage ('Scan Sonar'){
            steps {
    sh "mvn sonar:sonar \
          -Dsonar.projectKey=achat \
          -Dsonar.host.url=http://192.168.100.18:9000 \
          -Dsonar.login=53c30cc2409e64cecde7aaba9c90d0fde72bb823"
    }
        }
}
}