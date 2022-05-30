package com.empapp.dao;

import com.empapp.exception.EmployeeNotFoundException;
import com.empapp.model.Employee;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoJdbcImpl implements EmployeeDao {

    Connection con = null;
    Statement st = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/lowes";  //URL  for mysql
        String root = "mysql";
        String password = "mysql";
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(url, root, password);
        return con;
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

    @Override
    public boolean create(Employee emp) {  // insert employee
        // Insertion using Prepared Statement
        try {
            con = getConnection();
            String sql = "INSERT INTO employee(name, age, designation, department, country, salary, doj, created_time) " +
                    "VALUES (?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, emp.getName());
            pstmt.setInt(2, emp.getAge());
            pstmt.setString(3, emp.getDesignation());
            pstmt.setString(4, emp.getDepartment());
            pstmt.setString(5, emp.getCountry());
            pstmt.setDouble(6, emp.getSalary());
            pstmt.setDate(7, Date.valueOf(emp.getDoj()));  // convert LocalDate to Date
            pstmt.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now())); // convert LocalDateTime to Timestamp
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException | ClassNotFoundException e) {
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

    @Override
    public boolean update(Employee emp) {
        // Updation with Prepared Statement
        try {
            con = getConnection();
            String updateQuery = "UPDATE employee SET id = ? , name = ? , age = ? , designation = ? , department = ?" +
                    " , country = ? , salary = ? , doj = ? , modified_time = ?  WHERE id = ?";
            pstmt = con.prepareStatement(updateQuery);
            pstmt.setInt(1, emp.getEmpId());
            pstmt.setString(2, emp.getName());
            pstmt.setInt(3, emp.getAge());
            pstmt.setString(4, emp.getDesignation());
            pstmt.setString(5, emp.getDepartment());
            pstmt.setString(6, emp.getCountry());
            pstmt.setDouble(7, emp.getSalary());
            pstmt.setDate(8, Date.valueOf(emp.getDoj()));  // convert LocalDate to Date
            //pstmt.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now())); // convert LocalDateTime to Timestamp   , no need to set
            pstmt.setTimestamp(9, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.setInt(10, emp.getEmpId());

            int updateCount = pstmt.executeUpdate();
            if (updateCount > 0) {
                System.out.println(updateCount + " records updated ");
            } else {
                throw new EmployeeNotFoundException("Sorry Employee was not found in the database");
            }

        } catch (SQLException | ClassNotFoundException e) {
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

    @Override
    public boolean delete(int empId) {
        // Deletion with Prepared Statement
        try {
            con = getConnection();
            String deleteQuery = "DELETE FROM employee WHERE id = ?";
            pstmt = con.prepareStatement(deleteQuery);
            pstmt.setInt(1, empId);
            int deleteCount = pstmt.executeUpdate();
            if (deleteCount > 0) {
                System.out.println(deleteCount + " Employee(s) deleted");
            } else {
                throw new EmployeeNotFoundException("Sorry Employee was not found");
            }

        } catch (SQLException | ClassNotFoundException e) {
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

    @Override
    public Employee get(int empId) throws EmployeeNotFoundException {
        Employee emp = null;
        try {
            con = getConnection();
            String query = "SELECT id,name,age,designation,department,country,salary,doj,created_time,modified_time FROM employee WHERE id = ? ";
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, empId);
            rs = pstmt.executeQuery();
            System.out.println("Query executed Successfully: " + query);
            while (rs.next()) {
                emp = new Employee();
                emp.setEmpId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setAge(rs.getInt("age"));
                emp.setDesignation(rs.getString("designation"));
                emp.setDepartment(rs.getString("department"));
                emp.setCountry(rs.getString("country"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setDoj(rs.getDate("doj").toLocalDate());
                emp.setCreatedTime(rs.getTimestamp("created_time").toLocalDateTime());

                Timestamp modifiedTime = rs.getTimestamp("modified_time");
                if (modifiedTime != null) {
                    emp.setModifiedTime(modifiedTime.toLocalDateTime());
                }
            }
            if (emp == null) {
                throw new EmployeeNotFoundException("Sorry Employee was not found in the database");
            }

        } catch (SQLException | ClassNotFoundException e) {
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
        return emp;
    }

    @Override
    public List<Employee> getAll() {
        // get all employees
        List<Employee> employeeList = new ArrayList<>();
        try {
            con = getConnection();
            st = con.createStatement();
            String query = "select * from employee";
            rs = st.executeQuery(query);
            System.out.println("Query executed Successfully: " + query);
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setEmpId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setAge(rs.getInt("age"));
                emp.setDesignation(rs.getString("designation"));
                emp.setDepartment(rs.getString("department"));
                emp.setCountry(rs.getString("country"));
                emp.setSalary(rs.getDouble("salary"));

                emp.setDoj(rs.getDate("doj").toLocalDate());
                emp.setCreatedTime(rs.getTimestamp("created_time").toLocalDateTime());

                Timestamp modifiedTime = rs.getTimestamp("modified_time");
                if (modifiedTime != null) {
                    emp.setModifiedTime(modifiedTime.toLocalDateTime());
                }
                employeeList.add(emp);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                closeConnection(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employeeList;
    }
}
