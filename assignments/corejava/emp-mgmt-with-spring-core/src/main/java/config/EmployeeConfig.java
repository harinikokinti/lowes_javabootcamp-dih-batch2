package config;

import com.mysql.cj.jdbc.MysqlDataSource;
import dao.EmployeeDao;
import dao.EmployeeDaoImpl;
import model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import service.EmployeeService;
import service.EmployeeServiceImpl;

@Configuration
public class EmployeeConfig {

    // any object created as a Bean must be usable and initialisation must be at one place(here)
    @Bean
    public EmployeeService employeeService(@Autowired EmployeeDao employeeDao) {
       // EmployeeServiceImpl employeeService = new EmployeeServiceImpl(employeeDao);
        //  employeeService.setEmployeeDao(employeeDao);   // setter injection
      //  return employeeService;
        return new EmployeeServiceImpl(employeeDao);  // constructor injection
    }

    @Bean
    public EmployeeDao employeeDao(@Autowired MysqlDataSource dataSource) {
        return new EmployeeDaoImpl(dataSource); // constructor injection
    }

    @Bean
    public MysqlDataSource mysqlDataSource() {
        MysqlDataSource datasource = new MysqlDataSource();
        datasource.setServerName("localhost");
        datasource.setDatabaseName("lowes");
        datasource.setUser("mysql");
        datasource.setPassword("mysql");
        return datasource;
    }
}

