package crawler.LevelToCrawl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import crawler.MySQLAccess;
import crawler.ProductCrawler;
import product.Category;
import product.Product;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * Created by Zoe on 6/23/17.
 */
public class LevelThread2 implements Runnable{

    private final static String Queue2_NAME = "lowLevel";
    private static ProductCrawler crawler2;
    String proxyFilePath2 = "src/main/resources/proxylist2.txt";

    static final String d_user_name = "root";
    static final String d_password = "19930823";
    static final String d_server_name = "127.0.0.1:3306";
    static final String d_db_name = "PriceServer";
    static final MySQLAccess mySQLAccess = new MySQLAccess(d_server_name,d_db_name,d_user_name,d_password);

    private static ObjectMapper mapper = new ObjectMapper();
    @Override
    public void run() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = null;
        try {
            connection = factory.newConnection();
            Channel queue2 = connection.createChannel();
            queue2.queueDeclare(Queue2_NAME,true,false,false,null);
            crawler2 = new ProductCrawler(proxyFilePath2);
            for(int i=56;i<=69;i++){
                Category category = mySQLAccess.getData(i);
                System.out.println(category.category_name);
                crawler2.GetProductBasicInfoByURL(category);
                List<Product> products = crawler2.GetProductBasicInfoByURL(category);
                for(Product product: products){
                    mySQLAccess.addProductData(product);
                    String jsonInString = mapper.writeValueAsString(product);
                    System.out.println(jsonInString);
                    queue2.basicPublish("",Queue2_NAME,null,jsonInString.getBytes("UTF-8"));
                }
            }
            queue2.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
