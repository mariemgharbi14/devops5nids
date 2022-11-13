FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD ./target/achat-1.0.jar Test_categ.jar 
ENTRYPOINT ["java","-jar","/Test_categ.jar"]