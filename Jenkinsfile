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
			}  
			}
			
			stage('Wait 120s') {
            steps {
			sh 'sleep 120'
			}  
			}

			
    stage ('Scan Sonar'){
    steps {
    sh "mvn sonar:sonar \
          -Dsonar.projectKey=achat \
          -Dsonar.host.url=http://192.168.100.18:9000 \
          -Dsonar.login=079989e92ab515c2205a9579e1f64a72ec1e3ebe"
    }
    }
	 stage('Nexus Deploy') {
      steps {
        sh 'mvn clean deploy -Dmaven.test.skip=true'
      }
    }
	
}
}