# Order Service

## Feature Supported
#### 1. Rest APIs
* Create an Order:
    * POST URL:```/orders``` 
    * Sample: 
    ```json
    {

    } 
    ```

* Get order information: 
    * GET URL:```/orders/{orderId}```

* Add orderEvent: 
    * POST URL: ```/orders/{orderId}/events```
    * Sample: 
    ```json
    {
  
    }
    ```
    
#### 2. HAL Browser
* User can inspect supported REST APIs from Hal Browser
```localhost:port/browser```


## Requirements 
* Java Platform (JDK) 8
* Apache Maven
* Docker
* Docker Compose 

## Installation
#### 1. clone git
```aidl
git clone https://github.com/ljbschen/OnlineOrderApplication.git
```

#### 2. cd to Menu Service Folder
```aidl
cd order-service/
```

#### 3. run MongoDB docker instance
```aidl
docker-compose up
```

#### 4. Build and run Spring Boot application
```aidl
mvn clean install
java -jar target/order-service-1.0.0.jar