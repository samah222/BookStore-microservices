BookStore microservice is a project ro manage Book store which has user management, Books and order services. It consist of the following:

### Config Server
In microservices architecture, it's essential to manage configuration properties efficiently across services.
The Spring Cloud Config Server simplifies this by providing a centralized location for storing and distributing
configuration properties. This config-server set up a Spring Boot Configuration Server for all BookStore
services to retrieve their configuration from it.

### Eureka Server
It acts as service discovery for microservices, which is a way for applications and microservices to locate
each other on a network automatically.

### API Gateway
In microservices architecture, An API gateway acts as a centralized entry point for all microservice.
It is essential to manage incoming requests and routes them based on key
factors such as request path, headers, and query parameters, among others.

### Book Service
Book microservice is responsible for managing books. This service provides APIs for CRUD operations on books,
including adding new books, retrieving book details, updating book information, and deleting books.
It uses a relational database MySQL to store book data.

### Order Service
Order microservice is responsible for managing orders. This service handles creating new orders,
retrieving order details, updating order status, and canceling orders. It uses database transactions
to ensure data consistency when processing orders.

### User Service
User Service is responsible for managing user accounts and authentication. Implement APIs for user registration,
change password, password reset, and profile management.
