#Base Image (Linux)
#Install JRE/JDK
#Copy JAR file
#Start App
#Expose Port

FROM openjdk:11
LABEL description="Account Service Image"
#ARG JAR_FILE
#COPY ${JAR_FILE} app.jar
COPY  ./target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 8091