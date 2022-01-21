import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MultiQueryDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Reading file
        Path filePath= Paths.get("/home/chamals-pc/Desktop/dbScript.sql");
        byte[] bytes= Files.readAllBytes(filePath);
        String dbScript= new String(bytes);
        //System.out.println(dpscript);

        String[] statements=dbScript.split(";");
        System.out.println(statements[2]);




        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Connecting to the database");

        try {
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "12345678");

            for (String  statement:statements) {
                if(!statement.trim().isEmpty()) {
                    Statement st = connection.createStatement();   //regular statements since DML/DDL both used
                    System.out.println("Executing"+statement);
                }
            }

            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
