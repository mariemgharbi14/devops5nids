pipeline {
    agent any

    stages {
         stage('Cloning from GitHub') {
                steps {
                    git branch: 'marouen', url: 'https://github.com/mariemgharbi14/devops5nids.git'
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
  -Dsonar.projectKey=sonar2 \
  -Dsonar.host.url=http://192.168.1.183:9000 \
  -Dsonar.login=1db77c392305de7546d7dd3fb7e148febc34b39b -DskipTests"
    }
        }
    }
    
    post {
                success {
                   echo 'succes'
                }
failure {
                  echo 'failed'   
                }
             
       
    }

}