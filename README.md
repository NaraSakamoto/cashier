# Cashier

Cashier is a simple system that process boleto and credit card payments by API or checkout page.

### Techs
Cashier uses a number of open source projects to work properly:

* [Maven] - Build management tool
* [Java 8] - Programming language.
* [spring-boot] - Framework that makes it easy to create a Spring Framework based application.
* [PostgreSQL] - Object-relational database system.
* [Liquibase] - Library for creating, managing and applying database schema changes.
* [Freemarker] - Java template engine. Creates the HTML.
* [Bootstrap] - Front-end framework for creating user interface components.
* [Swagger] - Framework for describing the API

### Installation

Cashier requires Java 8+, Maven 3+ and a postgres database to run.

Create a database named 'cashier' with an owner that the login role is 'cashier' and the password is 'cashier'.

```sh
$ CREATE ROLE cashier LOGIN ENCRYPTED PASSWORD 'md59825ef00c46fc8e97ba078ef6b3c6f8a'
```

```sh
$ CREATE DATABASE cashier WITH OWNER = cashier;
```

Generate the .jar file compiling application with maven.

```sh
$ cd ${project location}/cashier
$ mvn package
```

Run the project with java. It will be available on port 8080

```sh
$ cd ${project location}/cashier/target
$ java -jar cashier-0.0.1-SNAPSHOT.jar
```

## Creating payments

There are two ways for create a payment. You can use the API and make a POST request with a JSON or use the checkout page.

### Using API
Details on http://localhost:8080/swagger-ui.html

Create a POST request using a JSON with payment information. You can use the clients that are already created on database, clients with id 1 or id 2.

URL: http://localhost:8080/api/payments
```json
{
  "buyer": {
    "cpf": 26576589634,
    "email": "nara.sakamoto@gmail.com",
    "name": "Nara Sakamoto"
  },
  "client": {
    "id": 1
  },
  "payment": {
    "amount": 100.0,
    "card": {
      "cvv": 123,
      "expirationDate": "2018-03-03",
      "holderName": "NUBANK",
      "number": 123456789
    },
    "type": "CREDIT_CARD"
  }
}
```
### Using checkout page
You can access the checkout page by:
- client 1: http://localhost:8080/payments/1
- client 2: http://localhost:8080/payments/2

## Todos

 - Write MORE Tests
 - Improve the checkout page creating validations and masks for the fields
 - Use docker

   [Maven]: <https://maven.apache.org/>
   [Java 8]: <https://www.java.com/pt_BR/download/faq/java8.xml>
   [spring-boot]: <https://projects.spring.io/spring-boot/>
   [PostgreSQL]: <https://www.postgresql.org/>
   [Liquibase]: <https://www.liquibase.org/>
   [Freemarker]: <https://freemarker.apache.org/>
   [Bootstrap]: <https://getbootstrap.com/>
   [Swagger]: <https://swagger.io/>