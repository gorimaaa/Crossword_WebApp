# CruciWeb - A Crossword Puzzle Platform

**CruciWeb** is a JEE-based web application that allows users to create and solve crossword puzzles. The project follows a custom implementation without using any frameworks and includes a MySQL database for backend operations.

## Technologies Used

- **Java**: Backend logic using Servlets.
- **JSP**: Frontend with embedded Java.
- **JavaScript**: Dynamic user interactions.
- **MySQL**: Data storage for users, puzzles, and progress.

## Deployment

1. **Run Locally**:
   - Open the project in Eclipse.
   - Configure a MySQL database, upload the "Database.sql" file in a database and update credentials in `DatabaseDAO.java`.
   - Create and start a Tomcat server to host the application locally.

2. **Production Deployment**:
   - Export the project as a `.war` file from Eclipse.
   - Upload the `.war` file to a production Tomcat server.
   - Configure the database connection in `DatabaseDAO.java` for the production environment.
   - Upload the "Database.sql" file into MySQL.

## Demo Video
[Capture vidéo du 2025-01-11 14-41-16.webm](https://github.com/user-attachments/assets/c2b0680d-6ad5-4bd6-b99d-686b47570f2d)


