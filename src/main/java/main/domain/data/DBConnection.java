package main.domain.data;

import java.sql.*;

public class DBConnection {

    private static Connection con;

    // This method will attempt to establish the connection to the MySQL database
    public static Connection getDBConn() {
        if (con == null) {
            try {
                String url = "jdbc:mysql://192.168.56.1:3306/petshop"; // Change to your DB URL
                String user = "monty";  // Replace with your username
                String password = "some_pass";  // Replace with your password

                // Load MySQL driver (not needed in newer versions of JDBC)
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establish the connection
                con = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to the database successfully!");
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return con;
    }

    // General method to get data from any table
    public static void getDataFromTable(String tableName) {
        // Define the SQL query to select all rows from the given table
        String query = "SELECT * FROM " + tableName;

        try (Statement stmt = con.createStatement()) {
            // Execute the query
            ResultSet rs = stmt.executeQuery(query);

            // Get the metadata to dynamically process the columns
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();

            // Print column names (optional)
            System.out.println("Columns: ");
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rsMetaData.getColumnName(i) + "\t");
            }
            System.out.println();

            // Iterate over the result set and print data
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    // Dynamically get the column data based on column type
                    System.out.print(rs.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Fetch data from the 'accessories' table
    public static void getAccessoriesData() {
        getDataFromTable("accessories");
    }

    // Fetch data from the 'animals' table
    public static void getAnimalsData() {
        getDataFromTable("animals");
    }

    // Fetch data from the 'appointments' table
    public static void getAppointmentsData() {
        getDataFromTable("appointments");
    }

    // Fetch data from the 'birds' table
    public static void getBirdsData() {
        getDataFromTable("birds");
    }

    // Fetch data from the 'canineandfeline' table
    public static void getCanineAndFelineData() {
        getDataFromTable("canineandfeline");
    }

    // Fetch data from the 'clients' table
    public static void getClientsData() {
        getDataFromTable("clients");
    }

    // Fetch data from the 'enclosures' table
    public static void getEnclosuresData() {
        getDataFromTable("enclosures");
    }

    // Fetch data from the 'fish' table
    public static void getFishData() {
        getDataFromTable("fish");
    }

    // Fetch data from the 'food' table
    public static void getFoodData() {
        getDataFromTable("food");
    }

    // Fetch data from the 'items' table
    public static void getItemsData() {
        getDataFromTable("items");
    }

    // Fetch data from the 'pharmacy' table
    public static void getPharmacyData() {
        getDataFromTable("pharmacy");
    }

    // Fetch data from the 'reptiles' table
    public static void getReptilesData() {
        getDataFromTable("reptiles");
    }

    // Fetch data from the 'rodents' table
    public static void getRodentsData() {
        getDataFromTable("rodents");
    }

    // Fetch data from the 'toys' table
    public static void getToysData() {
        getDataFromTable("toys");
    }


    // Method to close the connection
    public static void closeConnection() {
        try {
            if (con != null && !con.isClosed()) {
                con.close();
                System.out.println("Connection closed successfully.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Test the connection
        Connection conn = getDBConn();

        if (conn != null) {
            System.out.println("Database connection is successful!");
            
            // Call the method to fetch data from the 'accessories' table
            getAccessoriesData();
            
            // You can call these methods to fetch data from other tables as well:
            // getAnimalsData();
            // getAppointmentsData();
            // getBirdsData();
            // getCanineAndFelineData();
            // getClientsData();
            // getEnclosuresData();
            // getFishData();
            // getFoodData();
            // getItemsData();
            // getPharmacyData();
            // getReptilesData();
            // getRodentsData();
            // getToysData();
        } else {
            System.out.println("Failed to make connection.");
        }

        // Close the connection when done
       // closeConnection();
    }
}
