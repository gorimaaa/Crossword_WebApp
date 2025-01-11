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
   - Configure a MySQL database and update credentials in `DatabaseDAO.java`.
   - Create and start a Tomcat server to host the application locally.

2. **Production Deployment**:
   - Export the project as a `.war` file from Eclipse.
   - Upload the `.war` file to a production Tomcat server.
   - Configure the database connection in `DatabaseDAO.java` for the production environment.

## Demo Video

A short demonstration video showcasing the core features of CruciWeb is included in the repository.

## Getting Started

### Prerequisites

- JDK 11 or later
- Apache Tomcat (tested with version 9.0)
- MySQL Server
- Eclipse IDE (for local development)

### Installation

1. Clone the repository.
2. Import the project into Eclipse as a dynamic web project.
3. Configure MySQL and update `DatabaseDAO.java` with your credentials.
4. Run the project on Tomcat.


