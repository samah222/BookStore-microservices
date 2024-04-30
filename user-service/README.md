# BookStore-config-server

BookStore - User Service

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

User Service is responsible for managing user accounts and authentication. Implement APIs for user registration,
change password, password reset, and profile management.

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
   cd user-service

3. Build and run the Spring Boot Server:
    ```bash
   mvn spring-boot:run

The Order service will start on port 8083, see the application.properties file.
To build docker image for this service. There are two options, using Dockerfile or using Buildpacks:

Then to build the image use the following commands:

  ```bash
 mvn spring-boot:build-image
