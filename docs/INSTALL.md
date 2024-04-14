# Installation Guide for Student Catering Services Application

This guide details the process for setting up the Student Catering Services application, including both the front-end and the Spring Boot back-end components.

## Prerequisites

Before you begin, ensure you have the following installed:
- JDK 11 or later
- Maven
- MySQL Server or any other relational database server

## Back-end Setup

### Step 1: Clone the Repository
Clone the repository to your local machine:

git clone https://github.com/Kyoka-run/EE417-GroupAssignment.git
cd EE417-GroupAssignment/springboot

### Step 2: Configure Application Properties
Edit the application.properties file found in src/main/resources to reflect your local database settings:

spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password

### Step 3: Build the Project
Run the following command in the root directory of the Spring Boot project to build the application:
# mvn clean install
This will compile the application and run any unit tests.

### Step 4: Run the Application
To start the application, execute:
# mvn spring-boot:run
The server will start, and the back-end will be accessible at http://localhost:8080.

## Front-end Setup
The front-end static files are served by the Spring Boot application once it is running. You can access the home page by navigating to http://localhost:8080.

### Database Initialization
Execute the SQL scripts provided in the database directory to set up your database schema and populate it with initial data.

### Testing the Application
With the server running, open your web browser and navigate to http://localhost:8080. You should see the landing page of the Student Catering Services application.

### Additional Notes
If you encounter any issues with the Maven Wrapper (i.e., ./mvnw commands), ensure you have the correct permissions set (e.g., chmod +x mvnw on Unix-like systems) or use Maven directly.
The back-end is configured to serve static content found in the src/main/resources/static directory. If you make changes to front-end files, you may need to restart the server to see the updates.
Detailed API documentation can be found in the docs directory, outlining the available endpoints and their usage.

Be sure to replace placeholder texts such as `your_database_name`, `your_username`, and `your_password` with the actual values relevant to your setup. This guide assumes the Spring Boot Maven Wrapper (`mvnw`) is included in your repository; if not, developers will need to have Maven installed and use `mvn` commands instead. The SQL scripts for database setup should be detailed enough to allow a simple execution in whatever database management tool the user prefers (like MySQL Workbench or phpMyAdmin).





