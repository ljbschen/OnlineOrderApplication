# OnlineOrderApplication

#### Introduction
![alt text](https://user-images.githubusercontent.com/9488989/27652635-57197dc2-5bf1-11e7-92a3-6a9e5afcdc6d.png)


#### Start Edging Service first
```
cd edge-service
mvn clean install
java -jar target/edge-service-1.0.0.jar
```

#### Then start microservices
1. Menu-service
2. Order-service
3. Cart-service
4. Payment-service
5. Process-service

#### Then Start MongoDB databases
```aidl
docker-compose up
```
