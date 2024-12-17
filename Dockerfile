FROM openjdk:17-jdk
EXPOSE 8089
ADD target/Foyer-0.0.1.jar Foyer-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/Foyer-0.0.1.jar"]