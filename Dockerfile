FROM openjdk:17

COPY target/soundy-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "soundy-0.0.1-SNAPSHOT.jar"]
LABEL authors="voodookiidoo"
