FROM openjdk:8-jre
MAINTAINER Paolo Cariaso <mpcariaso@gmail.com>

RUN mkdir -p /maven

# copy the application jar file from target to the image container
ARG JAR_FILE
ADD target/${JAR_FILE} /maven/${JAR_FILE}

# This is within the container
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/maven/spring-boot-dockerfile-0.0.1-local.jar"]

# Add Maven dependencies (not shaded into the artifact; Docker-cached)
#ADD target/lib           /usr/share/myservice/lib