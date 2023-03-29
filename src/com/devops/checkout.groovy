package com.devops

def checkOut(repo) {
  checkout([
      $class: 'GitSCM',
      extensions: scm.extensions + [[$class: 'CleanBeforeCheckout'], [$class: 'WipeWorkspace']],
      userRemoteConfigs: [[url: "${repo}"]],
      doGenerateSubmoduleConfigurations: false
  ])
}