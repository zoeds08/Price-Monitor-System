package product;

import lombok.Data;

@Data
public class Product{
    private String product_id;
    private String category;
    private String title;
    private String detail_url;
    private double price;
    private double old_price;
    private double percentage;

    public Product(){}

    public Product(String product_id, String category, String title, String detail_url, double price, double old_price, double percentage) {
        this.product_id = product_id;
        this.category = category;
        this.title = title;
        this.detail_url = detail_url;
        this.price = price;
        this.old_price = old_price;
        this.percentage = percentage;
    }
}
