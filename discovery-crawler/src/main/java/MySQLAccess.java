import java.sql.*;


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
            };
        }

        return isExist;
    }


    public void addProductData(Category category) throws Exception {
        Connection connect = null;
        boolean isExist = false;
        String sql_string = "select categoryId from " + d_db_name + ".Category where id=" + category.categoryId;
        PreparedStatement product_info = null;
        try
        {
            connect = getConnection();
        }
        catch(SQLException e )
        {
            System.out.println(e.getMessage());
            throw e;
        }
        finally
        {
            if(connect != null && isExist) {
                connect.close();
            }
        }

        if(isExist) {
            return;
        }

        sql_string = "insert into " + d_db_name +".Category(id, category_name, product_list_url, priority) "
                + "values(?,?,?,?)";
        try {
            product_info = connect.prepareStatement(sql_string);
            product_info.setInt(1, category.categoryId);
            product_info.setString(2, category.category_name);

            product_info.setString(3, category.product_lists_url.toString());
            product_info.setInt(4, category.priority);
            product_info.executeUpdate();
        }
        catch(SQLException e )
        {
            System.out.println(e.getMessage());
            throw e;
        }
        finally
        {
            if (product_info != null) {
                product_info.close();
            }
            if (connect != null) {
                connect.close();
            }
        }
    }


}