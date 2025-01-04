package myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import myapp.model.User;

public class UserDao {
    DatabaseDAO dbQuery = new DatabaseDAO(); 

    public boolean registerUser(String username, String password, String email) {
        String getMaxIdQuery = "SELECT COALESCE(MAX(idUser), 0) + 1 AS nextId FROM Users";
        String insertQuery = "INSERT INTO Users (idUser, username, password, role, email) VALUES (?, ?, ?, ?, ?)";
 
        try (Connection conn = dbQuery.getConnection();
             PreparedStatement getMaxIdStmt = conn.prepareStatement(getMaxIdQuery);
             ResultSet rs = getMaxIdStmt.executeQuery()) {

            int nextId = 1; 
            if (rs.next()) {
                nextId = rs.getInt("nextId");
            }
            
            String role = "user";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setInt(1, nextId);
                insertStmt.setString(2, username);
                insertStmt.setString(3, password);
                insertStmt.setString(4, role);
                insertStmt.setString(5, email);

                int rowsInserted = insertStmt.executeUpdate();
                return rowsInserted > 0; 
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean validateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = dbQuery.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            return rs.next(); 

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public void displayAllUsers() {
        String query = "SELECT * FROM Users"; 
        try (Connection conn = dbQuery.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Liste des utilisateurs :");
            while (rs.next()) {
                int id = rs.getInt("idUser");
                String username = rs.getString("username");
                String password = rs.getString("password");
                System.out.println("ID: " + id + ", Nom d'utilisateur: " + username + ", Mot de passe: " + password);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des utilisateurs.");
        }
    }
    
    public String getUserRole(String email, String password) {
        String query = "SELECT role FROM Users WHERE email = ? AND password = ?";
        try (Connection conn = dbQuery.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("role"); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; 
    }
    
    public String getUsernameByEmail(String email) {
        String query = "SELECT username FROM Users WHERE email = ? ";
        try (Connection conn = dbQuery.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("username"); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; 
    }
    
    public int getUserIdByEmail(String email) {
        String query = "SELECT idUser FROM Users WHERE email = ?";
        try (Connection conn = dbQuery.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("idUser");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; 
    }
    
    public boolean deleteUserById(int idUser) {
        String query = "DELETE FROM Users WHERE idUser = ?";
        try (Connection conn = dbQuery.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idUser);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT idUser, username, email, role FROM Users";

        try (Connection conn = dbQuery.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("idUser"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
    
}

