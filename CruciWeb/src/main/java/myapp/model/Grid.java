package myapp.model;

import java.sql.Timestamp;
import java.sql.Date;

public class Grid {
    private int id; // Correspond à idFGrid
    private String name;
    private String difficulty;
    private Timestamp createdAt; // Correspond à created_at

    // Nouveaux champs
    private int dimX;
    private int dimY;
    private String solutionsRows;
    private String solutionsColumns;
    private String hintsRows;
    private String hintsColumns;
    private int idUser;

    // Getters et Setters pour les anciens champs
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    // Getters et Setters pour les nouveaux champs
    public int getDimX() {
        return dimX;
    }

    public void setDimX(int dimX) {
        this.dimX = dimX;
    }

    public int getDimY() {
        return dimY;
    }

    public void setDimY(int dimY) {
        this.dimY = dimY;
    }

    public String getSolutionsRows() {
        return solutionsRows;
    }

    public void setSolutionsRows(String solutionsRows) {
        this.solutionsRows = solutionsRows;
    }

    public String getSolutionsColumns() {
        return solutionsColumns;
    }

    public void setSolutionsColumns(String solutionsColumns) {
        this.solutionsColumns = solutionsColumns;
    }

    public String getHintsRows() {
        return hintsRows;
    }

    public void setHintsRows(String hintsRows) {
        this.hintsRows = hintsRows;
    }

    public String getHintsColumns() {
        return hintsColumns;
    }

    public void setHintsColumns(String hintsColumns) {
        this.hintsColumns = hintsColumns;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
