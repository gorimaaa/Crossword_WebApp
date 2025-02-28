package myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import myapp.model.Grid;

public class GridDao {
    private DatabaseDAO databaseDAO = new DatabaseDAO();

    public List<Grid> getAvailableGrids() {
        List<Grid> grids = new ArrayList<>();
        String query = "SELECT idFGrid, name, difficulty, created_at FROM FinishedGrids";

        try (Connection conn = databaseDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Grid grid = new Grid();
                grid.setId(rs.getInt("idFGrid"));
                grid.setName(rs.getString("name"));
                grid.setDifficulty(rs.getString("difficulty"));
                grid.setCreatedAt(rs.getTimestamp("created_at"));
                grids.add(grid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return grids;
    }
    
    public Grid getGridById(int id) {
        String query = "SELECT idFGrid, name, difficulty, created_at FROM FinishedGrids WHERE idFGrid = ?";
        Grid grid = null;

        try (Connection conn = databaseDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                grid = new Grid();
                grid.setId(rs.getInt("idFGrid"));
                grid.setName(rs.getString("name"));
                grid.setDifficulty(rs.getString("difficulty"));
                grid.setCreatedAt(rs.getTimestamp("created_at"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return grid;
    }
    
    public boolean deleteGridById(int id) {
        String query = "DELETE FROM FinishedGrids WHERE idFGrid = ?";
        try (Connection conn = databaseDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Grid> getGridsSortedBy(String sortOption) {
        List<Grid> grids = new ArrayList<>();
        String query = "SELECT idFGrid, name, difficulty, created_at FROM FinishedGrids";

        if ("difficulty".equals(sortOption)) {
            query += " ORDER BY difficulty";
        } else {
            query += " ORDER BY created_at DESC"; 
        }

        try (Connection conn = databaseDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Grid grid = new Grid();
                grid.setId(rs.getInt("idFGrid"));
                grid.setName(rs.getString("name"));
                grid.setDifficulty(rs.getString("difficulty"));
                grid.setCreatedAt(rs.getTimestamp("created_at"));
                grids.add(grid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return grids;
    }
    
    public boolean insertFinishedGrid(int dimX, int dimY, String name, String difficulty, String solutionsRows, String solutionsColumns, String hintsRows, String hintsColumns, int userId) {
        int idFGrid = getLastIdFGrid();
    	String query = "INSERT INTO FinishedGrids (dim_x, dim_y, name, difficulty, created_at, solutions_rows, solutions_columns, hints_rows, hints_columns, idUser, idFGrid) "
                     + "VALUES (?, ?, ?, ?, NOW(), ?, ?, ?, ?, ?, ?)";
        try (Connection conn = databaseDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setInt(1, dimX);
            stmt.setInt(2, dimY);
            stmt.setString(3, name);
            stmt.setString(4, difficulty);
            stmt.setString(5, solutionsRows);
            stmt.setString(6, solutionsColumns);
            stmt.setString(7, hintsRows);
            stmt.setString(8, hintsColumns);
            stmt.setInt(9, userId);
            stmt.setInt(10, idFGrid + 1);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Retourne true si l'insertion a réussi
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retourne false en cas d'erreur
        }
    }
    
    
    public int getLastIdFGrid() {
        String query = "SELECT MAX(idFGrid) AS lastId FROM FinishedGrids";
        int lastId = -1; // Valeur par défaut si aucune grille n'existe

        try (Connection conn = databaseDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                lastId = rs.getInt("lastId");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastId;
    }
    
    public Grid getGridInformationForSolving(int id) {
        String query = "SELECT idFGrid, dim_x, dim_y, name, difficulty, solutions_rows, solutions_columns, hints_rows, hints_columns, idUser FROM FinishedGrids WHERE idFGrid = ?";
        Grid grid = null;

        try (Connection conn = databaseDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                grid = new Grid();
                grid.setId(rs.getInt("idFGrid"));
                grid.setDimX(rs.getInt("dim_x"));
                grid.setDimY(rs.getInt("dim_y"));
                grid.setName(rs.getString("name"));
                grid.setDifficulty(rs.getString("difficulty"));
                grid.setSolutionsRows(rs.getString("solutions_rows"));
                grid.setSolutionsColumns(rs.getString("solutions_columns"));
                grid.setHintsRows(rs.getString("hints_rows"));
                grid.setHintsColumns(rs.getString("hints_columns"));
                grid.setIdUser(rs.getInt("idUser"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return grid;
    }

    



}
