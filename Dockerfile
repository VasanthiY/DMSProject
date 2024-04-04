FROM maven:3.9.5-jdk-17 AS build 
COPY . . 
RUN mvn clean package -Pprod -DskipTests 


FROM openjdk:17-jdk-slim
COPY --from=build /target/DogsManagementSystem-0.0.1-SNAPSHOT.jar DogsManagementSystem.jar
CMD ["java", "-jar", "DogsManagementSystem.jar"] 