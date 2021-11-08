import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/insta";
    private static final String USER = "root";
    private static final String PASSWORD = "RootRoot";
    public static void main(String[] args)  {
        Connection connection= null;
        try {
            connection= DriverManager.getConnection(URL,USER,PASSWORD);
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT * FROM Users");
            Service service= new Service(statement,connection);
//            service.save(new Contact(1,"Lisa","Gora","283933"));
//            service.save(new Contact(2,"Emma","Gora","283933"));
//            service.save(new Contact(3,"Rick","Gora","283933"));
//            service.save(new Contact(4,"Tori","Gora","283933"));
//            service.save(new Contact(5,"Ben","Gora","283933"));
//            service.save(new Contact(6,"Paul","Gora","283933"));
//            System.out.println(service.getContacts());

//            System.out.println(service.getContactById(1).getId());


            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
