package gall_bot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBWorker {
    private static final String URL = "jdbc:mysql://localhost:3306/gallows?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";

    private static final String USER_NAME = "root";
    private static final String PASSWORD = "marta1604";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }

}
