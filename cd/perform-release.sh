#!/usr/bin/env bash
#ssh-add
#ssh-add -l
mvn release:prepare

# travis encrypt OSSRH_USERNAME=xxx --add env.global
# travis encrypt OSSRH_PASSWORD=xxx --add env.global
# git push --delete origin 1.0.0.RELEASE
# git push --delete 1.0.0.RELEASE