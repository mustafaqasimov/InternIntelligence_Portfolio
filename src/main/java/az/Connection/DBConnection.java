package az.Connection;

import az.Connection.PRIVATEINFO.Secret;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connection = DriverManager.getConnection(Secret.getURL(),Secret.getUsername1(),Secret.getPassword1());
            System.out.println("Connection has succesfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void closeConnection(){
        try {
            connection.close();
            System.out.println("Connection has closed!!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clearconsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
