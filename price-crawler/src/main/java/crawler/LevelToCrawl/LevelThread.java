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
public class LevelThread implements Runnable {
    private final static String Queue1_NAME = "highLevel";
    private static ProductCrawler crawler1;
    String proxyFilePath1 = "src/main/resources/proxylist1.txt";

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
            Channel queue1 = connection.createChannel();
            queue1.queueDeclare(Queue1_NAME,true,false,false,null);
            crawler1 = new ProductCrawler(proxyFilePath1);
            for(int i=1;i<=33;i++){
                Category category = mySQLAccess.getData(i);
                System.out.println(category.category_name);
                List<Product> products = crawler1.GetProductBasicInfoByURL(category);
                for(Product product: products){
                    mySQLAccess.addProductData(product);
                    String jsonInString = mapper.writeValueAsString(product);
                    System.out.println(jsonInString);
                    queue1.basicPublish("",Queue1_NAME,null,jsonInString.getBytes("UTF-8"));
                }
            }
            queue1.close();
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
