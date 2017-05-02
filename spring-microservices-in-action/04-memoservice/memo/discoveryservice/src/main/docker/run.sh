#!/bin/sh
echo "********************************************************"
echo "Starting the Discovery Service"
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -jar /usr/local/discovery-service/@project.build.finalName@.jar