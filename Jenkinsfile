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
                ls
			    sh '(cd ./SMB/; mvn clean package)'
                stash name: "app", includes: "**"
            }
        }
    }
}