package com.devops

def checkOut(repo) {
  git branch: 'main', url: "${repo}"
}