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



}
