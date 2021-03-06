pipeline {
    agent any
    stages {
        stage('Build') {
             agent {
              docker {
               image 'maven:3-alpine'
              }
            }
            steps {
			    sh '(mvn clean package)'
			    stash name: "app", includes: "**"
            }
        }
        stage('QualityTest') {
             agent {
              docker {
               image 'maven:3-alpine'
              }
            }
            steps {
                unstash "app"
			    sh '(mvn clean test)'
			    sh '(mvn sonar:sonar -Dsonar.projectKey=qdl-smb_SMB -Dsonar.organization=qdl -Dsonar.host.url=https://sonarcloud.io -Dsonar.login=04f5e2082e159d655d42843f6d903606d34e37a3)'
            }
        }
        stage('IntegrationTest') {
             agent {
              docker {
               image 'lucienmoor/katalon-for-jenkins:latest'
               args '-p 9999:9090'
              }
            }
            steps {
                unstash "app"
                sh 'java -jar target/SMF-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &'
                sh 'sleep 30'
			    sh 'chmod +x ./runTest.sh'
			    sh './runTest.sh'
			    cleanWs()
            }
            post {
                always {
                    echo 'always clean up'
                    deleteDir()
                }
            }
        }
    }
}