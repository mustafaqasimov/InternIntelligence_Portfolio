package az.Service;

import az.Connection.DBConnection;
import az.Entities.Contact;
import az.Process;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ContactProcess implements Process<Contact> {

    private  static final Scanner scan = new Scanner(System.in);
    private Connection connection = DBConnection.getConnect();
    private static PreparedStatement stmt = null;
    private static ResultSet resultSet = null;

    @Override
    public void add(List<Contact> list) {
        String query = "INSERT INTO contactforms(name , email , message) VALUES(?,?,?)";

        try {
            stmt = connection.prepareStatement(query);

            for (Contact contact : list) {
                stmt.setString(1, contact.getName());
                stmt.setString(2, contact.getEmail());
                stmt.setString(3, contact.getMessage());

                stmt.addBatch();
            }
            stmt.executeBatch();
            System.out.println("Contacts established succesfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection();
        }
    }

    @Override
    public void getID() {
        System.out.print("Please Enter contact number:");
        int id = scan.nextInt();

        String query ="SELECT * FROM contactforms WHERE id=?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);
            resultSet = stmt.executeQuery();

            if(resultSet.next()) {
                int number = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                String message = resultSet.getString(4);

                System.out.println("id: "+ number + " Name: "+ name +" Email: "+ email+ " Message: "+ message);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnection.closeConnection();
        }
    }

    @Override
    public void uptade(Contact contact) {
        String query = "UPDATE contactforms SET name = ?, email = ?, message = ? WHERE id = ?";

        try {
            stmt = connection.prepareStatement(query);

            stmt.setString(1, contact.getName());
            stmt.setString(2, contact.getEmail());
            stmt.setString(3, contact.getMessage());
            stmt.setInt(4,contact.getId());

            stmt.executeUpdate();
            System.out.println("Contact uptaded succesfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnection.closeConnection();
        }
    }

    @Override
    public void delete() {
        System.out.print("Enter the contact to be deleted:");
        int id = scan.nextInt();

        String query = "DELETE from contactforms WHERE id =?";
        try {
            stmt = connection.prepareStatement(query);
            stmt.setInt(1,id);

            stmt.executeUpdate();
            System.out.println("contact has deleted!");
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

        String query = "SELECT * FROM contactforms WHERE name LIKE '"+letter+"%' ";
        try {
            stmt = connection.prepareStatement(query);
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString(3);
                String message = resultSet.getString(4);
                System.out.println("Contact ID: " + id +" Name: "+ name +" email: "+ email+ "Message: "+ message);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            DBConnection.closeConnection();
        }
    }
}
