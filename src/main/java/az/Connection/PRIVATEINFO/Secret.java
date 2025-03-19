package az.Connection.PRIVATEINFO;

public class Secret {
    private static String URL = "jdbc:mysql://localhost:3306/portfolio";
    private static String username1 = "root";
    private static String password1= "24072007M";

    public static String getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        Secret.URL = URL;
    }

    public static String getUsername1() {
        return username1;
    }

    public static void setUsername1(String username1) {
        Secret.username1 = username1;
    }

    public static String getPassword1() {
        return password1;
    }

    public static void setPassword1(String password1) {
        Secret.password1 = password1;
    }
}
