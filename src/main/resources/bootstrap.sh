#!/bin/sh

JAVA_OPTS="-Dloader.path=/external/config -Djava.security.egd=file:/dev/ ./urandom -XX:+UseContainerSupport "
JAVA_VM_ARGS="-jar /path/to/springboot/app/myapp.jar --server.port=8888"
JAVA_BIN="$JAVA_HOME/bin/java

COMMAND="$JAVA_BIN $JAVA_OPTS $JAVA_VM_ARGS"

$COMMAND