package com.devops

def samDeploy() {
    def template = libraryResource 'com/devops/template.yaml'
    writeFile file: "template.yaml" , text: template

    def config = readJSON file: "config.json"

    sh '''
        chmod a+x template.yaml
        SAM_PARAMETERS=$( cat config.json)
        sam deploy -t template.yaml --stack-name=${config[].ParameterKey == 'stack-name'} --region=us-east-1 --no-fail-on-empty-changeset --capabilities CAPABILITY_IAM --parameter-overrides $SAM_PARAMETERS
    '''
}