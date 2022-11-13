pipeline {
    agent any

    stages {
        stage('get form GIT ') {
            steps {
                echo 'Getting project form GIT...'
                git branch: 'medali' ,
                url : 'https://github.com/mariemgharbi14/devops5nids.git' ,
                credentialsId: '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' ;
            }
        }

        stage('MVN CLEAN') {
            steps {
                     sh 'mvn clean ';
                }
            }
              
        stage('MVN COMPILE') {
            steps {
               sh 'mvn compile';
           }
       }

           stage('JUNIT') {
             steps {
               sh 'mvn test'
          }
       }
       
        stage('MVN VERIFY') {
             steps {
               sh 'mvn verify'
          }
       }

       stage ('SONAR check'){
            steps {
                sh "mvn sonar:sonar \
                -Dsonar.projectKey=sonar2 \
                -Dsonar.host.url=http://192.168.33.10:9000 \
                -Dsonar.login=69a2256b9b2708fe2e66cf8e7e4da130e3ad2228 -DskipTests"
                    }
             }

        stage('NEXUS check') {
            steps {
                sh 'mvn clean deploy -Dmaven.test.skip=true'
            }
        }

        post { 
            success { mail to: "mohamedali.saguertrabelsi@esprit.tn", subject: "Pipeline executed succesfully", body: " All stages of the pipeline have finished running successfully ${env.JOB_NAME}, \n Build Number: ${env.BUILD_NUMBER}, \n Build URL: ${env.BUILD_URL}" }
        
            failure { mail to: "mohamedali.saguertrabelsi@esprit.tn", subject: "Pipeline Failed to execute correctly", body: " One or more stages of the pipeline failed to run successfully and returned some errors ${env.JOB_NAME}, \n Build Number: ${env.BUILD_NUMBER}, \n Build URL: ${env.BUILD_URL} " } 
        }


    }
}
