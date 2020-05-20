FROM openjdk:8
ADD target/devopscasestudy-docker.war devopscasestudy-docker.war
EXPOSE 8083
ENTRYPOINT ["java","-jar","devopscasestudy-docker.war"]