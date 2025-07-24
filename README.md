# Order Track

**Brief**  
This project is designed to track orders.

---

## 📂 Content

1. [Description](#-description)
2. [Installation](#-installation)
3. [Exploitation](#-exploitation)
4. [Technologies](#-technologies)
5. [Functionality](#-functionality)

---

## 📝 Description

This project provides a REST API for working with products, customers, orders and payment of the orders.

---

## 🚀 Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/VladislavKabral/OrderTrack.git

2. Move to the folder with the project:

   ```bash
   cd 'Your folder with the project'

3. Install the dependencies:

   ```bash
   ./gradlew build

4. You need to change environment variable for yourself. Before starting, add your DB_USER, DB_PASSWORD, DB_URL 
   for the all services. Be careful, OrderService use MongoDB and requires another environment variable.

    Example for PostgreSQL:
   ```
   DB_USER='Your database user'
   DB_PASSWORD='Your database password'
   DB_URL='Your database name'
   ```    
   
   Example for MongoDB:
   ``` 
   DB_HOST='Your database host'
   DB_NAME='Your database name'
   DB_PORT='Your database port'
   ```

5. Then you need to start Kafka, MongoDB and Redis. The easiest way to do it is to use Docker containers.  
   Redis:
   ```bash
   docker run -d --name redis -p 6379:6379 redis:latest
   ```
   MongoDB:
   ```bash
   docker run -d --name mongodb -p 27017:27017 mongo:latest
   ```
   Kafka:
   ```bash
   docker run -d --name zookeeper -p 2181:2181 -e ZOOKEEPER_CLIENT_PORT=2181 -e ZOOKEEPER_TICK_TIME=2000 confluentinc/cp-zookeeper:latest
   
   docker run -d --name kafka --link zookeeper -p 9092:9092 -e KAFKA_BROKER_ID=1 -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 -e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=PLAINTEXT:PLAINTEXT -e KAFKA_INTER_BROKER_LISTENER_NAME=PLAINTEXT -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 confluentinc/cp-kafka:latest
   ```
   Please, check the commands before running.<br><br> 
    
6. Now, you can use the app. Start EurekaServer, after that ApiGateway and then the other services.

## 💻 Exploitation

Use an application to send requests, for example, Postman.

* GET: http://localhost:8765/customers
* GET: http://localhost:8765/customers/{id}
* POST: http://localhost:8765/customers
* PUT: http://localhost:8765/customers/{id}
* DELETE: http://localhost:8765/customers/{id}
  <br><br>
* GET: http://localhost:8765/products
* GET: http://localhost:8765/products/{id}
* POST: http://localhost:8765/products
* PUT: http://localhost:8765/products/{id}
* GET: http://localhost:8765/products/{id}/check?count=&totalAmount=
* PUT: http://localhost:8765/products
  <br><br>
* GET: http://localhost:8765/payments/accounts
* GET: http://localhost:8765/payments/accounts/{id}
* POST: http://localhost:8765/payments/accounts
* POST: http://localhost:8765/payments/payment
* PUT: http://localhost:8765/payments/{id}/balance
  <br><br>
* GET: http://localhost:8765/orders
* GET: http://localhost:8765/orders/{id}
* POST: http://localhost:8765/orders

P.S. You can find DTO schemes for POST and PUT requests in the project's structure.

---

## 🛠️ Technologies

* Kotlin 1.9.25
* Spring Boot 3.5.3
* Spring Data JPA
* Spring Data MongoDB
* Gradle
* PostgreSQL
* Flyway
* MongoDB
* Mongock
* Redis
* Kafka
* Eureka server
* API Gateway
* OpenFeign

---

## ✨ Functionality

* Making an order
* Paying the order by local payment system
* Creating a payment account
* Updating the payment account's balance
* Product management
* Customer data management
* Sending a notification in case of successful payment