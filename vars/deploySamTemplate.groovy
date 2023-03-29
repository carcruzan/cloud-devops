def call(Map pipelineParams) {
    pipeline {
        agent {
            docker { image 'amazon/aws-sam-cli-build-image-provided' }
        }
        options {
            skipDefaultCheckout(true)
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
                }
                steps {
                    script {
                        env.ENVIRONMENT_ID = 'prd'
                        env.DEFAULT_REGION = 'us-east-1'
                        def x = new com.devops.samDeploy()
                        x.samDeploy()
                    }
                }
            }
            stage('Deploy to DEV') {
                when {
                    branch 'origin/develop'
                }
                steps {
                    script {
                        env.ENVIRONMENT_ID = 'dev'
                        env.DEFAULT_REGION = 'us-east-1'
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
