FROM openjdk:8-jre
MAINTAINER Paolo Cariaso <mpcariaso@gmail.com>

RUN mkdir -p /maven

# copy the application jar file from target to the image container
ARG JAR_FILE
ADD target/${JAR_FILE} /maven/spring-boot-dockerfile.jar

# This is run within the container
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/maven/spring-boot-dockerfile.jar"]

# Add Maven dependencies (not shaded into the artifact; Docker-cached)
#ADD target/lib           /usr/share/myservice/lib