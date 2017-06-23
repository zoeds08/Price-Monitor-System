package crawler;


import com.rabbitmq.client.Channel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import product.Category;
import product.Product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.*;

public class ProductCrawler {

//    private static final String AMAZON_QUERY_URL = "https://www.amazon.com/s/ref=nb_sb_noss?field-keywords=";
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36";
    private final String authUser = "bittiger";
    private final String authPassword = "cs504";
    private List<String> proxyList;
    private List<String> urlList;
    private List<String> titleList;
    private List<String> priceList;
    private HashSet crawledUrl;
    private Channel errorChannel;
    private String errorChannelName;

    BufferedWriter logBFWriter;

    private int id = 1;
    private int index = 0;

    public ProductCrawler(String proxy_file, Channel errChannel, String errChannelName) {
        crawledUrl = new HashSet();
        initProxyList(proxy_file);

        initHtmlSelector();

        errorChannel = errChannel;
        errorChannelName = errChannelName;
    }


    //raw url: https://www.amazon.com/KNEX-Model-Building-Set-Engineering/dp/B00HROBJXY/ref=sr_1_14/132-5596910-9772831?ie=UTF8&qid=1493512593&sr=8-14&keywords=building+toys
    //normalizedUrl: https://www.amazon.com/KNEX-Model-Building-Set-Engineering/dp/B00HROBJXY
    private String normalizeUrl(String url) {
        int i = url.indexOf("ref");
        System.out.println("ref index: " + i);
        String normalizedUrl = url.substring(0, i - 1);
        return normalizedUrl;
    }

    private void initProxyList(String proxy_file) {
        proxyList = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(proxy_file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String ip = fields[0].trim();
                proxyList.add(ip);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Authenticator.setDefault(
                new Authenticator() {
                    @Override
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                authUser, authPassword.toCharArray());
                    }
                }
        );

