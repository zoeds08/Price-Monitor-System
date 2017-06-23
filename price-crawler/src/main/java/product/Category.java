package product;

import lombok.Data;

import java.util.HashSet;

@Data
public class Category {
    public int categoryId;
    public String category_name;
    public String product_lists_url;
    public int priority;

    public Category(){}

    public Category(int categoryId, String category_name, String product_lists_url, int priority) {
        this.categoryId = categoryId;
        this.category_name = category_name;
        this.product_lists_url = product_lists_url;
        this.priority = priority;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getProduct_lists_url() {
        return product_lists_url;
    }

    public void setProduct_lists_url(String product_lists_url) {
        this.product_lists_url = product_lists_url;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
