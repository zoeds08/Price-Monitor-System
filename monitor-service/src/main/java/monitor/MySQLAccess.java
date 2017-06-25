package monitor;

import product.Product;

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
            }
        }

        return isExist;
    }

    public Product getData(int id) throws Exception {//id:[1,94]
        Connection connect = null;
        PreparedStatement adStatement = null;
        ResultSet result_set = null;
        Product product = new Product();
        String sql_string = "select * from " + d_db_name + ".Product where product_id=" + id;
        try {
            connect = getConnection();
            adStatement = connect.prepareStatement(sql_string);
            result_set = adStatement.executeQuery();
            while (result_set.next()) {
                product.setProduct_id(result_set.getString("product_id"));
                product.setCategory(result_set.getString("category_name"));
                product.setTitle(result_set.getString("title"));
                product.setDetail_url(result_set.getString("detail_url"));
                product.setPrice(result_set.getDouble("price"));
                product.setOld_price(result_set.getDouble("old_price"));
                product.setPercentage(result_set.getDouble("percentage"));
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
        return product;
    }

    public void addProductData(Product product) throws Exception {
        Connection connect = null;
        boolean isExist = false;
        String sql_string = "select product_id from " + d_db_name + ".Product where product_id= '" + product.getProduct_id() + "'";

        PreparedStatement product_info = null;
        try
        {
            connect = getConnection();
            isExist = isRecordExist(connect,sql_string);
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

        sql_string = "insert into " + d_db_name +".Product(product_id, category_name, title, detail_url, price, old_price, percentage) "
                + "values(?,?,?,?,?,?,?)";
        try {
            product_info = connect.prepareStatement(sql_string);
            product_info.setString(1, product.getProduct_id());
            product_info.setString(2, product.getCategory());

            product_info.setString(3, product.getTitle());
            product_info.setString(4, product.getDetail_url());
            product_info.setDouble(5, product.getPrice());
            product_info.setDouble(6, product.getOld_price());
            product_info.setDouble(7, product.getPercentage());

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



    public void updateProductData(String product_id, double price, double old_price, double percentage) throws Exception {
        Connection connect = null;

        PreparedStatement product_info = null;

        String sql_string = "UPDATE " + d_db_name +".Product SET price = ?, old_price = ?, percentage = ? WHERE product_id = ?";
        try {
            connect = getConnection();
            product_info = connect.prepareStatement(sql_string);
            product_info.setDouble(1, price);
            product_info.setDouble(2, old_price);
            product_info.setDouble(3, percentage);
            product_info.setString(4, product_id);
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