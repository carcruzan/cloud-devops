package com.devops

def samDeploy(PathCodeLambda) {
    def template = libraryResource 'com/devops/template.yaml'
    writeFile(file: 'template.yaml', text: template)
    
    sh '''

        ZipName=$(cat config.json | jq -r '.[] | select(.ParameterKey=="ZipName")| .ParameterValue ')
        Bucket=$(cat config.json | jq -r '.[] | select(.ParameterKey=="Bucket")| .ParameterValue ')
        SAM_PARAMETERS=$( cat config.json | jq -r '.[] | "\\(.ParameterKey)=\\(.ParameterValue)"')
        region=$(cat config.json | jq -r '.[] | select(.ParameterKey=="region")| .ParameterValue ')
        stack_name=$(cat config.json | jq -r '.[] | select(.ParameterKey=="stackName") | .ParameterValue ')

        cd $PathCodeLambda

        zip -r $ZipName *
        aws s3 cp $ZipName s3://$Bucket

        cd ..

        sam deploy -t template.yaml --stack-name=$stack_name --region=$region --no-fail-on-empty-changeset --capabilities CAPABILITY_IAM --parameter-overrides $SAM_PARAMETERS

    '''
}
