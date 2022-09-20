# Warehouse management system
The warehouse management system consists of 3 services that communicate with each other over REST API. The system creates orders depending on a user request. The system analyses amount of requested goods, its presence in a stock, creates an order, and calculates its total price depending on the catalogue of products. Also, the system is able to handle exceptional business scenarios.
## Services
* Catalog Service - the service is responsible for retrieving actual prices for requested products.
* Inventory Service - the service is responsible for checking the amount of goods in the stock.
* Order Service - the service processes client's requests. It creates orders according to a user request and calogue & inventory services' info.
## Technologies and tools used
### Technologies
* Java
* Spring Boot
* Spring Data JPA
* MySQL
### Tools
* Eclipse IDE
* Postman
* MySQL Workbench
