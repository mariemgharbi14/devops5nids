FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD ./target/achat-1.0.jar Test_facture.jar 
ENTRYPOINT ["java","-jar","/Test_facture.jar"]
