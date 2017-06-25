package monitor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import net.spy.memcached.MemcachedClient;

import product.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.TimeoutException;

@WebServlet(name = "/monitor")
public class MonitorServlet extends HttpServlet{

    private static String memcacheServer = "127.0.0.1";
    private static int memcachePort = 11211;
    private static int EXP = 60000;

    private final static String Queue1_NAME = "highLevel";
    private final static String Queue2_NAME = "lowLevel";
    private final static String Reduced_Queue1_NAME = "highLevelReduced";
    private final static String Reduce_Queue2_NAME = "lowLevelReduced";

    static final String d_user_name = "root";
    static final String d_password = "19930823";
    static final String d_server_name = "127.0.0.1:3306";
    static final String d_db_name = "PriceServer";
    static final MySQLAccess mySQLAccess = new MySQLAccess(d_server_name,d_db_name,d_user_name,d_password);

    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws ServletException {
        MemcachedClient cache = null;
        try {
            cache = new MemcachedClient(new InetSocketAddress(memcacheServer, memcachePort));
//            System.out.println("connect");
        }catch(IOException e){
            e.printStackTrace();
        }
//        System.out.println("build");
        productConsume(Queue1_NAME,Reduced_Queue1_NAME, cache);
        productConsume(Queue2_NAME,Reduce_Queue2_NAME,cache);
    }

    public static void productConsume(String Queue_Name, String Reduced_Queue_Name, MemcachedClient memcachedClient){

        //1.get product from the queue;
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
//        System.out.println("factory");
        try {
            Connection connection = factory.newConnection();
            Channel consumed = connection.createChannel();
            consumed.queueDeclare(Queue_Name,true,false,false,null);

            Channel toPublish = connection.createChannel();
            toPublish.queueDeclare(Reduced_Queue_Name,true,false,false,null);

//            System.out.println("before consume");
            Consumer consumer = new DefaultConsumer(consumed){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                    //2. check Memcached {product}
                    Product product = mapper.readValue(body,Product.class);
                    double value = getDoubleData(memcachedClient,product.getProduct_id());
                    System.out.println("get");
                    //3. update Memcached + MySQL
                    if(value==-1){
                        try {
                            mySQLAccess.addProductData(product);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        double oldPrice = value;
                        double price = product.getPrice();
                        double percentage = (price-oldPrice+1)/(oldPrice+1);
                        System.out.println(percentage);
                        product.setOld_price(oldPrice);
                        product.setPercentage(percentage);
                        if(percentage!=1){
                            try {
                                mySQLAccess.updateProductData(product.getProduct_id(),price,oldPrice,percentage);
                                System.out.println("update product " + product.getProduct_id() + " " + price + " " + oldPrice);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        if (percentage<0){
                            String jsonInString = mapper.writeValueAsString(product);
                            System.out.println(jsonInString);
                            toPublish.basicPublish("", Reduced_Queue_Name,null, jsonInString.getBytes("UTF-8"));
                            //4. publish to ReducedQueue
                        }
                    }
                    addDoubleData(memcachedClient,product.getProduct_id(),product.getPrice());//update MemCached
                }
            };
            consumed.basicConsume(Queue_Name, true, consumer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

    public static double getDoubleData(MemcachedClient cache, String key){
        if(cache.get(key) instanceof Double){
            double value = (Double) cache.get(String.valueOf(key));
            return value;
        }
        return -1;
    }

    public static void addDoubleData(MemcachedClient cache, String key, double value){
        cache.set(key,EXP, value);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