        System.setProperty("http.proxyUser", authUser);
        System.setProperty("http.proxyPassword", authPassword);
        System.setProperty("socksProxyPort", "61336"); // set proxy port
    }

    private void initHtmlSelector() {
        urlList = new ArrayList<String>();
        urlList.add(" > div > div > div > div.a-fixed-left-grid-col.a-col-right > div.a-row.a-spacing-small > div:nth-child(1) > a");
        urlList.add(" > div > div.a-row.a-spacing-none > div:nth-child(1) > a");
        urlList.add(" > div > div:nth-child(3) > div:nth-child(1) > a");

        titleList = new ArrayList<String>();
        titleList.add(" > div > div > div > div.a-fixed-left-grid-col.a-col-right > div.a-row.a-spacing-small > div:nth-child(1)  > a > h2");
        titleList.add(" > div > div > div > div.a-fixed-left-grid-col.a-col-right > div.a-row.a-spacing-small > a > h2");
        titleList.add(" > div > div.a-row.a-spacing-none > div:nth-child(1) > a > h2");
        titleList.add(" > div > div:nth-child(3) > div > a > h2");


        priceList = new ArrayList<String>();
        //#refinements > div.categoryRefinementsSection > ul.forExpando > li:nth-child(1) > a > span.boldRefinementLink
        priceList.add(" > div > div > div > div.a-fixed-left-grid-col.a-col-right > div:nth-child(2) > div.a-column.a-span7 > div:nth-child(2) > a > span");
        priceList.add(" > div > div > div > div.a-fixed-left-grid-col.a-col-right > div:nth-child(3) > div.a-column.a-span7 > div:nth-child(2) > a > span");
//        priceList.add(" #a-autoid-" + 0 + "-announce > span");//Arts
        priceList.add(" > div > div:nth-child(4) > a > span.a-color-base.sx-zero-spacing");
        priceList.add(" > div > div > div > div.a-fixed-left-grid-col.a-col-right > div:nth-child(4) > div.a-column.a-span7 > div.a-row.a-spacing-none > a > span");
        priceList.add(" > div > div > div > div.a-fixed-left-grid-col.a-col-right > div:nth-child(2) > div.a-column.a-span7 > table > tbody > tr.a-spacing-none.s-table-twister-row-no-border.s-table-twister-row > td:nth-child(2) > div > a > span");
        priceList.add(" > div > div > div > div.a-fixed-left-grid-col.a-col-right > div:nth-child(2) > div.a-column.a-span7 > table > tbody > tr:nth-child(3) > td:nth-child(2) > div > a > span");
        priceList.add(" > div > div > div > div.a-fixed-left-grid-col.a-col-right > div:nth-child(2) > div.a-column.a-span7 > div.a-row.a-spacing-top-mini.a-spacing-mini > div:nth-child(2) > a > span.a-size-base.a-color-base");
        priceList.add(" > div > div > div > div.a-fixed-left-grid-col.a-col-right > div:nth-child(2) > div.a-column.a-span7 > div.a-row.a-spacing-top-mini.a-spacing-mini > div > a > span.a-size-base.a-color-base");


        
    }

    private void initLog(String log_path) {
        /*
        try {
            File log = new File(log_path);
            // if file doesnt exists, then create it
            if (!log.exists()) {
                log.createNewFile();
            }
            FileWriter fw = new FileWriter(log.getAbsoluteFile());
            logBFWriter = new BufferedWriter(fw);

        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    private void setProxy() {
        //rotate
        if (index == proxyList.size()) {
            index = 0;
        }
        String proxy = proxyList.get(index);
        System.setProperty("socksProxyHost", proxy); // set proxy server
        index++;
    }

    private void testProxy() {
        System.setProperty("socksProxyHost", "199.101.97.149"); // set proxy server
        System.setProperty("socksProxyPort", "61336"); // set proxy port
        String test_url = "http://www.toolsvoid.com/what-is-my-ip-address";
        try {
            Document doc = Jsoup.connect(test_url).userAgent(USER_AGENT).timeout(10000).get();
            String iP = doc.select("body > section.articles-section > div > div > div > div.col-md-8.display-flex > div > div.table-responsive > table > tbody > tr:nth-child(1) > td:nth-child(2) > strong").first().text(); //get used IP.
            System.out.println("IP-Address: " + iP);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Product> GetProductBasicInfoByURL(Category category) {
        List<Product> products = new ArrayList<>();
        try {
            if (false) {
                testProxy();
                return products;
            }

            setProxy();

            String category_name = category.category_name;
            String url = category.product_lists_url;

//            HashMap<String,String> headers = new HashMap<String,String>();
//            headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//            headers.put("Accept-Encoding", "gzip, deflate, sdch, br");
//            headers.put("Accept-Language", "en-US,en;q=0.8");
            Document doc = Jsoup.connect(url+"&field-keywords=-1234567").userAgent(USER_AGENT).timeout(100000).get();

//            System.out.println(doc.text().substring(0,10));
            Elements results = doc.select("li[data-asin]");
            System.out.println("num of results = " + results.size());


            for(int i = 0; i < results.size() ;i++) {
                Product product = new Product();

                product.product_id = id++;
                product.category = category_name;

                //detail url
                for (String pathUrl : urlList) {
                    String path = "#result_"+Integer.toString(i)+ pathUrl;
                    Element ele = doc.select(path).first();
                    if(ele != null) {
                        String detail_url = ele.attr("href");
                        System.out.println("detail = " + detail_url);
                        String normalizedUrl = normalizeUrl(detail_url);
                        if(crawledUrl.contains(normalizedUrl)) {
                            String errMsg = "crawled url:" + normalizedUrl;
                            errorChannel.basicPublish("", errorChannelName,null,errMsg.getBytes("UTF-8"));
                            continue;
                        }
                        crawledUrl.add(normalizedUrl);
                        System.out.println("normalized url  = " + normalizedUrl);
                        product.detail_url = normalizedUrl;
                        break;
                    } else {
                        //logBFWriter.write("cannot parse detail for query:" + query + ", title: " + ad.title);
                        //logBFWriter.newLine();
                        String errMsg = "cannot parse detail for one product of :" + category_name + ", title: " + product.title;
                        errorChannel.basicPublish("", errorChannelName,null,errMsg.getBytes("UTF-8"));
                        continue;
                    }
                }

                //title
                for (String title : titleList) {
                    String title_ele_path = "#result_"+Integer.toString(i)+ title;
                    Element title_ele = doc.select(title_ele_path).first();
                    if(title_ele != null) {
                        System.out.println("title = " + title_ele.text());
                        product.title = title_ele.text();
                        break;
                    }
                }

                if (product.title == "") {
                    logBFWriter.write("cannot parse title for one product of category: " + category_name);
                    logBFWriter.newLine();
                    continue;
                }

                //price
                for(String itemPrice: priceList){
                    String path = "#result_"+Integer.toString(i)+ itemPrice;
                    Element price_ele = doc.select(path).first();
                    if(price_ele!=null){
                        String price = price_ele.text();
                        price = price.substring(1);
                        String[] splits = price.split(" ");
                        if(splits.length>1) price = splits[0]+"."+splits[1];
                        try {
                            product.price = Double.parseDouble(price);
                        } catch (NumberFormatException ne) {
                            // TODO Auto-generated catch block
                            ne.printStackTrace();
                            //log
                        }
                        System.out.println("price = " + product.price );
                        break;
                    }else {
                        //logBFWriter.write("cannot parse detail for query:" + query + ", title: " + ad.title);
                        //logBFWriter.newLine();
//                        String errMsg = "cannot parse price for one product of :" + category_name + ", title: " + product.title;
//                        errorChannel.basicPublish("", errorChannelName,null,errMsg.getBytes("UTF-8"));
                        continue;
                    }
                }

                String path2 = "#a-autoid-" + Integer.toString(i) + "-announce > span";
                Element price_ele2 = doc.select(path2).first();
                if(price_ele2!=null){
                    String price = price_ele2.text();
                    String[] splits = price.split(" ");
                    if(splits.length>1) price = splits[0]+"."+splits[1];
                    try {
                        product.price = Double.parseDouble(price);
                    } catch (NumberFormatException ne) {
                        // TODO Auto-generated catch block
                        ne.printStackTrace();
                        //log
                    }
                    System.out.println("price = " + product.price );
                }else {
                    String errMsg = "cannot parse price for one product of :" + category_name + ", title: " + product.title;
                    errorChannel.basicPublish("", errorChannelName,null,errMsg.getBytes("UTF-8"));
                }

                product.old_price = 0;
                product.percentage = 1;

                if(product.detail_url!=null){
                    products.add(product);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return products;
    }
}
