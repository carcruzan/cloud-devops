def call(Map pipelineParams) {
    pipeline {
        agent {
            docker { image 'amazon/aws-sam-cli-build-image-provided' }
        }
        stages {
            stage('Checkout') {
                steps {
                    script {
                        def z = new com.devops.checkout()
                        z.checkOut(pipelineParams.scmUrl)
                    }
                }
            }
            stage('Deploy to PROD') {
                when {
                    branch 'refs/remotes/origin/main'
                    environment name: 'ENVIRONMENT_ID', value: 'prd'
                    environment name: 'DEFAULT_REGION', value: 'us-east-1'
                }
                steps {
                    script {
                        def x = new com.devops.samDeploy()
                        x.samDeploy()
                    }
                }
            }
            stage('Deploy to DEV') {
                when {
                    branch 'develop'
                    environment name: 'ENVIRONMENT_ID', value: 'dev'
                    environment name: 'DEFAULT_REGION', value: 'us-east-1'
                }
                steps {
                    script {
                        def x = new com.devops.samDeploy()
                        x.samDeploy()
                    }
                }
            }
        }
        post {
            always {
                cleanWs()
            }
        }
    }
}
