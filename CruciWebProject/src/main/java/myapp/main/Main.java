package myapp.main;

import myapp.dao.DatabaseDAO;
import myapp.dao.GridDao;

import java.sql.Connection;
import java.util.List;

import myapp.dao.UserDao;
import myapp.model.Grid;

public class Main {
    public static void main(String[] args) {
        GridDao gridDao = new GridDao();

        List<Grid> grids = gridDao.getAvailableGrids();
        if (grids.isEmpty()) {
            System.out.println("Aucune grille trouvée !");
        } else {
            grids.forEach(grid -> {
                System.out.println("ID : " + grid.getId());
                System.out.println("Nom : " + grid.getName());
                System.out.println("Difficulté : " + grid.getDifficulty());
                System.out.println("Créée le : " + grid.getCreatedAt());
                System.out.println("----------------------------");
            });
        }
    }
}
