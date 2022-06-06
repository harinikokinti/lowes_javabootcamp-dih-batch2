package com.lowes.empapp.dao;

import com.lowes.empapp.model.User;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import java.sql.*;


public class UserDao extends MysqlDataSource {

    @Autowired
    DataSource datasource;
    Connection con = null;
    Statement st = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public UserDao(DataSource datasource) {
        this.datasource = datasource;
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean addUser(User user) {  // insert employee
        // Insertion using Prepared Statement
        try {
            con = datasource.getConnection();
            String sql = "INSERT INTO user(first_name, last_name, email, username, password) " +
                    "VALUES (?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUsername());
            pstmt.setString(5, user.getPassword());
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                pstmt.close();
                closeConnection(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Get Login Credentials (username & password )
    public boolean validateLogin(User user)  {
        try {
            con = datasource.getConnection();
            String query = "SELECT username,password FROM user WHERE username = ? and  password = ? ";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            rs = pstmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pstmt.close();
                closeConnection(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
