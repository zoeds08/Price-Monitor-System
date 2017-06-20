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


public class CategoryCrawler {

    static final String d_user_name = "root";
    static final String d_password = "19930823";
    static final String d_server_name = "127.0.0.1:3306";
    static final String d_db_name = "PriceServer";
    static final MySQLAccess sqlAccess = new MySQLAccess(d_server_name,d_db_name,d_user_name,d_password);

    private static final String Books_QUERY_URL = "https://www.amazon.com/b/ref=sr_aj?node=283155&ajr=0";
    private static final String Software_QUERY_URL = "https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dsoftware&field-keywords=";
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36";
    //1-32
    private static final String Books_url = "https://www.amazon.com/s/ref=nb_sb_noss_2?url=search-alias%3Dstripbooks&field-keywords=-1234567";
    //1-17
    private static final String Software_url = "https://www.amazon.com/s/ref=nb_sb_noss_2?url=search-alias%3Dbeauty&field-keywords=-1234567";
    static final String[] names = {"Books", "Software"};
    static final String[] urls = {Books_QUERY_URL,Software_QUERY_URL};

    private static int index=1;

    public static void main(String[] args) throws Exception {
        addBooks();
        addSoftware();
    }

    public static void addBooks() throws Exception {
        HashSet<String> all = new HashSet<>();
        all.add(Books_url);
        Document doc = Jsoup.connect(urls[0]).userAgent(USER_AGENT).timeout(1000).get();
        for(int i=1;i<=32;i++){
            HashSet<String> set = new HashSet<>();
            Element ele = doc.select("#leftNav > ul:nth-child(5) > ul > div > li:nth-child("+String.valueOf(i)+") > span > a").first();
            if(ele!=null){
                String detailUrl = ele.attr("href");
                System.out.println("detail url = " + detailUrl);
                set.add(detailUrl);
                all.add(detailUrl);
                String name = doc.select("#leftNav > ul:nth-child(5) > ul > div > li:nth-child("+String.valueOf(i)+") > span > a > span").text();
                System.out.println("category name = " + name);
                Category category1 = new Category(index++, name, set,2);
                sqlAccess.addProductData(category1);
            }
        }
        Category books = new Category(index++, names[0],all,1);
        sqlAccess.addProductData(books);
    }

    public static void addSoftware() throws Exception {
        HashSet<String> all = new HashSet<>();
        all.add(Software_url);
        Document doc = Jsoup.connect(urls[1]).userAgent(USER_AGENT).timeout(1000).get();
        for(int i=1;i<=17;i++){
            HashSet<String> set = new HashSet<>();
            Element ele = doc.select("#leftNav > ul:nth-child(7) > ul > div > li:nth-child("+String.valueOf(i)+") > span > a").first();
            if(ele!=null){
                String detailUrl = ele.attr("href");
                System.out.println("detail url = " + detailUrl);
                set.add(detailUrl);
                all.add(detailUrl);
                String name = doc.select("#leftNav > ul:nth-child(7) > ul > div > li:nth-child("+String.valueOf(i)+") > span > a > span").text();
                System.out.println("category name = " + name);
                Category category2 = new Category(index++, name, set,2);
                sqlAccess.addProductData(category2);
            }
        }
        Category soft = new Category(index++, names[1],all,1);
        sqlAccess.addProductData(soft);
    }
}
