# BookStore-config-server

BookStore-config-server

## Table of Contents

- [Project Overview](#project-overview)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Running the Config Server](#running-the-config-server)
- [Configuration Management](#configuration-management)
    - [Git Repository](#git-repository)
    - [Configuration Files](#configuration-files)
- [Client Services](#client-services)
- [Contributing](#contributing)
- [License](#license)

## Project Overview

In microservices architecture, it's essential to manage configuration properties efficiently across services.
The Spring Cloud Config Server simplifies this by providing a centralized location for storing and distributing
configuration properties. This config-server set up a Spring Boot Configuration Server for all BookStore
services to retrieve their configuration from it.

## Getting Started

### Prerequisites

To run this project, you need the following tools installed:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)

### Running the Config Server

1. Clone this repository to your local machine:

   ```bash
   https://github.com/samah222/BookStore-microservices.git

2. Navigate to the project directory:
   ```bash
   cd config-server

3. Build and run the Spring Boot Server:
    ```bash
   mvn spring-boot:run

The Book microservice will start on port 8888, see the application.properties file.
To build docker image for this service. There are two options, using Dockerfile or using Buildpacks:

Then to build the image use the following commands:

  ```bash
 mvn spring-boot:build-image
