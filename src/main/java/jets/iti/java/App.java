package jets.iti.java;

import java.util.ArrayList;
import java.util.*;
public class App 
{

    public static void main( String[] args )
    {
        //DepartmentDAO.createDepartmentTable();
        //DepartmentDAO.fillDepartmentTable();

        //UserDAO.createUserTable();
        UserDAO.fillUserTable();

        ArrayList<User> users = UserDAO.getAllUsersUsingjdbcRs();
        System.out.println("Printing all users from DB using JDBCRowSet");
        for(User u : users)
        {
            System.out.println(u.toString());
        }

        System.out.println("Inserting User into the DB...");
        User testUser = new User("ID99","Dept3","Mahmoud","Marwan",new java.sql.Date(System.currentTimeMillis()));
        UserDAO.insertUser(testUser);

        System.out.println("Deleting user by ID...");
        UserDAO.deleteUserUsingCachedRowSet("ID98");

        System.out.println("Printing all users and their departments");
        UserDAO.getUserDepartments();

        System.out.println("Saving users to XML file");
        UserDAO.saveUsersToXML();
        System.exit(0);       
    }



}
