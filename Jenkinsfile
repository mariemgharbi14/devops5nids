pipeline {
agent any

stages {
 

         stage('Cloning from GitHub') {
                steps {
                    git branch: 'heditrigui', url: 'https://github.com/mariemgharbi14/devops5nids.git'
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
       stage("Building Docker Image") {
                       steps{

                           sh 'docker build -t heditrigui/achat .'
                       }
               }
           stage("Login to DockerHub") {
                       steps{
                           sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u heditrigui -p dockerpass'
                       }
               }
           stage("Push to DockerHub") {
                       steps{
                        sh 'docker push heditrigui/achat'
                   }
              }
       stage('Docker compose') {

                          steps {
                               sh 'docker-compose up -d';
                               sh 'sleep 300'
                               
                                 }  }
       stage ('sonar '){
    steps {
       script {
           withSonarQubeEnv('sonarqube_token'){
               sh "mvn sonar:sonar"
           }
       }
    }
       }

           stage('deploy to Nexus') {
      steps {
        sh 'mvn clean deploy -Dmaven.test.skip=true'
      }
    }
    stage('get from Nexus') {
      steps {
        sh 'wget --user=admin --password=8425 http://192.168.1.13:8081/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar'
      }
    }
      stage('git secret check'){
        steps{
      script{
        echo 'running trufflehog to check project history for secrets'
        sh 'trufflehog --regex --entropy=False --max_depth=3 https://github.com/pawnu/secDevLabs'
      }
        }
      }
      stage('SCA'){
        steps{
          echo 'running python safety check on requirements.txt file'
          sh 'safety check -r $WORKSPACE/owasp-top10-2017-apps/a7/gossip-world/app/requirements.txt'
          /*
      echo 'running liccheck on dependencies'
      sh """
              virtualenv --no-site-packages .
              source bin/activate
          pip install -r $WORKSPACE/owasp-top10-2017-apps/a7/gossip-world/app/requirements.txt
              liccheck -s ~/my_strategy.ini -r $WORKSPACE/owasp-top10-2017-apps/a7/gossip-world/app/requirements.txt
              deactivate
            """
        */
        }
      }  
      stage('SAST') {
          steps {
              echo 'Testing source code for security bugs and vulnerabilities'
          sh 'bandit -r $WORKSPACE/owasp-top10-2017-apps/a7/gossip-world/app/ -ll || true'
          }
      }
      stage('Container audit') {
          steps {
              echo 'Audit the dockerfile used to spin up the web application'
        script{             
            def exists = fileExists '/var/jenkins_home/lynis/lynis'
            if(exists){
                echo 'lynis already exists'
            }else{
                  sh """
                  wget https://downloads.cisofy.com/lynis/lynis-2.7.5.tar.gz
                  tar xfvz lynis-2.7.5.tar.gz -C ~/
                  rm lynis-2.7.5.tar.gz
                  """
            }
        }
          dir("/var/jenkins_home/lynis"){  
            sh """
            mkdir $WORKSPACE/$BUILD_TAG/
            ./lynis audit dockerfile $WORKSPACE/owasp-top10-2017-apps/a7/gossip-world/deployments/Dockerfile | ansi2html > $WORKSPACE/$BUILD_TAG/docker-report.html
            mv /tmp/lynis.log $WORKSPACE/$BUILD_TAG/docker_lynis.log
            mv /tmp/lynis-report.dat $WORKSPACE/$BUILD_TAG/docker_lynis-report.dat
            """
          }
          }
      }     



}
   post { 
    success { 
        mail to: "hedi.trigui@esprit.tn", 
        subject: "Welcome to DevOps project Front-End : Pipeline Success", 
        body: "success on job ${env.JOB_NAME}, Build Number: ${env.BUILD_NUMBER}, Build URL: ${env.BUILD_URL}" } 
    failure { mail to: "hedi.trigui@esprit.tn", 
    subject: "Pipeline Failure", 
    body: "Welcome to DevOps project Front-End :Failure on job ${env.JOB_NAME}, Build Number: ${env.BUILD_NUMBER}, Build URL: ${env.BUILD_URL} " } 
}

}
