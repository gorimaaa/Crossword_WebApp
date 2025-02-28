package myapp.model;

public class SavedGrid {
    private int idSGrid;
    private String answersRows;
    private String answersColumns;
    private int idUser;
    private int idFGrid;

    // Getters et Setters
    public int getIdSGrid() {
        return idSGrid;
    }

    public void setIdSGrid(int idSGrid) {
        this.idSGrid = idSGrid;
    }

    public String getAnswersRows() {
        return answersRows;
    }

    public void setAnswersRows(String answersRows) {
        this.answersRows = answersRows;
    }

    public String getAnswersColumns() {
        return answersColumns;
    }

    public void setAnswersColumns(String answersColumns) {
        this.answersColumns = answersColumns;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdFGrid() {
        return idFGrid;
    }

    public void setIdFGrid(int idFGrid) {
        this.idFGrid = idFGrid;
    }
}
