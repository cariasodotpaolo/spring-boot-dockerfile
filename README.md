# Spring Boot Docker build using Spotify Maven plugin

PREREQUISITE: Docker For Mac, Dockerfile in project root where pom.xml is located

1. Build the application

   > mvn clean [install | package] dockerfile:build -P [local | dev | uat] (default is local)

   Note: This will not run the image but the image will be pushed

2. check the target folder:

   - the profile name is appended to JAR file name
   - check the application.properties file in classpath resource. properties should reflect the profile used

4. Check the docker image that has been pushed

   > docker images

   - compare the image ID shown by mvn stdout to the output of 'docker images'. Should be the same.

5. Check the docker dir created by the plugin inside target dir.

6. run the image:

   > docker run -i -t --rm -p 8091:8090 fb3427ccc45b

   - where fb3427ccc45b is the image ID


Check using MVC Controller. Browse the following URL:

LOCAL profile - http://localhost:8091/spring-boot-dockerfile-LOCAL/profiles/active
DEV profile - http://localhost:8091/spring-boot-dockerfile-DEV/profiles/active
UAT profile - http://localhost:8091/spring-boot-dockerfile-UAT/profiles/active

Note:
- the context root name changes based on profile used to illustrate property value change.
- the profile name displayed by the browser is being retrieved using Spring's Environment bean getActiveProfiles() method.