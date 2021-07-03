FROM adoptopenjdk/openjdk8:alpine-jre
ARG JAR_FILE=target/load-balancer-1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]