package myapp.main;

import myapp.dao.DatabaseDAO;

public class Main {
    public static void main(String[] args) {
        // Instanciation de la classe DatabaseDAO
        DatabaseDAO dao = new DatabaseDAO();

        // Appel de la méthode executeQuery
        dao.executeQuery();
    }
}
