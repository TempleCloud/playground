#!/bin/sh
echo "********************************************************"
echo "Starting echo-service "
echo "********************************************************"
java -jar /usr/local/echo-service/@project.build.finalName@.jar
