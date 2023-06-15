FROM openjdk:17-jdk-alpine AS build
COPY ./ /build
WORKDIR /build
USER spring:spring

FROM openjdk:17-jdk-alpine
WORKDIR /app
ENV APP_DIST="api"
ENV JAVA_OPTS=${JAVA_OPTS}

COPY --from=build /build/build/libs/${APP_DIST}-0.0.1-SNAPSHOT.jar /app/spring-boot-application.jar
ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]
