package az.Service;

import az.Connection.DBConnection;
import az.Entities.Skills;
import az.Process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SkillsProcess implements Process<Skills> {

    private  static final Scanner scan = new Scanner(System.in);
    private Connection connection = DBConnection.getConnect();
    private static PreparedStatement stmt = null;
    private static ResultSet resultSet = null;

    @Override
    public void add(List<Skills> list) {
        String query = "INSERT INTO skills(skill_name) VALUES(?)";

        try {
            stmt = connection.prepareStatement(query);

            for (Skills skills : list) {
                stmt.setString(1,skills.getSkill_name());

                stmt.addBatch();
            }
            stmt.executeBatch();
            System.out.println("Skills INSERTED succesfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            DBConnection.closeConnection();
        }
    }

    @Override
    public void getID() {
        System.out.print("Please Enter Skills number:");
        int id = scan.nextInt();

        String query ="SELECT * FROM skills WHERE id=?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            resultSet = stmt.executeQuery();

            if(resultSet.next()) {
                int number = resultSet.getInt(1);
                String skillsname = resultSet.getString(2);

                System.out.println("id: "+ number + " Name: "+ skillsname);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnection.closeConnection();
        }
    }

    @Override
    public void uptade(Skills skills) {
        String query = "UPDATE skills SET skill_name = ? WHERE id = ?";

        try {
            stmt = connection.prepareStatement(query);

            stmt.setInt(1,skills.getId());
            stmt.setString(2, skills.getSkill_name());

            stmt.executeUpdate();
            System.out.println("Skills uptaded succesfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnection.closeConnection();
        }
    }

    @Override
    public void delete() {
        System.out.print("Enter the Skills to be deleted:");
        int id = scan.nextInt();

        String query = "DELETE from skills WHERE id =?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);

            stmt.executeUpdate();
            System.out.println("Skills has deleted!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnection.closeConnection();
        }
    }

    @Override
    public void findbyname() {
        System.out.print("Enter a letter: ");
        String letter = scan.next();

        String query = "SELECT * FROM skills WHERE skill_name LIKE '"+letter+"%' ";
        try {
            stmt = connection.prepareStatement(query);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int skillsid = resultSet.getInt(1);
                String skillsname = resultSet.getString(2);

                System.out.println("Skills ID: " + skillsid+" Name: "+ skillsname);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnection.closeConnection();
        }
    }
}

