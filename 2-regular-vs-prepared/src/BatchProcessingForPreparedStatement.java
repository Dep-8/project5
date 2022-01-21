import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BatchProcessingForPreparedStatement {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dep8_hello", "root", "mysql");
        long t1 = System.currentTimeMillis();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO user (username, password) VALUES (?, ?)");

        for (int i = 0; i < 10000; i++) {
            stm.setString(1, "admin" + i + Math.random());
            stm.setString(2, "password");
           stm.addBatch();
        }

        int [] result=stm.executeBatch();
        for (int i = 0; i < result.length; i++) {
            System.out.println("Result"+(i+1)+":"+result[i]);
        }

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);

        connection.close();
    }
}
