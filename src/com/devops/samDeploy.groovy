package com.devops

def samDeploy() {
    def template = libraryResource 'com/devops/template.yaml'
    writeFile(file: 'template.yaml', text: template)

    sh '''
        sed -i 's/${ENVIRONMENT_ID}/${env.ENVIRONMENT_ID}/gI' config.json
        cat config.json
    '''
    // sh '''

    //     PathCodeLambda=$(cat config.json | jq -r '.properties[] | .PathCodeLambda')
    //     ZipName=$(cat config.json | jq -r '.deploy[] | select(.ParameterKey=="ZipName")| .ParameterValue ')
    //     Bucket=$(cat config.json | jq -r '.deploy[] | select(.ParameterKey=="Bucket")| .ParameterValue ')
    //     SAM_PARAMETERS=$( cat config.json | jq -r '.deploy[] | "\\(.ParameterKey)=\\(.ParameterValue)"')
    //     region=$(cat config.json | jq -r '.deploy[] | select(.ParameterKey=="region")| .ParameterValue ')
    //     stack_name=$(cat config.json | jq -r '.deploy[] | select(.ParameterKey=="stackName") | .ParameterValue ')

    //     cd $PathCodeLambda

    //     zip -r $ZipName *
    //     aws s3 cp $ZipName s3://$Bucket

    //     cd ..

    //     sam deploy -t template.yaml --stack-name=$stack_name --region=${env.DEFAULT_REGION} --no-fail-on-empty-changeset --capabilities CAPABILITY_IAM --parameter-overrides $SAM_PARAMETERS

    // '''
}
