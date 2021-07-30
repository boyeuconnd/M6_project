FROM openjdk:8-jdk-alpine
EXPOSE 8080

ENV DB_DRIVER=com.mysql.cj.jdbc.Driver
ENV DB_URL=jdbc:mysql://mysql-8:3306/m6_project
ENV DB_USERNAME=root
ENV DB_PASSWORD=1234
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
ADD /build/libs/m6-project-0.1.0.jar m6-project-0.1.0.jar
ENTRYPOINT ["java","-jar","m6-project-0.1.0.jar"]