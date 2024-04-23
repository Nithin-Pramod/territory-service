# Territory Service

A Spring Boot application for managing territories and finding their ancestors.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Usage](#usage)
- [Testing](#testing)

## Introduction

The Territory Service is a RESTful API built using Spring Boot that allows users to manage territories and find their ancestors. 

## Features

- Find ancestors of a territory
- RESTful API with intuitive endpoints
- Easy-to-use interface

## Getting Started

### Prerequisites

Before running the application, ensure you have the following installed:

- Java Development Kit (JDK) 8 or later
- Maven
### Properties

Add the following properties to your `application.properties` or `application.yml` file:

```properties
spring.application.name=territory-service
server.port=8082
springdoc.swagger-ui.path=/swagger-ui.html
```


### Installation

1. Clone the repository:

```bash
git clone https://github.com/Nithin-Pramod/territory-service.git
```

2. Navigate to the project directory:

```bash
cd territory-service
```

3. Build the project using Maven:

```bash
mvn clean install
```

## Usage

### File Paths

- **Test Classes**: Test classes are located in the `src/test/java` directory.
- **Other Classes**: Other classes, including main application classes, are located in the `src/main/java` directory.

```plaintext
project
├── java
│   └── com
│       └── territoryservice
│           └── territoryservice
│               ├── configuration
│               │   └── AppConfig.java
│               ├── controller
│               │   └── TerritoryController.java
│               ├── entity
│               │   └── Territory.java
│               ├── model
│               │   ├── Territories.java
│               │   └── TerritoryNode.java
│               ├── service
│               │   └── TerritoryService.java
│               └── TerritoryServiceApplication.java
├── resources
│   ├── application.properties
│   └── territories.json
└── test
    └── java
        └── com
            └── territoryservice
                └── territoryservice
                    ├── TerritoryControllerTests.java
                    └── TerritoryServiceTests.java
```


To use the Territory Service, follow these steps:

1. Run the application:

```bash
java -jar target/territory-service.jar
```
### Actuator Endpoints

The Actuator provides several endpoints to monitor and interact with the application. Access the Actuator endpoints at [http://localhost:8082/actuator](http://localhost:8082/actuator).

### Swagger UI

Swagger UI provides an interactive documentation interface for the API. Access Swagger UI at [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html).

### Swagger Documentation

The Swagger documentation provides detailed information about the API endpoints and their usage. Access the Swagger documentation at [http://localhost:8082/v3/api-docs](http://localhost:8082/v3/api-docs).


2. Access the API endpoints using an HTTP client such as cURL or Postman.

## Testing

The project includes unit tests and integration tests to ensure its functionality. To run the tests, use the following command:

```bash
mvn test
```



