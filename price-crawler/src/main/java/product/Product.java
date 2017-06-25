package product;

import lombok.Data;


public class Product{
    private String product_id;
    private String category;
    private String title;
    private String detail_url;
    private double price;
    private double old_price;
    private double percentage;

    public Product(){
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail_url() {
        return detail_url;
    }

    public void setDetail_url(String detail_url) {
        this.detail_url = detail_url;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOld_price() {
        return old_price;
    }

    public void setOld_price(double old_price) {
        this.old_price = old_price;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

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
