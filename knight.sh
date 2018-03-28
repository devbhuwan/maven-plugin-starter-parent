#!/usr/bin/env bash

function build {
    mvn clean install -DskipTests
    return 0;
}

function prepareDocumentation {
    mvn package -P docs-html -DskipTests
    return 0
}
#build
prepareDocumentation