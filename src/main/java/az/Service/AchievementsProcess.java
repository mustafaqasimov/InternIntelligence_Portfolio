package az.Service;

import az.Connection.DBConnection;
import az.Entities.Achievement;
import az.Process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AchievementsProcess implements Process<Achievement> {

    private  static final Scanner scan = new Scanner(System.in);
    private Connection connection = DBConnection.getConnect();
    private static PreparedStatement stmt = null;
    private static ResultSet resultSet = null;

    @Override
    public void add(List<Achievement> list) {
        String query = "INSERT INTO achievements(title, description) VALUES(?,?)";

        try {
            stmt = connection.prepareStatement(query);

            for (Achievement achievement: list){
                stmt.setString(1, achievement.getTitle());
                stmt.setString(2, achievement.getDescription());

                stmt.addBatch();
            }
            stmt.executeBatch();
            System.out.println("Achievement inserted succesfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnection.closeConnection();
        }
    }

    @Override
    public void getID() {
        System.out.print("Enter a achievement id: ");
       int id = scan.nextInt();

        String query = "SELECT * FROM achievements WHERE id=?";
        try {
            stmt = connection.prepareStatement(query);

            stmt.setInt(1,id);
            resultSet = stmt.executeQuery();
            if(resultSet.next()) {
                int number = resultSet.getInt(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);

                System.out.println("ID: "+ number + " Title: "+title + " Description: "+ description);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnection.closeConnection();
        }
    }

    @Override
    public void uptade(Achievement achievement) {
        String query = "UPDATE achievements SET title = ?,description = ? WHERE id = ?";

        try {
            stmt = connection.prepareStatement(query);

            stmt.setString(1, achievement.getTitle());
            stmt.setString(2,achievement.getDescription());

            stmt.setInt(3,achievement.getId());
            stmt.executeUpdate();
            System.out.println("Achievements uptaded succesfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnection.closeConnection();
        }
    }

    @Override
    public void delete() {
        System.out.print("Enter the Achievement id:");
        int id = scan.nextInt();
        String query = "DELETE FROM achievements WHERE id=?";

        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            stmt.executeUpdate();
            System.out.println("Projects has deleted!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnection.closeConnection();
        }
    }

    @Override
    public void findbyname() {
        System.out.println("Enter a letter:");
        String letter = scan.next();
        String query = "SELECT * FROM achievements WHERE title LIKE '"+letter+"%' ";

        try {
            stmt = connection.prepareStatement(query);

            while (resultSet.next()){
               int number = resultSet.getInt(1);
               String title = resultSet.getString(2);
               String description = resultSet.getString(3);
               System.out.println("Achievements ID: "+ number + " Title: "+ title + " Description: "+ description);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnection.closeConnection();
        }
    }
}
