def call(Map config) {
    def z = new com.devops.checkout()
    def x = new com.devops.samDeploy()
    pipeline {
        agent {
            docker { image 'amazon/aws-sam-cli-build-image-provided' }
        }
        stages {
            stage('Checkout') {
                steps {
                    z.checkOut('https://github.com/carcruzan/IaC.git')
                }
            }
            stage('SAM Deploy'){
                steps {
                    x.samDeploy('${config.name}')
                }
            }
        }
    }
}