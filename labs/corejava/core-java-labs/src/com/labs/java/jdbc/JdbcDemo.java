package com.labs.java.jdbc;

import java.sql.*;

public class JdbcDemo {
    public static void main(String[] args) {
        Connection con = null;
        Statement st =null;
        ResultSet rs = null;

        // STEP 1: Load Driver (MySQL)
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Drive not loaded - " +e.getMessage());
            e.printStackTrace();
            return;
        }

        // STEP 2: Open Connection to DB

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lowes", "mysql", "mysql");
            System.out.println(con);
            System.out.println("connected to mysql");

            // STEP 3: Create Statement
            st = con.createStatement();
            String query = "select * from employee";

            // STEP 4: Execute Query
            rs = st.executeQuery(query);
            System.out.println("Query executed Successfully: " + query);

            // STEP 5: Get Resultset and access the results
            System.out.println("Got results - " + rs);
            System.out.println("EmpId \t Name \t Age \t Designation \t Department \t Country");
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String designation = rs.getString("designation");
                String department = rs.getString("department");
                String country = rs.getString("country");
                System.out.printf("%d \t %s \t %d \t %s \t %s \t %s \n", id, name, age, designation, department, country);
            }
        } catch(SQLException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Error while closing the connections. " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}







/*
public class JdbcDemo {
   static public Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/lowes";  //URL  for postgres
        //  "jdbc:mysql://localhost:3306/employee_db?useLegacyDatetimeCode=false&serverTimezone=UTC";  URL for mysql
        String userName = "mysql";
        String password = "mysql";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, userName, password);
        return connection;
    }


    public static void main(String[] args) {
        try {
            Connection connection = getConnection();
            System.out.println("connected to mysql");
            System.out.println("Connection Status: " + connection.isClosed());
            Statement st = connection.createStatement();
           ResultSet rs = st.executeQuery("select * from employee");
            System.out.println(rs.next());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }  finally {
            try {
                rs.close();

            }
        }
    }
}
*/




