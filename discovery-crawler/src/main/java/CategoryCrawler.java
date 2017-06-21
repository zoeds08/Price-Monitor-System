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
    private static final String Apps_Games_QUERY_URL = "https://www.amazon.com/b/ref=sr_aj?node=2350149011&ajr=0";
    private static final String Software_URL = "https://www.amazon.com/b/ref=sr_aj?node=229534&ajr=0";

    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36";


    private static final String Books_url = "https://www.amazon.com/s/ref=nb_sb_noss_2?url=search-alias%3Dstripbooks&field-keywords=-1234567";
    private static final String Arts_url = "https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Darts-crafts&field-keywords=";
    private static final String Apps_url = "https://www.amazon.com/s/ref=nb_sb_noss_2?url=search-alias%3Dmobile-apps&field-keywords=-1234567";
    private static final String Software_url = "https://www.amazon.com/s/ref=nb_sb_noss_2?url=search-alias%3Dsoftware&field-keywords=-1234567";

    static final String[] names = {"Books", "Arts, Crafts & Sewing", "Apps & Games", "Software"};
    static final String[] urls = {Books_QUERY_URL,Apps_Games_QUERY_URL,Arts_QUERY_URL,Software_URL};
    static final String[] selfs = {Books_url,Apps_url,Arts_url,Software_url};//with -1234567
    static final int[] nums = {32,28,13,17};//items
    static final int[] params = {5,5,2,7};//div params

    private static int index=1;

    public static void main(String[] args) throws Exception {
        for(int i=0;i<4;i++){
            addCategory(i);
        }
    }


    public static void addCategory(int self) throws Exception {
            HashSet<String> all = new HashSet<>();
            all.add(selfs[self]);
            Document doc = Jsoup.connect(urls[self]).userAgent(USER_AGENT).timeout(100000).get();
            for (int i = 1; i <= nums[self]; i++) {
                HashSet<String> set = new HashSet<>();
                Element ele = doc.select("#leftNav > ul:nth-child("+ String.valueOf(params[self])+") > ul > div > li:nth-child(" + String.valueOf(i) + ") > span > a").first();
                if (ele != null) {
                    String detailUrl = ele.attr("href");
                    System.out.println("detail url = " + detailUrl);
                    set.add("https://www.amazon.com" + detailUrl);
                    all.add("https://www.amazon.com" + detailUrl);
                    String name = doc.select("#leftNav > ul:nth-child("+ String.valueOf(params[self])+") > ul > div > li:nth-child(" + String.valueOf(i) + ") > span > a > span").text();
                    System.out.println("category name = " + name);
                    Category category1 = new Category(index++, name, set, 2);
                    sqlAccess.addProductData(category1);
                }
            }
            Category books = new Category(index++, names[self], all, 1);
            sqlAccess.addProductData(books);
    }
}