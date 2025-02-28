package myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import myapp.model.SavedGrid;

public class SavedGridDao {
    private DatabaseDAO databaseDAO = new DatabaseDAO();

    public List<SavedGrid> getSavedGridsByUser(int idUser) {
        List<SavedGrid> savedGrids = new ArrayList<>();
        String query = "SELECT * FROM SavedGrids WHERE idUser = ?";

        try (Connection conn = databaseDAO.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idUser);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                SavedGrid savedGrid = new SavedGrid();
                savedGrid.setIdSGrid(rs.getInt("idSGrid"));
                savedGrid.setAnswersRows(rs.getString("answers_rows"));
                savedGrid.setAnswersColumns(rs.getString("answers_columns"));
                savedGrid.setIdUser(rs.getInt("idUser"));
                savedGrid.setIdFGrid(rs.getInt("idFGrid"));

                savedGrids.add(savedGrid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return savedGrids;
    }
}
