import lombok.Data;

import java.util.HashSet;

@Data
public class Category {
    private int categoryId;
    private String category_name;
    private String product_lists_url;
    private int priority;

    public Category(int categoryId, String category_name, String product_lists_url, int priority) {
        this.categoryId = categoryId;
        this.category_name = category_name;
        this.product_lists_url = product_lists_url;
        this.priority = priority;
    }
}
