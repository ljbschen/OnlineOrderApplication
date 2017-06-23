# OnlineOrderApplication

## Feature Supported
#### 1. Rest APIs
* Upload Restaurant Information:
    * POST URL:```/menu/upload``` 
    * Sample: 
    ```json
    [
      {
       "latitude": 38.9093216,
       "longitude": -76.0036435,
       "restaurantName":"oyo",
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
      }
    ]
    ```

* Upload Menu Item Information: 
    * POST URL:```/menu/addItems?restaurantName=$$$```
    * Sample: 
    ```json
    [
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
    ```
* Delete All Restaurant Information: 
    * DELETE URL: ```/menu/purge```
* Search restaurant by name :
    * GET URL: ```/menu/{restaurantName}```
    
* Search restaurant by current location with optional customized pagination and distance
    * GET URL: ```/menu/near```
    * Sample: ```/menu/near?lat=38&lng=-77&page=0&size=10&distance=1```

#### 2. HAL Browser
* User can inspect supported REST APIs from Hal Browser
```localhost:port/browser```
