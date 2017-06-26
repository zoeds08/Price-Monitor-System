package product;

import lombok.Data;

@Data
public class User {
    private int id;
    private String email;
    private String password;
    private int notification_type;//[1: push; 2: pull+push]
    private String category_list;
    private double threshold;

    public User(){}

}
