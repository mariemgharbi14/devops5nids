pipeline {
agent any

stages {
         stage('GitHub Clone') {
                steps {
                    git branch: 'khalil', url: 'https://github.com/mariemgharbi14/devops5nids.git'
                }  
            }
           
		   stage('Docker compose') {
            steps {
            sh 'docker-compose up -d';
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
			

    stage ('Scan Sonar'){
    steps {
    sh "mvn sonar:sonar \
          -Dsonar.projectKey=achat \
          -Dsonar.host.url=http://192.168.1.192:9000 \
          -Dsonar.login=c86cb074981bf9966a4b67d72a3fdc5d5c98b89e"
    }
    }
	 stage('Nexus Deploy') {
      steps {
        sh 'mvn clean deploy -Dmaven.test.skip=true'
      }
    }
	
	
	stage("Docker Image") {
                       steps{

                           sh 'docker build -t khalilazizi/achat .'
                       }
               }
           stage("DockerHub Login") {
                       steps{
                           sh 'echo "Login to dockerhub in progress" | docker login -u khalilazizi -p azerty12345'
                       }
               }
           stage("DockerHub Push") {
                       steps{
                        sh 'docker push khalilazizi/achat'
                   }
              }
			  
			  
}
   post { 
    success { mail to: "khalil.azizi@esprit.tn", subject: "Succes Notification", body: " Job done ${env.JOB_NAME}, \n Build Number: ${env.BUILD_NUMBER}, \n Build URL: ${env.BUILD_URL}" }
		
    failure { mail to: "khalil.azizi@esprit.tn", subject: "Pipeline Failure", body: " Failure on job ${env.JOB_NAME}, \n Build Number: ${env.BUILD_NUMBER}, \n Build URL: ${env.BUILD_URL} " } 
}
}