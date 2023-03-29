package com.devops

def checkOut(repo) {
  // git branch: 'main', url: "${repo}"
  checkout([
      $class: 'GitSCM',
      branches: 'develop',
      extensions: scm.extensions + [[$class: 'LocalBranch'], [$class: 'WipeWorkspace']],
      userRemoteConfigs: [[url: '${repo}']],
      doGenerateSubmoduleConfigurations: false
  ])
}