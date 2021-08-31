# HotelBooking

This a Java application that is run on IntelliJ Spring Boot.

Clone the application from the `master` branch. Import the application on IntelliJ (Maven). Wait till Maven setting is done. 
Edit configurations for the Maven app to choose spring-boot:run, then Run the application.

The requests would then be accessible through API call. I used Postman to test the API calls.

POST: http://localhost:8080/checkin

POST: http://localhost:8080/checkout
{"roomId": "1B"}

GET: http://localhost:8080/available-rooms

POST: http://localhost:8080/mark-available
{"roomId": "1B"}

POST: http://localhost:8080/mark-repair
{"roomId": "1B"}
