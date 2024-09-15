FROM openjdk:17
EXPOSE 8080
ADD target/viajesglobal-github.jar viajesglobal-github.jar
ENTRYPOINT["java","-jar","/viajesglobal-github.jar"]