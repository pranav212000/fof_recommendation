package com.example.fof_recommendation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "sudeep";
    private String BASE_URL = "jdbc:mysql://localhost/";
    private String DB_URL;
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;

    public void useDatabase(String database) throws SQLException {
        DB_URL = BASE_URL + database;

//    	Register JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//                        STEP 3: Open a connection
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Connected database successfully...");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }



    public User addUser(String name, String lastname, String address, String userContact) throws SQLException {
        //STEP 4: Execute a query
        System.out.println("Inserting records into the table...");
        statement = connection.createStatement();

        String sql = "INSERT INTO users (name, lastname, address, usercontact) " +
                "VALUES (?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, name);
        preparedStatement.setString(2, lastname);
        preparedStatement.setString(3, address);
        preparedStatement.setString(4, userContact);

        preparedStatement.executeUpdate();

//        ResultSet resultSet = preparedStatement.getResultSet();
//        System.out.println(resultSet.toString());
//
        String userId = "";
//        while(resultSet != null && resultSet.next()) {
//            userId = String.valueOf(resultSet.getInt(1));
//            System.out.println("result set : " + resultSet.toString());
//        }


        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        if(resultSet != null && resultSet.next()){
            userId = String.valueOf(resultSet.getInt(1));
        }


        System.out.println("IN JDBC");
        System.out.println(userId);

        return new User(userId, name, lastname, address, userContact);

    }


//
//    public static void main(String[] args) {
//        Connection conn = null;
//        Statement stmt = null;
//        try{
//            //STEP 2: Register JDBC driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            //STEP 3: Open a connection
//            System.out.println("Connecting to a selected database...");
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            System.out.println("Connected database successfully...");
//
//            //STEP 4: Execute a query
//            System.out.println("Creating statement...");
//            stmt = conn.createStatement();
//
//            String sql = "SELECT * FROM city WHERE district = 'Maharashtra'";
//            ResultSet rs = stmt.executeQuery(sql);
//            ResultSetMetaData rsmd = rs.getMetaData();
//            int columnsNumber = rsmd.getColumnCount();
//            while (rs.next()) {
//                for (int i = 1; i <= columnsNumber; i++) {
//                    if (i > 1) System.out.print(",  ");
//                    String columnValue = rs.getString(i);
//                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
//                }
//                System.out.println("");
//            }
//
//
//            //STEP 5: Extract data from result set
////            while(rs.next()){
////                //Retrieve by column name
////                int id  = rs.getInt("id");
////                int age = rs.getInt("age");
////                String first = rs.getString("first");
////                String last = rs.getString("last");
////
////                //Display values
////                System.out.print("ID: " + id);
////                System.out.print(", Age: " + age);
////                System.out.print(", First: " + first);
////                System.out.println(", Last: " + last);
////            }
//
//
//            rs.close();
//        }catch(SQLException se){
//            //Handle errors for JDBC
//            se.printStackTrace();
//        }catch(Exception e){
//            //Handle errors for Class.forName
//            e.printStackTrace();
//        }finally{
//            //finally block used to close resources
//            try{
//                if(stmt!=null)
//                    conn.close();
//            }catch(SQLException se){
//            }// do nothing
//            try{
//                if(conn!=null)
//                    conn.close();
//            }catch(SQLException se){
//                se.printStackTrace();
//            }//end finally try
//        }//end try
//        System.out.println("Goodbye!");
//    }//end main
}
