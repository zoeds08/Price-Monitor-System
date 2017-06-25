package service;

import product.Product;
import product.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MySQLAccess {
    private Connection d_connect = null;
    private String d_user_name;
    private String d_password;
    private String d_server_name;
    private String d_db_name;

    public void close() throws Exception {
        System.out.println("Close database");
        try {
            if (d_connect != null) {
                d_connect.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public MySQLAccess(String server, String db, String user, String password) {
        d_server_name = server;
        d_db_name = db;
        d_user_name = user;
        d_password = password;
    }

    private Connection getConnection() throws Exception {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            String conn = "jdbc:mysql://" + d_server_name + "/" +
                    d_db_name+"?user="+d_user_name+"&password="+d_password;
            System.out.println("Connecting to database: " + conn);
            d_connect = DriverManager.getConnection(conn);
            System.out.println("Connected to database");
            return d_connect;
        } catch(Exception e) {
            throw e;
        }
    }

    private Boolean isRecordExist(Connection connect,String sql_string) throws SQLException {
        PreparedStatement existStatement = null;
        boolean isExist = false;

        try
        {
            existStatement = connect.prepareStatement(sql_string);
            ResultSet result_set = existStatement.executeQuery();
            if (result_set.next())
            {
                isExist = true;
            }
        }
        catch(SQLException e )
        {
            System.out.println(e.getMessage());
            throw e;
        }
        finally
        {
            if (existStatement != null)
            {
                existStatement.close();
            }
        }

        return isExist;
    }

    public List<User> getUserInfo(String category) throws Exception {//id:[1,94]
        Connection connect = null;
        PreparedStatement adStatement = null;
        ResultSet result_set = null;
        List<User> users = new ArrayList<>();
        String sql_string = "select * from " + d_db_name + ".user where category_list= '" + category + "'";
        try {
            connect = getConnection();
            adStatement = connect.prepareStatement(sql_string);
            result_set = adStatement.executeQuery();
            while (result_set.next()) {
                User user = new User();
                user.setId(result_set.getInt("id"));
                user.setEmail(result_set.getString("email"));
                user.setPassword(result_set.getString("password"));
                user.setNotification_type(result_set.getInt("notification_type"));
                user.setCategory_list(result_set.getString("category_list"));
                user.setThreshold(result_set.getDouble("threshold"));
                users.add(user);
            }
        }
        catch(SQLException e )
        {
            System.out.println(e.getMessage());
            throw e;
        }
        finally
        {
            if (adStatement != null) {
                adStatement.close();
            };
            if (result_set != null) {
                result_set.close();
            }
            if (connect != null) {
                connect.close();
            }
        }
        return users;
    }

}