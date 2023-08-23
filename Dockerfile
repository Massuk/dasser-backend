FROM openjdk:19
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/dasser-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} dasser.jar
ENTRYPOINT ["java","-jar","/dasser.jar"]