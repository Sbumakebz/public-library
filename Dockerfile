FROM openjdk:21

# cd /opt/app
WORKDIR /opt/public-library

# Refer to Maven build -> finalName
ARG JAR_FILE=target/public-library.jar

# cp target/spring-boot-web.jar /opt/public-library/public-library.jar
COPY ${JAR_FILE} /opt/public-library

EXPOSE 8000

ENTRYPOINT ["java","-jar","public-library.jar"]