import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class CategoryCrawler{

    static final String d_user_name = "root";
    static final String d_password = "19930823";
    static final String d_server_name = "127.0.0.1:3306";
    static final String d_db_name = "PriceServer";
    static final MySQLAccess sqlAccess = new MySQLAccess(d_server_name,d_db_name,d_user_name,d_password);

    private static final String Books_QUERY_URL = "https://www.amazon.com/b/ref=sr_aj?node=283155&ajr=0";
    private static final String Arts_QUERY_URL = "https://www.amazon.com/b/ref=sr_aj?node=2617941011&ajr=0";
    private static final String Toys_QUERY_URL = "https://www.amazon.com/b/ref=sr_aj?node=165793011&ajr=0";
    private static final String Software_URL = "https://www.amazon.com/b/ref=sr_aj?node=229534&ajr=0";

    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36";


    static final String[] names = {"Books", "Toys & Games", "Arts, Crafts & Sewing", "Software"};
    static final String[] urls = {Books_QUERY_URL,Toys_QUERY_URL,Arts_QUERY_URL,Software_URL};
    static final int[] nums = {32,21,13,17};//items
    static final int[] params = {5,5,2,7};//div params

    private static int index=1;

    public static void main(String[] args) throws Exception {
        for(int i=0;i<4;i++){
            addCategory(i);
        }
    }


    public static void addCategory(int self) throws Exception {
            Document doc = Jsoup.connect(urls[self]).userAgent(USER_AGENT).timeout(100000).get();
            for (int i = 1; i <= nums[self]; i++) {
                Element ele = doc.select("#leftNav > ul:nth-child("+ String.valueOf(params[self])+") > ul > div > li:nth-child(" + String.valueOf(i) + ") > span > a").first();
                if (ele != null) {
                    String detailUrl = ele.attr("href");
                    System.out.println("detail url = " + detailUrl);
                    String name = doc.select("#leftNav > ul:nth-child("+ String.valueOf(params[self])+") > ul > div > li:nth-child(" + String.valueOf(i) + ") > span > a > span").text();
                    System.out.println("category name = " + name);
                    Category category1 = new Category(index++, name, "https://www.amazon.com" + detailUrl, 2);
                    sqlAccess.addProductData(category1);
                }
            }
            Category books = new Category(index++, names[self], urls[self], 1);
            sqlAccess.addProductData(books);
    }
}