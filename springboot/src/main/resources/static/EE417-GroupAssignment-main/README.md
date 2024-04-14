# EE417-GroupAssignment
## Data-monitored Student Catering Services

Welcome to the Data-monitored Student Catering Services web application, developed for the EE417 course assignment. This application serves as a platform for students to explore and order a variety of food options provided by the student catering service, including coffee, pizza, Mexican dishes, and more.

## Features

- Responsive design ensuring a seamless experience across various devices and screen sizes.
- User authentication system with separate access for administrators and students.
- Dynamic navigation bar facilitating easy access to all the sections of the application.
- Comprehensive food menus for pizza, coffee, and Mexican cuisine with a shopping cart feature.
- Informative pages like 'About Us', 'FAQ', 'Terms of Service', and a contact form for inquiries.

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

Make sure you have the following installed:
- JDK 11 or later
- Maven
- MySQL Server or any other relational database server
- A modern web browser like Google Chrome, Firefox, or Safari.

### Back-end Setup
Step 1: Clone the Repository

Clone the repository to your local machine:

```bash
git clone https://github.com/Kyoka-run/EE417-GroupAssignment.git
cd EE417-GroupAssignment/springboot
```
Step 2: Configure Application Properties
Edit the application.properties file found in src/main/resources to reflect your local database settings:

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```

Step 3: Build the Project
Run the following command in the root directory of the Spring Boot project to build the application:

```bash
./mvnw clean install
# Or if using Maven directly:
# mvn clean install
```
This will compile the application and run any unit tests.

Step 4: Run the Application
To start the application, execute:

```bash
./mvnw spring-boot:run
# Or if using Maven directly:
# mvn spring-boot:run
```
The server will start, and the back-end will be accessible at http://localhost:8080.

For more detailed installation information, see INSTALL.md in the docs folder.

### Database Initialization
Execute the SQL scripts provided in the database directory to set up your database schema and populate it with initial data.

### Testing the Application
With the server running, open your web browser and navigate to http://localhost:8080. You should see the landing page of the Student Catering Services application.
   
### Usage
Navigate through the application using the navigation bar. Access different sections such as 'Home', 'Shop', and 'About Us'. For administrative functions, use the admin login form available at admin.html.

### API Documentation
Refer to the api_documentation.md file in the repository for detailed information about backend endpoints, their usage, and examples.

### Contributing
Feel free to fork the project and submit pull requests. You can also open issues for bugs or feature requests.

### License
Distributed under the MIT License. See LICENSE for more information.

### Acknowledgements
Thanks to all contributors who have invested their efforts in this project.
Special thanks to DCU for providing the platform for this assignment.

