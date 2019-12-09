# fraud-Score-calculator
API is to provide fraud score of a message send from sender. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

> Prerequisite

##### To run application on docker environment

* Docker 
* Docker-compose

##### To run application without docker environment

* Maven
* Java 8 or higher

> Steps

##### On Docker environment

1. Clone the repository where docker service is available.
2. Go inside the project directory using command line.
3. Run docker-compose up to start application
4. To rebuild the application use docker-compose build and then docker-compose up to start application.
5. You can change application port in docker-compose.yml. Current port number is "8080". Please check and modify according to your need.

   
##### Without Docker environment

1. Clone the repository.
2. Go inside the project directory using command line.
3. Install mvn and execute mvn clean spring-boot:run. Application port is 8080, you can change in application.yml file.


Test the endpoints using swagger http://localhost:8080/swagger-ui.html#/

  * http://localhost:8081/message
  
  or you can use any REST API test tools such as Postman or SoapUI.


  

## Task Description

A large number of fraudulent messages are being sent through the Care.com messaging service. In order to prevent this, you are given the task to calculate a fraud score of messages being sent.

A higher score equates to a higher risk of fraud.

## Tasks
1. Create a microservice that meets the requirements below
1. Include a Dockerfile to run the application


## Microservice 
Create a fraud detection microservice written in Kotlin or Java in a framework of your choice.
The microservice should expose a REST api that adheres to the swagger file `fraud-api.yml`.


Include appropriate test coverage.

### Optional
- Deploy the microservice to a cloud provider of your choice.

### Calculate fraud score
The Fraud score should be calculated by analyzing the `message` attribute

- For every `<a> tag` add 1 point.
- For every occurrence of any word in the following list (`money`, `transfer`, `paypal`) add 10 points.
- If the sender has messaged the recipient more than 5 times in the last 2 hours add 100 points.

#### Request

##### POST /message 
```
{
"message": "string",
"recipientId": "string",
"senderId": "string"
}
```
#### Response
```
{
"score": "Long",
}
```

## Development and Architecture Aspects

> Technology used

* Java 8
* Spring boot
* Maven 
* Swagger for documentation
* H2 in-memory database
* Docker


## Test Cases

Unit Test cases and Integration test cases were written. Please use below command to run test cases

```
mvn clean test
```

find following file inside working directory - **target/site/jacoco/index.html** file, review the code coverage report 


