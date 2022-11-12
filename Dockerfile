FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD ./target/achat-1.0.jar Tes_Prod.jar 
ENTRYPOINT ["java","-jar","/Tes_Prod.jar"]