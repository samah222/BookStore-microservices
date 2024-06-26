# BookStore- Service Discovery server

BookStore - Service Discovery server : Eureka Server

## Table of Contents

- [Project Overview](#project-overview)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Running the Server](#running-the-server)

## Project Overview

It acts as service discovery for microservices, which is a way for applications and microservices to locate
each other on a network automatically.

## Getting Started

### Prerequisites

To run this project, you need the following tools installed:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)

### Running the Server

1. Clone this repository to your local machine:

   ```bash
   https://github.com/samah222/BookStore-microservices.git

2. Navigate to the project directory:
   ```bash
   cd Eureka-server-bookstore

3. Build and run the Spring Boot Server:
    ```bash
   mvn spring-boot:run

The Order service will start on port 8761, see the application.properties file.
To build docker image for this service. There are two options, using Dockerfile or using Buildpacks:

Then to build the image use the following commands:

  ```bash
 mvn spring-boot:build-image
