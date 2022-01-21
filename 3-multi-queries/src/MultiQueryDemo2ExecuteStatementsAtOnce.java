import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MultiQueryDemo2ExecuteStatementsAtOnce {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        //Reading file
        Path filePath= Paths.get("/home/chamals-pc/Desktop/dbScript.sql");
        byte[] bytes= Files.readAllBytes(filePath);
        String dbScript= new String(bytes);
        //System.out.println(dpscript);



        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Connecting to the database");

        try {
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306?allowMultiQueries=true", "root", "12345678");
            Statement st=connection.createStatement();
            st.execute(dbScript);



            connection.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
