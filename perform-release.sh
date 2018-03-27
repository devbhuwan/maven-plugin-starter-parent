#!/usr/bin/env bash
#ssh-add
#ssh-add -l
mvn release:prepare

#travis encrypt OSSRH_USERNAME=xxx --add env.global
#travis encrypt OSSRH_PASSWORD=xxx --add env.global