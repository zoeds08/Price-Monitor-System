import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import product.Product;
import product.User;
import service.EmailService;
import service.MySQLAccess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class RecomApplication {

    private final static String Reduced_Queue1_NAME = "highLevelReduced";
    private final static String Reduce_Queue2_NAME = "lowLevelReduced";

    static final String d_user_name = "root";
    static final String d_password = "19930823";
    static final String d_server_name = "127.0.0.1:3306";
    static final String d_db_name = "PriceServer";
    static final MySQLAccess mySQLAccess = new MySQLAccess(d_server_name,d_db_name,d_user_name,d_password);

    static EmailService emailService = new EmailService();

    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args){
        productConsume(Reduced_Queue1_NAME);
        productConsume(Reduce_Queue2_NAME);
    }

    public static void productConsume(String Queue_Name){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");

        Connection connection = null;
        try {
            connection = factory.newConnection();
            Channel consumed = connection.createChannel();
            consumed.queueDeclare(Queue_Name,true,false,false,null);

            Consumer consumer = new DefaultConsumer(consumed){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    Product product = mapper.readValue(body,Product.class);

                    //1. category_name -> DB
                    //2. get user list from DB
                    String category = product.getCategory();
                    double percentage = product.getPercentage();
                    try {
                        List<User> users = mySQLAccess.getUserInfo(category);
                        //3. push to emails
                        for(User user: users){
                            String email = user.getEmail();
                            double threshold = user.getThreshold();
                            if(Math.abs(percentage)>threshold){
                                //push();
                                emailService.sendEmail(email,product);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            consumed.basicConsume(Queue_Name,true,consumer);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

}
