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
	
	
	stage("Docker Image") {
                       steps{

                           sh 'docker build -t khalil/achat .'
                       }
               }
           stage("DockerHub Login") {
                       steps{
                           sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u KhalilAzizi -p azerty12345'
                       }
               }
           stage("DockerHub Push") {
                       steps{
                        sh 'docker push khalil/achat'
                   }
              }
			  
			  
}
   post { 
    success { mail to: "khalil.azizi@esprit.tn", subject: "Succes Notification", body: "Job done ${env.JOB_NAME}, \n Build Number: ${env.BUILD_NUMBER}, \n Build URL: ${env.BUILD_URL}" }
		
    failure { mail to: "khalil.azizi@esprit.tn", subject: "Pipeline Failure", body: "Failure on job ${env.JOB_NAME}, \n Build Number: ${env.BUILD_NUMBER}, \n Build URL: ${env.BUILD_URL} " } 
}

}