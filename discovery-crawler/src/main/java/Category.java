import lombok.Data;

import java.util.HashSet;

@Data
public class Category {
    public int categoryId;
    public String category_name;
    public HashSet<String> product_list_url;
    public int priority;

    public Category(int categoryId, String category_name, HashSet<String> product_list_url, int priority) {
        this.categoryId = categoryId;
        this.category_name = category_name;
        this.product_list_url = product_list_url;
        this.priority = priority;
    }
}
