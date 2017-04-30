----------------------------------------------------------------------------------------------------
# Introduction

A simple spring boot 'echo' micro-service to echo the supplied input back to the client.

This is derivative of the [this git repository](https://github.com/carnellj/spmia-chapter1)


----------------------------------------------------------------------------------------------------
# Software needed

1.	Apache Maven (http://apache.maven.org). 
2.	Docker (http://docker.com).
3.	Git Client (http://git-scm.com).


----------------------------------------------------------------------------------------------------
# Building

The build system creates a docker image built via maven using the [Spotify docker plugin](https://github.com/spotify/docker-maven-plugin). 

   **mvn clean package docker:build**


----------------------------------------------------------------------------------------------------
# Running

The docker image is run via docker compose

   **docker-compose -f docker/common/docker-compose.yml up**

