FROM maven:3-jdk-8-alpine as builder
ADD . /root
WORKDIR /root
RUN mvn clean install -DskipTests
FROM openjdk:8-jre-alpine
WORKDIR /root
COPY --from=builder /root/pet-clinic-web/target/pet-clinic-web-0.0.7-SNAPSHOT.jar .
ENTRYPOINT [ "sh", "-c", "java -jar pet-clinic-web-0.0.7-SNAPSHOT.jar" ]
EXPOSE 8080