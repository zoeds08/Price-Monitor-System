package service;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import product.Product;

public class EmailService {
    public void sendEmail(String userEmail, Product product) throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("zoeds08", "10210823America"));
        email.setSSLOnConnect(true);
        email.setFrom("zoeds08@gmail.com");
        email.setSubject(product.getCategory()+" Exciting Reduced ");
        StringBuffer sb = new StringBuffer();
        sb.append("<html>\n" + "<body>\n" + "\n" + "<h3>Here is the information of the reduced price product" + "</h3> \n <hr>");
        sb.append("<h3> Name: " + product.getTitle() + "</h3>\n"
                + "<h3> EXCITING price: " + product.getPrice() + "</h3>\n"
                + "<h3> last price: " + product.getOld_price() + "</h3>\n"
                + "<h3> 1-Click -> " + product.getDetail_url() + "</h3>\n");
        sb.append("\n" + "</body>\n" + "</html>");
        email.setHtmlMsg(sb.toString());
        email.setMsg(sb.toString());
        email.addTo(userEmail);
        System.out.println("send product info: " + product.getTitle() + " to " + userEmail);
        email.send();
    }
}
