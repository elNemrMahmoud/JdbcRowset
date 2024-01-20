package jets.iti.java;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.WebRowSet;
import javax.sql.rowset.JoinRowSet;


class UserDAO{

    public static void createUserTable()
    {
        try{
        JdbcRowSet jdbcRowSet = RowSetUtil.getJDBCRowset();
        String sql = "CREATE TABLE Users (id VARCHAR(255) PRIMARY KEY,deptId VARCHAR(255),fname VARCHAR(255),lname VARCHAR(255),dob DATE,FOREIGN KEY (deptId) REFERENCES Department(departmentId));";
        jdbcRowSet.setCommand(sql);
        jdbcRowSet.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void fillUserTable()
    {
        try{
            JdbcRowSet jdbcRowSet = RowSetUtil.getJDBCRowset();

            jdbcRowSet.setCommand("INSERT INTO Users (id, deptId, fname, lname, dob) VALUES (?, ?, ?, ?, ?)");

            for (int i = 0; i < 10; i++) {
                jdbcRowSet.setString(1, "ID" + i);
                jdbcRowSet.setString(2, "Dept" + i%4);
                jdbcRowSet.setString(3, "FirstName" + i);
                jdbcRowSet.setString(4, "LastName" + i);
                jdbcRowSet.setDate(5, new java.sql.Date(System.currentTimeMillis()));

                jdbcRowSet.execute();
            }

            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
    public static ArrayList<User> getAllUsersUsingjdbcRs()
    {
        ArrayList<User> users = new ArrayList<>();
        try{
            JdbcRowSet jdbcRowSet = RowSetUtil.getJDBCRowset();
            jdbcRowSet.setCommand("SELECT * FROM Users");
            jdbcRowSet.execute();

            while (jdbcRowSet.next()) {
                users.add(new User(
                        jdbcRowSet.getString("id"),
                        jdbcRowSet.getString("deptId"),
                        jdbcRowSet.getString("fname"),
                        jdbcRowSet.getString("lname"),
                        jdbcRowSet.getDate("dob")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;            
        
    }

    public static void insertUser(User user)
    {
        try{
            CachedRowSet cachedRowSet = RowSetUtil.getCachedRowset();
            cachedRowSet.setCommand("SELECT * FROM Users");
            cachedRowSet.setConcurrency(CachedRowSet.CONCUR_UPDATABLE);
            cachedRowSet.execute();

            cachedRowSet.moveToInsertRow();
            cachedRowSet.updateString("id", user.getId());
            cachedRowSet.updateString("deptId", user.getDeptId());
            cachedRowSet.updateString("fname", user.getFname());
            cachedRowSet.updateString("lname", user.getLname());
            cachedRowSet.updateDate("dob", user.getDob());

            cachedRowSet.insertRow();
            cachedRowSet.moveToCurrentRow();
            cachedRowSet.acceptChanges();

        } catch (SQLException e) {
            //e.printStackTrace();
        }       
    }

    public static void deleteUserUsingCachedRowSet(String userId) {

        try{
            CachedRowSet cachedRowSet = RowSetUtil.getCachedRowset();
            cachedRowSet.setCommand("SELECT * FROM Users WHERE id = ?");
            cachedRowSet.setString(1, userId);
            cachedRowSet.setConcurrency(CachedRowSet.CONCUR_UPDATABLE);
            cachedRowSet.execute();

            if (cachedRowSet.next()) {
                cachedRowSet.deleteRow();
                cachedRowSet.acceptChanges();
            }

        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public static void getUserDepartments()
    {
        try{
            CachedRowSet userRowSet = RowSetUtil.getCachedRowset();
            CachedRowSet departmentRowSet = RowSetUtil.getCachedRowset();
            JoinRowSet joinRowSet = RowSetUtil.getJoinRowset();

            userRowSet.setCommand("SELECT id, fname, lname, deptId FROM Users");
            departmentRowSet.setCommand("SELECT departmentId,departmentName FROM Department");

            userRowSet.execute();
            departmentRowSet.execute();

            joinRowSet.addRowSet(userRowSet,"deptId");
            joinRowSet.addRowSet(departmentRowSet,"departmentId");

            while (joinRowSet.next()) {
                String userName = joinRowSet.getString("fname") + " " + joinRowSet.getString("lname");
                String departmentName = joinRowSet.getString("departmentName");
                System.out.println("User: " + userName + ", " + departmentName);
            }    
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void saveUsersToXML()
    {
        try{
            WebRowSet webRowSet = RowSetUtil.getWebRowset();
            webRowSet.setCommand("SELECT * FROM Users");
            webRowSet.execute();
            FileOutputStream out = new FileOutputStream("C:/Users/dark1/Desktop/JavaCore/Projects/JDBC/Day4/src/main/java/resources/Users.xml");
            webRowSet.writeXml(out);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}