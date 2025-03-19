package az.Service;

import az.Connection.DBConnection;
import az.Entities.Projects;
import az.Process;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class ProjectsProcess implements Process<Projects> {

    private  static final Scanner scan = new Scanner(System.in);
    private Connection connection = DBConnection.getConnect();
    private static PreparedStatement stmt = null;
    private static ResultSet resultSet = null;


    @Override
    public void add(List<Projects> list) {
        String query = "INSERT INTO projects(project_name , title) VALUES(?,?)";

        try {
            stmt = connection.prepareStatement(query);

            for (Projects projects : list) {
                stmt.setString(1, projects.getProject_name());
                stmt.setString(2, projects.getTitle());

                stmt.addBatch();
            }
            stmt.executeBatch();
            System.out.println("Projects Inserted succesfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection();
        }
    }

    @Override
    public void getID() {
        System.out.print("Please Enter Projects number:");
        int id = scan.nextInt();

        String query ="SELECT * FROM projects WHERE id=?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            resultSet = stmt.executeQuery();

            if(resultSet.next()) {
                int number = resultSet.getInt(1);
                String projectsname = resultSet.getString(2);
                String title = resultSet.getString(3);

                System.out.println("id: "+ number + " Name: "+ projectsname +" Title: "+ title);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnection.closeConnection();
        }
    }

    @Override
    public void uptade(Projects projects) {
        String query = "UPDATE projects SET project_name = ?,title = ? WHERE id = ?";

        try {
            stmt = connection.prepareStatement(query);


            stmt.setString(1, projects.getProject_name());
            stmt.setString(2, projects.getTitle());
            stmt.setInt(3,projects.getId());

            stmt.executeUpdate();
            System.out.println("Projects uptaded succesfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnection.closeConnection();
        }
    }

    @Override
    public void delete() {
        System.out.print("Enter the projects to be deleted:");
        int id = scan.nextInt();

        String query = "DELETE from projects WHERE id =?";
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
        System.out.print("Enter a letter: ");
        String letter = scan.next();

        String query = "SELECT * FROM projects WHERE project_name LIKE '"+letter+"%' ";
        try {
            stmt = connection.prepareStatement(query);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int projectsid = resultSet.getInt(1);
                String projectsname = resultSet.getString(2);
                String title = resultSet.getString(3);
                System.out.println("Projects ID: " + projectsid+" Name: "+ projectsname +" Title: "+ title);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnection.closeConnection();
        }
    }
}

