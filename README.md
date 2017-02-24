# Reverse Geocoder


**! IMPORTANT !**

Replace the dummy `api.key` value in `src/main/resources/googlemaps.properties` and `src/test/resources/googlemaps.properties` with a valid Google Maps API key for full functionality.

##### Highlights
- Spring Boot
- Swagger (SpringFox)
- Google Maps Java API
- In-Memory Cache 
    - FIFO
    - bounded (configurable)
    - thread-safe
- Gradle with Code Quality plugins
- 100% Code Coverage

## Instructions
To Build - `gradlew build`

To Run - `gradlew bootRun`

To Test (including static code analysis) - `gradlew check`

API Documentation (once running) - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Get Address - [http://localhost:8080/address?lat=42.22&lon=-90.01](http://localhost:8080/address?lat=42.22&lon=-90.01)

Get Address Cache - [http://localhost:8080/address/cache](http://localhost:8080/address/cache)
