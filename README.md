# OnlineOrderApplication

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

#### Then Start MongoDB databases
```aidl
docker-compose up
```
