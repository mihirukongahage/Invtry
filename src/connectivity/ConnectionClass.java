package connectivity;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
    public Connection connection;
    public Connection getConnection(){

        String dbName="invtry";
        String userName="root";
        String password="";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,userName,password);
        }catch(Exception e){
            e.printStackTrace();
        }

        return connection;
    }
}
