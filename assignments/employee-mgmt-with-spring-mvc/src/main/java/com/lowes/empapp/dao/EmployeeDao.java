package com.lowes.empapp.dao;

import com.lowes.empapp.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDao {

    @Autowired
    DataSource datasource;

    Connection con = null;
    Statement st = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public EmployeeDao(DataSource datasource) {
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


    public boolean addEmployee(Employee employee) {  // add employee
        // Insertion using Prepared Statement
        try {
            con = datasource.getConnection();
            String sql = "INSERT INTO employee_new" +
                    "(name, age, gender, contractor, skills, designation," +
                    "department, address, country) " +
                    "VALUES (?,?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, employee.getName());
            pstmt.setInt(2, employee.getAge());
            pstmt.setString(3, employee.getGender());
            pstmt.setString(4, String.valueOf(employee.isContractor()));

            List<String> skills = employee.getSkills();
            String skillsList = skills.stream()
                    .collect(Collectors.joining(","));

            pstmt.setString(5, skillsList);
            pstmt.setString(6, employee.getDesignation());
            pstmt.setString(7, employee.getDepartment());
            pstmt.setString(8, employee.getAddress());
            pstmt.setString(9, employee.getCountry());
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
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


    public boolean update(Employee emp) {  // update employee
        // Updation with Prepared Statement
        try {
            con = datasource.getConnection();
            String updateQuery = "UPDATE employee_new SET id = ? , name = ? , age = ? , gender = ? , contractor = ? " +
                    " ,skills = ? , designation = ? , department = ? , address = ? " +
                    " , country = ?  WHERE id = ?";
            pstmt = con.prepareStatement(updateQuery);
            pstmt.setString(1, emp.getId());
            pstmt.setString(2, emp.getName());
            pstmt.setInt(3, emp.getAge());
            pstmt.setString(4, emp.getGender());
            pstmt.setString(5, String.valueOf(emp.isContractor()));

            List<String> skills = emp.getSkills();
            String skillsList = skills.stream()
                    .collect(Collectors.joining(","));

            pstmt.setString(6, skillsList);
            pstmt.setString(7, emp.getDesignation());
            pstmt.setString(8, emp.getDepartment());
            pstmt.setString(9, emp.getAddress());
            pstmt.setString(10, emp.getCountry());
            pstmt.setString(11, emp.getId());

            int updateCount = pstmt.executeUpdate();
            if (updateCount > 0) {
                System.out.println(updateCount + " records updated ");
            }
        } catch (Exception e) {
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


    public boolean delete(String empId) {   // delete employee
        // Deletion with Prepared Statement
        try {
            con = datasource.getConnection();
            String deleteQuery = "DELETE FROM employee_new WHERE id = ?";
            pstmt = con.prepareStatement(deleteQuery);
            pstmt.setString(1, empId);
            int deleteCount = pstmt.executeUpdate();
            if (deleteCount > 0) {
                System.out.println(deleteCount + " Employee(s) deleted");
            }

        } catch (Exception e) {
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



    public List<Employee> getAll() {   // get all employees
        List<Employee> employeeList = new ArrayList<>();
        try {
            con = datasource.getConnection();
            st = con.createStatement();
            String query = "select * from employee_new";
            rs = st.executeQuery(query);
            System.out.println("Query executed Successfully: " + query);
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getString("id"));
                emp.setName(rs.getString("name"));
                emp.setAge(rs.getInt("age"));
                emp.setGender(rs.getString("gender"));
                emp.setContractor(rs.getBoolean("contractor"));
                emp.setSkills(Collections.singletonList(rs.getString("skills")));
                emp.setDesignation(rs.getString("designation"));
                emp.setDepartment(rs.getString("department"));
                emp.setAddress(rs.getString("address"));
                emp.setCountry(rs.getString("country"));
                employeeList.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (st != null) {
                    st.close();
                }
                closeConnection(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return employeeList;
    }


    public Employee get(String empId)  {   // get employee
        Employee emp = null;
        try {
            con = datasource.getConnection();
            String query = "SELECT * FROM employee_new WHERE id = ? ";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);
           // System.out.println("emp id is : " + empId);
            rs = pstmt.executeQuery();
            System.out.println("Query executed Successfully: " + query);
            while (rs.next()) {
                emp = new Employee();
                emp.setId(rs.getString("id"));
                emp.setName(rs.getString("name"));
                emp.setAge(rs.getInt("age"));
                emp.setGender(rs.getString("gender"));
                emp.setContractor(rs.getBoolean("contractor"));
                emp.setSkills(Collections.singletonList(rs.getString("skills")));
                emp.setDesignation(rs.getString("designation"));
                emp.setDepartment(rs.getString("department"));
                emp.setAddress(rs.getString("address"));
                emp.setCountry(rs.getString("country"));
                System.out.println("employee details: " + emp.getId() + emp.getSkills());

            }

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
        return emp;
    }
}
