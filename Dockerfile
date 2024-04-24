FROM maven:3.8.3-openjdk-21
EXPOSE 80
COPY / ./
# Refer to Maven build -> finalName
ARG JAR_FILE=target/*.jar

# cd /opt/app
WORKDIR /opt/public-library

# cp target/spring-boot-web.jar /opt/public-library/public-library.jar
COPY ${JAR_FILE} public-library.jar

ENTRYPOINT ["java","-jar","public-library.jar"]