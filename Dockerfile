FROM openjdk:17-jdk-alpine
MAINTAINER baeldung.com
COPY target/accountingOfSocks-0.0.1-SNAPSHOT.jar accountingOfSocks-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/accountingOfSocks-0.0.1-SNAPSHOT.jar"]
#docker build -t springio/gs-spring-boot-docker .КОПИРОВАТЬ
 #Эта команда создает изображение и маркирует его как springio/gs-spring-boot-docker.

# version: '3.2'
# services:
#   mysql_db:
#     image: mysql:latest
#     environment:
#       MYSQL_ROOT_PASSWORD: secret
#       MYSQL_DATABASE: messanger
#     ports:
#       - "3306:3306"