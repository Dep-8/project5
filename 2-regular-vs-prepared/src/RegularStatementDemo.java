import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RegularStatementDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dep8_hello", "root", "mysql");

        long t1 = System.currentTimeMillis();
        Statement stm = connection.createStatement();

        for (int i = 0; i < 10000; i++) {
            String sql = String.format("INSERT INTO user (username, password) VALUES ('%s', '%s')",
                    "admin" + i + Math.random(), "password");
            stm.executeUpdate(sql);
        }

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);

        connection.close();
    }
}

