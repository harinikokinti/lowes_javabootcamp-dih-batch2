package com.lowes.empapp.dao;

import com.lowes.empapp.exception.DatabaseException;
import com.lowes.empapp.exception.EmployeeException;
import com.lowes.empapp.model.Employee;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    Logger logger = LoggerFactory.getLogger(EmployeeDaoImpl.class);

    @Autowired
    DataSource datasource;
    Connection con = null;
    Statement st = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public EmployeeDaoImpl(DataSource datasource) {
        this.datasource = datasource;
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Logging SQLException ", e);
            }
        }
    }

    @Override
    public Employee create(Employee emp) {  // insert employee
        // Insertion using Prepared Statement
        try {
            con = datasource.getConnection();
            //   logger.info("entered dao create ");
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
            pstmt.executeUpdate();

        } catch (SQLException e) {
            logger.error("Logging SQLException ", e);
            throw new DatabaseException("Database Error, check the connection or the query", e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                closeConnection(con);
            } catch (SQLException e) {
                logger.error("Logging SQLException ", e);
            }
        }
        return emp;
    }

    @Override
    public Employee update(Employee emp) {  // Updation with Prepared Statement
        try {
            con = datasource.getConnection();
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
                throw new EmployeeException("Sorry Employee was not found in the database");
            }
        } catch (SQLException e) {
            logger.error("Logging SQLException ", e);
            throw new DatabaseException("Database Error, check the connection or the query", e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                closeConnection(con);
            } catch (SQLException e) {
                logger.error("Logging SQLException ", e);
            }
        }
        return emp;
    }

    @Override
    public boolean delete(int empId) {  // Deletion with Prepared Statement
        try {
            con = datasource.getConnection();
            String deleteQuery = "DELETE FROM employee WHERE id = ?";
            pstmt = con.prepareStatement(deleteQuery);
            pstmt.setInt(1, empId);
            int deleteCount = pstmt.executeUpdate();
            if (deleteCount > 0) {
                System.out.println(deleteCount + " Employee(s) deleted");
            } else {
                throw new EmployeeException("Sorry Employee was not found in the database");
            }
        } catch (SQLException e) {
            logger.error("Logging SQLException ", e);
            throw new DatabaseException("Database Error, check the connection or the query", e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                closeConnection(con);
            } catch (SQLException e) {
                logger.error("Logging SQLException ", e);
            }

        }
        return false;
    }

    @Override
    public Employee get(int empId) throws EmployeeException {  // get employee
        Employee emp = null;
        try {
            con = datasource.getConnection();
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
                throw new EmployeeException("Sorry Employee was not found in the database");
            }
        } catch (SQLException e) {
            logger.error("Logging SQLException ", e);
            throw new DatabaseException("Database Error, check the connection or the query", e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                closeConnection(con);
            } catch (SQLException e) {
                logger.error("Logging SQLException ", e);
            }
        }
        return emp;
    }

    @Override
    public List<Employee> getAll() {   // get all Employees
        // get all employees
        List<Employee> employeeList = new ArrayList<>();
        try {
            con = datasource.getConnection();
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

            if (employeeList.isEmpty()) {
                throw new EmployeeException("Sorry no employees found in the database");
            }
        } catch (SQLException e) {
            logger.error("Logging SQLException ", e);
            throw new DatabaseException("Database Error, check the connection or the query", e);
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
                logger.error("Logging SQLException ", e);
            }
        }
        return employeeList;
    }
}