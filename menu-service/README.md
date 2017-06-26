# Menu Service

## Feature Supported
#### 1. Rest APIs
##### Admin Privilege:
* Upload Restaurant Information:
    * POST URL:```/restaurants``` 
    * Sample: 
    ```json
    {
      "admin": true,
      "restaurants": [
        {
          "latitude": 38.9093216,
          "longitude": -76.0036435,
          "restaurantName": "oyo",
          "restaurantAddress": "11111",
          "restaurantCity": "san jose",
          "restaurantCuisine": "chinese",
          "menu": [
            {
              "itemName": "first",
              "itemDescription": "good",
              "itemPrice": 12.99,
              "itemType": "ENTREES"
            },
            {
              "itemName": "second",
              "itemDescription": "bad",
              "itemPrice": 2.99,
              "itemType": "APPETIZERS"
            }
          ]
        },
        {
          "latitude": 38.8093216,
          "longitude": -75.0036435,
          "restaurantName": "hola",
          "restaurantAddress": "2222",
          "restaurantCity": "san jose",
          "restaurantCuisine": "chinese",
          "menu": [
            {
              "itemName": "first",
              "itemDescription": "good",
              "itemPrice": 12.99,
              "itemType": "ENTREES"
            },
            {
              "itemName": "second",
              "itemDescription": "oh..no",
              "itemPrice": 2.99,
              "itemType": "APPETIZERS"
            }
          ]
        }
      ]
    } 
    ```

* Upload Menu Item Information: 
    * POST URL:```/{restaurantName}/menu```
    * Sample: 
    ```json
    {
      "admin": true,
      "items": [
        {
          "itemName": "third",
          "itemDescription": "not bad",
          "itemPrice": 9.99,
          "itemType": "ENTREES"
        },
        {
          "itemName": "fourth",
          "itemDescription": "not good",
          "itemPrice": 3.99,
          "itemType": "APPETIZERS"
        }
      ]
    }
    ```
* Delete All Restaurant Information: 
    * DELETE URL: ```/restaurants```
    * Sample: 
    ```json
    {"admin": true}
    ```
    
##### User Privilege:
* Search restaurant by name :
    * GET URL: ```/{restaurantName}```
    
* Search restaurant by current location with optional customized pagination and distance
    * GET URL: ```/restaurants/near```
    * Sample: ```/near?lat=38.9093216&lng=-76.0036435&page=0&size=10&distance=10```
    
* Search menu within a range :
    * GET URL: ```/{restaurantName}/menu```
    * Sample: ```/{restaurantName}/menu?low=5&high=15```

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
cd menu-service/
```

#### 3. run MongoDB docker instance
```aidl
docker-compose up
```

#### 4. Build and run Spring Boot application
```aidl
mvn clean install
java -jar target/menu-service-1.0.0.jar