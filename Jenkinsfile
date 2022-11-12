pipeline {
agent any

stages {
         stage('GitHub Clone') {
                steps {
                    git branch: 'khalil', url: 'https://github.com/mariemgharbi14/devops5nids.git'
                }  
            }
           
               stage('Maven Clean') {
                        steps {
                           sh 'mvn clean '
                        }
                    }
          stage('Maven Compile') {
            steps {
               sh 'mvn compile'
           }
        }

          stage('Maven Test') {
            steps {
               sh 'mvn test'
            }
        }
          stage('Maven Verify') {
             steps {
               sh 'mvn verify'
          }
       }
	   		
			stage('Docker compose') {
            steps {
            sh 'docker-compose up -d';
			sh 'sleep 120'
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