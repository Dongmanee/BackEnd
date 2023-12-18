FROM openjdk:17-alpine

ARG JAR_FILE=./dongmanee/build/libs/dongmanee-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} /dongmanee.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev", "/dongmanee.jar"]