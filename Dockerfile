FROM openjdk:16-jdk-alpine
ADD /target/Exchange_bot-0.0.1-SNAPSHOT.jar exchange_bot.jar
ENTRYPOINT ["java","-jar","/exchange_bot.jar"]