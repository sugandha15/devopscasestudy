FROM maven:3.5.2-jdk-8-alpine AS maven_build

COPY pom.xml /tmp/

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package

#pull base image

FROM openjdk:8-jdk-alpine

#maintainer
MAINTAINER sugandha203@gmail.com
#expose port 8083
EXPOSE 8083

#default command
CMD java -jar /home/devopscasestudy-docker-jenkins.war



COPY --from=maven_build /tmp/target/devopscasestudy-docker-jenkins.war /home/devopscasestudy-docker-jenkins.war
aven_build /tmp/target/devopscasestudy-docker-jenkins.war /home/devopscasestudy-docker-jenkins.war