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
            stage('SAM Deploy'){
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
