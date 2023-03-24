package com.devops

def samDeploy(FunctionName) {
  sh '''
    sam deploy -t final_template.yaml --stack-name=test-IaC --region=us-east-1 --no-fail-on-empty-changeset --capabilities CAPABILITY_IAM --parameter-overrides "ParameterKey=FunctionName,ParameterValue=${FunctionName}"
    '''
}