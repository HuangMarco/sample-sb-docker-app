FROM java:8-jdk-alpine

# Tell Docker to copy files from the local file system to a specific folder inside the build image
COPY ./target/demo-0.0.1-SNAPSHOT.jar /usr/app/

# Sets the working directory for any RUN, CMD, ENTRYPOINT, COPY and ADD instructions that follow in the Dockerfile. Now we switch to the path that WORKDIR defines
WORKDIR /usr/app

# This tells Docker to execute a shell command-line within the target system. Here we practically just "touch" our file so that it has its modification time updated 
#   (Docker creates all container files in an "unmodified" state by default)
RUN sh -c 'touch demo-0.0.1-SNAPSHOT.jar'


# Allows you to configure a container that will run as an executable. 
#  It's where you tell Docker how to run your application. We know we run our spring-boot app as java -jar <app-name>.jar, so we put it in an array.
ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]

# Summary about above content:
# https://stackabuse.com/dockerizing-a-spring-boot-application/
# 1. We need to involve the jar again and again in our Dockerfile
# 2. We need to manually open a terminal and run docker commands. It would be nice if we could make it a part of a Maven life-cycle
#    so that we can build images as a part of our CI/CD pipelines.

# Consider above, I will involve maven plugin to do the job:
# There are many maven plugins available that we can use in pom.xml. By this way maven plugin will internally creates the Dockerfile based on the configuration
#       in the pom.xml file and then uses the generated Dockerfile to build the image.

# We will use fabric8io/docker-maven-plugin

