import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Crawler {
    private static final String Books_QUERY_URL = "https://www.amazon.com/b/ref=sr_aj?node=283155&ajr=0";
    private static final String Beauty_Personal_Care_QUERY_URL = "https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dsoftware&field-keywords=";
    private static final String Clothing_Shoes_Jewelry_QUERY_URL = "https://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Dfashion&field-keywords=-1234567&rh=n%3A7141123011%2Ck%3A-1234567";

    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36";

    private static final String booksSelector = "#leftNavContainer > ul:nth-child(2) > ul > div > li:nth-child(1) > span > a";//1-32 items
    private static final String booksPart1 = "#leftNavContainer > ul:nth-child(2) > ul > div > li:nth-child(";
    private static final String booksPart2 = ") > span > a";

    private static final String clothingsPart1 = "#leftNavContainer > ul:nth-child(5) > ul > li:nth-child(";//1-11 items
    private static final String clothingsPart2 = ") > span > a";

    private static CategoryCrawler crawler;

//    public static void main(String[] args) throws Exception {
//
//        Document doc = Jsoup.connect(Books_QUERY_URL).userAgent(USER_AGENT).timeout(100000).maxBodySize(0).get();
////        System.out.println(doc.toString());
//        for (int i = 1; i <= 32; i++) {
//            Element ele = doc.select("#leftNav > ul:nth-child(5) > ul > div > li:nth-child(" + String.valueOf(i) + ") > span > a").first();
//
//            System.out.println(ele.toString());
//            if (ele != null) {
//                String detailUrl = ele.attr("href");
//                System.out.println("detail url = " + detailUrl);
//                String title = doc.select("#leftNav > ul:nth-child(5) > ul > div > li:nth-child(" + String.valueOf(i) + ") > span > a > span").text();
//                System.out.println("title = " + title);
//            }
////        }
//
//        }
//    }
}
