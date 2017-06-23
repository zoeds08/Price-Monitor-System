package crawler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import product.Category;
import product.Product;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class CrawlerMain {
    private final static String IN_QUEUE_NAME = "q_feeds";
    private final static String OUT_QUEUE_NAME = "q_product";
    private final static String ERR_QUEUE_NAME = "q_error";

    private static ProductCrawler crawler;
    private static ObjectMapper mapper;

    static final String d_user_name = "root";
    static final String d_password = "19930823";
    static final String d_server_name = "127.0.0.1:3306";
    static final String d_db_name = "PriceServer";
    static final MySQLAccess mySQLAccess = new MySQLAccess(d_server_name,d_db_name,d_user_name,d_password);

    private static Channel outChannel;
    private static Channel errChannel;


    public static void main(String[] args) throws Exception {

        String proxyFilePath = args[0];
        mapper = new ObjectMapper();

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        com.rabbitmq.client.Channel inChannel = connection.createChannel();
        inChannel.queueDeclare(IN_QUEUE_NAME,true,false,false,null);
        inChannel.basicQos(10);
        System.out.println("[*] Waiting for messages. To exit press CTRL+C");


        outChannel= connection.createChannel();
        outChannel.queueDeclare(OUT_QUEUE_NAME,true,false,false,null);

        errChannel = connection.createChannel();
        errChannel.queueDeclare(ERR_QUEUE_NAME,true,false,false,null);

        crawler = new ProductCrawler(proxyFilePath, errChannel, ERR_QUEUE_NAME);

        Channel categoryChannel = connection.createChannel();
        categoryChannel.queueDeclare("categories",true,false,false,null);

        for(int i=1;i<=94;i++){//categoryId:[1,94]
            try{
                Category category = mySQLAccess.getData(i);
                System.out.println(category.category_name);
//                Channel categoryChannel = connection.createChannel();
//                categoryChannel.queueDeclare(category.category_name,true,false,false,null);

                List<Product> products = crawler.GetProductBasicInfoByURL(category);
                for(Product product: products){
                    String jsonInString = mapper.writeValueAsString(product);
                    System.out.println(jsonInString);
                    categoryChannel.basicPublish("",category.category_name,null,jsonInString.getBytes("UTF-8"));
                    mySQLAccess.addProductData(product);
                }
//                categoryChannel.close();
                Thread.sleep(2000);
            }catch (InterruptedException ex){
                Thread.currentThread().interrupt();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
        categoryChannel.close();
        connection.close();
    }
}
