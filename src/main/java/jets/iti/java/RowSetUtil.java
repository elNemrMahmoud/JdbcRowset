package jets.iti.java;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.WebRowSet;
import javax.sql.rowset.JoinRowSet;

class RowSetUtil{

    private static Properties prop = PropertiesFileUtil.getPropertiesFromFile();
    
    private static RowSetFactory rsFactory;
    
    
    public static JdbcRowSet getJDBCRowset()
    {
        JdbcRowSet jdbcRowSet =null;
        try{
            rsFactory = RowSetProvider.newFactory();
            jdbcRowSet = rsFactory.createJdbcRowSet();

            jdbcRowSet.setUrl(prop.getProperty("ORACLE_DB_URL"));
            jdbcRowSet.setUsername(prop.getProperty("ORACLE_DB_USERNAME"));
            jdbcRowSet.setPassword(prop.getProperty("ORACLE_DB_PASSWORD"));
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return jdbcRowSet;
    }

    public static CachedRowSet getCachedRowset()
    {
        CachedRowSet cachedRowset = null;
        try{
            rsFactory = RowSetProvider.newFactory();
            cachedRowset = rsFactory.createCachedRowSet();
            

            cachedRowset.setUrl(prop.getProperty("ORACLE_DB_URL"));
            cachedRowset.setUsername(prop.getProperty("ORACLE_DB_USERNAME"));
            cachedRowset.setPassword(prop.getProperty("ORACLE_DB_PASSWORD"));

        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return cachedRowset;
    }

    public static WebRowSet getWebRowset()
    {
        WebRowSet webRowSet = null;
        try{
            rsFactory = RowSetProvider.newFactory();
            webRowSet = rsFactory.createWebRowSet();

            webRowSet.setUrl(prop.getProperty("ORACLE_DB_URL"));
            webRowSet.setUsername(prop.getProperty("ORACLE_DB_USERNAME"));
            webRowSet.setPassword(prop.getProperty("ORACLE_DB_PASSWORD"));
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return webRowSet;
    } 
/*
    public static FilteredRowSet getFilteredRowset()
    {
        FilteredRowSet filteredRowset = rsFactory.createFilteredRowSet();

        filteredRowset.setURL(prop.getProperty("ORACLE_DB_URL"));
        filteredRowset.setUsername(prop.getProperty("ORACLE_DB_USERNAME"));
        filteredRowset.setPassword(prop.getProperty("ORACLE_DB_PASSWORD"));

        return filteredRowset;
    }
*/

    public static JoinRowSet getJoinRowset()
    {
        JoinRowSet joinRowset = null;        
        try{
            rsFactory = RowSetProvider.newFactory();
            joinRowset = rsFactory.createJoinRowSet();

            joinRowset.setUrl(prop.getProperty("ORACLE_DB_URL"));
            joinRowset.setUsername(prop.getProperty("ORACLE_DB_USERNAME"));
            joinRowset.setPassword(prop.getProperty("ORACLE_DB_PASSWORD"));
        }catch(SQLException e)
        {
            e.printStackTrace();
        }
        return joinRowset;
    }


}