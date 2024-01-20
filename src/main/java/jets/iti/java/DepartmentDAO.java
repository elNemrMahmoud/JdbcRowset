package jets.iti.java;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DepartmentDAO{

    public static void createDepartmentTable()
    {
        try{
            JdbcRowSet jdbcRowSet = RowSetUtil.getJDBCRowset();
            String sql ="CREATE TABLE Department (departmentId VARCHAR(255) PRIMARY KEY,departmentName VARCHAR(255));";
            jdbcRowSet.setCommand(sql);
            jdbcRowSet.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void fillDepartmentTable()
    {
        try{
            JdbcRowSet jdbcRowSet = RowSetUtil.getJDBCRowset();
            jdbcRowSet.setCommand("INSERT INTO Department (departmentId, departmentName) VALUES (?, ?)");

            for (int i = 0; i < 5; i++) {
                String departmentId = "Dept" + i;
                String departmentName = "Department " + i;

                jdbcRowSet.setString(1, departmentId);
                jdbcRowSet.setString(2, departmentName);
                jdbcRowSet.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }        
    }
}