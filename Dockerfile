FROM openjdk:17--ea-9-jdk-slim
EXPOSE 8080
WORKDIR /usr/local/bin/ReservationLivres-0.0.1-SNAPSHOT.jar
ADD target/ReservationLivres-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","ReservationLivres-0.0.1-SNAPSHOT.jar"]


