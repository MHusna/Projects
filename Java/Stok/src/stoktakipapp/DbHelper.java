package stoktakipapp;
import java.sql.*;

/**
 *
 * @author mehme
 */
public class DbHelper {
    
    private String userName = "root";
    private String password = "";
    private String dbUrl = "jdbc:mysql://localhost:3306/stok_db";
    
    
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(dbUrl,userName,password);
    }
    
    public void showErrorMessage(SQLException exception){
        System.out.println("Error: "+exception.getMessage());
        System.out.println("Error Code: "+exception.getErrorCode());
    }
}
