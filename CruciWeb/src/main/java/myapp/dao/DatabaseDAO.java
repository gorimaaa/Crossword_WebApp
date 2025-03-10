package myapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseDAO {
	/*private static final String DB_URL = "jdbc:mysql://localhost/projet";
    private static final String DB_USER = "projet"; // Nom d'utilisateur
    private static final String DB_PASSWORD = "tejorp"; // Mot de passe*/

    private static final String DB_URL = "jdbc:mysql://167.172.62.4:8889/CruciWeb";
    private static final String DB_USER = "root"; // Nom d'utilisateur
    private static final String DB_PASSWORD = "root"; // Mot de passe*/

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC introuvable : " + e.getMessage());
            throw new SQLException("Erreur lors du chargement du driver JDBC.");
        }

        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
