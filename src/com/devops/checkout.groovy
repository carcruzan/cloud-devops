package com.devops

def checkOut(repo) {
  checkout([
      $class: 'GitSCM',
      branches: [[name: 'develop']],
      extensions: scm.extensions + [[$class: 'CleanBeforeCheckout'], [$class: 'WipeWorkspace']],
      userRemoteConfigs: [[url: "${repo}"]],
      doGenerateSubmoduleConfigurations: false
  ])
}