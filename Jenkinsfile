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


    }
}