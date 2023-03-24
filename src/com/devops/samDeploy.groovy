package com.devops

def samDeploy() {
    def template = libraryResource 'com/devops/template.yaml'
    writeFile(file: 'template.yaml', text: template)
    
    sh '''

        SAM_PARAMETERS=$( cat config.json | jq -r '.[] | "\\(.ParameterKey)=\\(.ParameterValue)"')
        
        region=$(cat config.json | jq -r '.[] | select(.ParameterKey=="region")| .ParameterValue ')
        
        stack_name=$(cat config.json | jq -r '.[] | select(.ParameterKey=="stackName") | .ParameterValue ')

        sam build --use-container
        sam deploy -t final_template.yaml --stack-name=$stack_name --region=$region --no-fail-on-empty-changeset --capabilities CAPABILITY_IAM --parameter-overrides $SAM_PARAMETERS --tags Key=Name,Value=$stack_name

    '''
}
