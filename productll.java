import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class productll {
    private static final String url = "jdbc:mysql://localhost:3306/zom";
    private static final String username = "root";
    private static final String password = "9027707502";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        Connection connection = DriverManager.getConnection(url, username, password);

        //  retrive(connection);
        // insertdata(connection);
        // deleteData(connection);
        while(true){
            showmwnu();
            int choice = scanner.nextInt();

            switch(choice){
                case 1 : insertdata(connection);
                break;
                
                case 2 : deleteData(connection);
                break;
               
                case 3 : retrive(connection);
                break;
              
                default : 
                System.out.println("Invalid choice");
                break;
            
            }
            System.out.println();
    
        }
        

    }

    public static void retrive(Connection connection) throws SQLException{
       try{ String query = "SELECT * FROM products";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            System.out.println("Id: "+id);
            System.out.println("Name: "+name);
            System.out.println("Price: "+price);
        }
    }finally{
        System.out.println("Data retrived");
     }
    }

    public static void insertdata(Connection connection) throws SQLException{
         String query = "INSERT INTO products(name, price)VALUES(?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Enter name: ");
            String name = scanner.next();
            System.out.println("Enter price: ");
            double price = scanner.nextDouble();
            System.out.println("Enter choice Y/N");
            String choice = scanner.next();

            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, price);
            System.out.println("Succesfully added");

            preparedStatement.addBatch();
            if(choice.toUpperCase().equals("N")) {
                break;
            }
        }
        int arr[] = preparedStatement.executeBatch();
        preparedStatement.close();
        scanner.close();
        connection.close();
    }
    public static void deleteData(Connection connection)throws SQLException{
        String query = "DELETE FROM products WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        Scanner scanner = new Scanner(System.in);

        try{
            System.out.println("Enter id: ");
            int id = scanner.nextInt();
            preparedStatement.setInt(1, id);

        }
        finally{
            System.out.println("Delete succesfully");
        }
        scanner.close();

    }

    public static void showmwnu(){
        System.out.println("Database zom, Table products");
        System.out.println("1.Insert data: ");
        System.out.println("2.Delete data: ");
        System.out.println("3.show data: ");
       
    }
    public static void exit(String choice){
            if(choice.toUpperCase().equals("N")){
                
            }
    }
}
