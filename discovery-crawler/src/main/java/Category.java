import lombok.Data;

import java.util.HashSet;

@Data
public class Category {
    public int categoryId;
    public String category_name;
    public String product_lists_url;
    public int priority;

    public Category(int categoryId, String category_name, String product_lists_url, int priority) {
        this.categoryId = categoryId;
        this.category_name = category_name;
        this.product_lists_url = product_lists_url;
        this.priority = priority;
    }
}
