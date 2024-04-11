# Installation Guide for Student Catering Services Application

This document outlines the steps necessary to install and run the Student Catering Services web application on a local development environment.

## Prerequisites

Ensure you have the following installed:

- A web browser (e.g., Google Chrome, Firefox, Safari)
- A local server environment (XAMPP, WAMP, MAMP, or equivalent)
- Java Development Kit (JDK), if the backend is Java-based
- MySQL Server for database management

## Step 1: Clone the Repository

Clone the project to your local machine:

```bash
git clone https://github.com/Kyoka-run/EE417-GroupAssignment.git
cd EE417-GroupAssignment

## Step 2: Set Up the Local Server
Copy the project into the document root of your local server:

For XAMPP, it's usually C:/xampp/htdocs/.
For WAMP, it's usually C:/wamp/www/.
For MAMP, it's usually /Applications/MAMP/htdocs/.

## Step 3: Import the Database
Start your MySQL server using your local server environment control panel.
Open your preferred MySQL database management tool (e.g., phpMyAdmin).
Create a new database named student_catering_db.
Import the provided SQL script located in the database directory of the project to create and populate tables.

## Step 4: Configure the Application
Update the database connection settings in the server-side code to match your local MySQL setup. You may need to set up the following:

Database name: student_catering_db
Username: Typically root for local environments
Password: Usually empty ('') for local environments, unless you set one

## Step 5: Start the Application
Launch the application by opening your web browser and visiting:
http://localhost/EE417-GroupAssignment

## Troubleshooting
If you face any issues:

Check that your local server and MySQL services are active.
Confirm that the project files are in the correct directory for your server environment.
Make sure the database name and credentials in your configuration file match your local MySQL setup.
Ensure the SQL script is correctly imported, and tables are present in your database.
If you continue to experience issues, review the project's documentation for more guidance or contact a project maintainer.
