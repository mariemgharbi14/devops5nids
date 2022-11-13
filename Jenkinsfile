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
  -Dsonar.host.url=http://192.168.1.77:9000 \
  -Dsonar.login=1db77c392305de7546d7dd3fb7e148febc34b39b -DskipTests"
    }
        }
        stage('Nexus') {
      steps {
        sh 'mvn clean deploy -Dmaven.test.skip=true'
      }
    }
    stage('wget Nexus') {
      steps {
        sh 'wget --user=admin --password=nexus http://192.168.1.77:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar'
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