def call(Map config) {
    pipeline {
        agent {
            docker { image 'amazon/aws-sam-cli-build-image-provided' }
        }

        parameters {
            string(description: 'Name Lambda Function', name: 'FunctionName')
        }
        stages {
            stage('Checkout') {
                steps {
                    script {
                        def z = new com.devops.checkout()
                        z.checkOut('https://github.com/carcruzan/IaC.git')
                    }
                }
            }
            stage('SAM Deploy'){
                steps {
                    script {
                        def x = new com.devops.samDeploy()
                        x.samDeploy("$FunctionName")
                    }
                }
            }
        }
    }
}
