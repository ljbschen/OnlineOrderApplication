# Cart Service
## Introduction
Cart-service is an event-driven application that keeps tracking states of shopping cart. I choose Mongodb as the datastore for quick and massive searching queries. 

## Feature Supported
#### 1. Rest APIs
* Add Cart Event:
    * Create append-only states to the cart
    * POST URL:```/cart/{userId}/events``` 
    * Sample: 
    ```json
    {
      "cartEventType": "ADD_ITEM",
      "item":{
        "name": "1",
        "price": 12.99,
        "tax": 1.00,
        "restaurantName":"oyo"
      } 
    } 
    ```

* Get current cart information: 
    * Aggregate all events and return the current cart
    * GET URL:```/cart/{userId}```
    
* Checkout current cart: 
    * Generate Order object and send to Payment-Service, return a link to Payment-Service
    * POST URL: ```/cart/{userId}/checkout```
    
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