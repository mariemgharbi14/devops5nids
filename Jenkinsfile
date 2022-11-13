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

       stage ('MVN SONARQUBE '){
            steps {
                
                    
                    sh "mvn sonar:sonar"
                    
                
            }
        }


    }
}