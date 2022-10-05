FROM openjdk:11
COPY build/libs/MenToMen-0.0.1-SNAPSHOT.jar app.jar
ENV TZ=Asia/Seoul
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=prod","/app.jar"]