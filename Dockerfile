FROM openjdk:17-oracle
ADD target/spring-boot-batch.jar spring-boot-batch.jar
ENTRYPOINT ["java", "-jar", "spring-boot-batch.jar"]