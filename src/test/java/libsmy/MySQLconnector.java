package libsmy;

import  java.sql.Connection;
import  java.sql.Statement;
import  java.sql.ResultSet;
import  java.sql.DriverManager;
import  java.sql.SQLException;

public class MySQLconnector {

    public String dbUrl;
    public String username;
    public String password;
    public Connection connect;
    public Statement stmt;

    public void dbConnection () throws  ClassNotFoundException, SQLException {
        //Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"
        this.dbUrl = "jdbc:mysql://10.10.1.11:3306/backgammon";

        //Database Username
        this.username = "matvienkod";

        //Database Password
        this.password = "GgFhEEjL6s4frfzb";

        //Load mysql jdbc driver
        Class.forName("com.mysql.jdbc.Driver");

        //Create Connection to DB
        this.connect = DriverManager.getConnection(dbUrl, username, password);

        //Create Statement Object
        this.stmt = connect.createStatement();

        //Query to Execute
//        String query = "select *  from employee;";

        // Execute the SQL Query. Store results in ResultSet
//        ResultSet rs= stmt.executeQuery(query);

        // While Loop to iterate through all data and print results если что то было получено то цикл while сработает
     /*   while (rs.next()){
            String myName = rs.getString(1);
            String myAge = rs.getString(2);
            System. out.println(myName+"  "+myAge);
        }*/
        // closing DB Connection
//        con.close();
    }
}

