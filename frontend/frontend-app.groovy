pipeline{
    agent any
    stages{
        stage('Code-Pull'){
            steps{
                git branch: 'main', url: 'https://github.com/jyotiparmar20/FRA-project.git'
            }
        }
        stage('Code-Build'){
            steps{
                sh '''
                    cd frontend
                    npm install
                    npm run build
                '''
            }
        }
        stage('Deploy'){
            steps{
                sh '''
                cd frontend
                aws s3 sync dist/ s3://flight-app-r-bkt/
                '''  
            }
        }
    }
}
