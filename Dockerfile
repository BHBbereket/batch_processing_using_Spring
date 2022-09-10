FROM openjdk:17-oracle
ADD target/*.jar spring-boot-batch.jar
ENTRYPOINT ["java", "-jar", "spring-boot-batch.jar"]