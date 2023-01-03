# AS <NAME> to name this stage as maven
FROM maven:3.8.3-openjdk-17 as build

WORKDIR /usr/src/app
COPY . /usr/src/app
# Compile and package the application to an executable JAR
RUN mvn clean install -DskipTests

# For Java 17, 
FROM openjdk:17-alpine

ARG JAR_FILE=jwt_learn-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

#copy the jar file from target to opp/app
COPY --from=build /usr/src/app/target/${JAR_FILE} /opt/app/

ENTRYPOINT ["java","-jar","jwt_learn-0.0.1-SNAPSHOT.jar"]
