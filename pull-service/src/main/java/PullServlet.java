import product.Product;
import product.User;
import service.EmailService;
import service.MySQLAccess;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/MonitorPrice")
public class PullServlet extends HttpServlet{


    private ServletConfig config = null;
    EmailService emailService = new EmailService();

    static final String d_user_name = "root";
    static final String d_password = "19930823";
    static final String d_server_name = "127.0.0.1:3306";
    static final String d_db_name = "PriceServer";
    static final MySQLAccess mySQLAccess = new MySQLAccess(d_server_name,d_db_name,d_user_name,d_password);
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. email -> category & threshold
        List<Product> allFound = new ArrayList<>();
        String email = req.getParameter("email");
        try {
            List<User> users = mySQLAccess.getUserInfo(email);
            for(User user: users){
                String category = user.getCategory_list();
                double thre = user.getThreshold();
                //2. category -> productList
                List<Product> products = mySQLAccess.getProductInfo(category);
                allFound.addAll(products);
                //3. send email
                for(Product product: products){
                    double percentage = product.getPercentage();
                    if(Math.abs(percentage)>thre){
                        //push();
                        emailService.sendEmail(email,product);
                    }
                }

            }
            resp.setContentType("text/html");
            StringBuffer sb = new StringBuffer();
            sb.append("<html>\n" + "<body>\n" + "\n" + "<h3>Here is the information of the reduced price product:" + "</h3> \n <hr>");
            for(Product product: allFound){
                sb.append("<h3> Name: " + product.getTitle() + "</h3>\n"
                        + "<h3> reduced percentage: " + product.getPercentage() + "</h3>\n"
                        + "<h3> 1-Click -> " + product.getDetail_url() + "</h3>\n");
            }
            sb.append("\n" + "</body>\n" + "</html>");
            resp.getWriter().println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
        super.init(config);
        System.out.println("server is running");
    }
}
