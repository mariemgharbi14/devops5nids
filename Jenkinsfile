pipeline {
    agent any

    stages {
        stage('get form GIT ') {
            steps {
                echo 'Getting project form GIT...'
                git branch: 'main' ,
                url : 'https://github.com/mariemgharbi14/devops5nids.git' ,
                credentialsId: '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' ;
            }
        }
    }
}