# Spring Boot and Spring Batch Project for Reading Paginated Data from External REST API
## Overview
This project utilizes the power of Spring Boot and Spring Batch to efficiently read paginated data from an external REST API (https://gorest.co.in/). The goal is to handle large datasets by fetching data in chunks, or pages, to ensure scalability and optimal performance.

## Technologies Used
- **Spring Boot**: A powerful framework for building Java-based, enterprise-grade applications with a focus on convention over configuration, simplicity, and ease of use.
- **Spring Batch**: A lightweight, comprehensive framework for batch processing in Java. It provides reusable functions that are essential in processing large volumes of records, including logging/tracing, transaction management, job processing statistics, and resource management.

## Prerequisites
- Java 21
- Maven
- Mysql Database
- IDE of your choice (E.g., IntelliJ, Eclipse)

## Usage
1) Clone the repository:
```bash
git clone https://github.com/AniltonMoraisJr/ServiceReaderJob.git
```
2) Navigate to the project directory:
```bash
cd spring-batch-rest-api-paginated-reader
```
3) Configure database properties in application.properties:

```properties
# Spring Batch database
spring.datasource.driver-class-name= com.mysql.cj.jdbc.Driver
spring.datasource.jdbcUrl=jdbc:mysql://localhost:3306/spring_batch
spring.datasource.username=root
spring.datasource.password=root
```

4) Check the properties to config size of chunk, limit (limit of elements to get from external api) and page size.
Obs. The limit should be removed if you want to get all data. 

```properties
chunkSize=40
limit=40
pageSize=10
```
5) Build the project:
```bash
mvn clean install
```
6) Run the application:
```bash
java -jar target/spring-batch-rest-api-paginated-reader.jar
```
The application will execute the Spring Batch job, fetching and processing paginated data from the external REST API.

Feel free to customize the project according to your specific requirements and integrate it into your Spring Boot application.