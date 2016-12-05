package com.example.sdern_000.restosam;

import com.example.sdern_000.restosam.Order.Order;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * Created by Nikita Rykov on 04.12.2016.
 */

public class Book {
    private Date date;
    private String restaurantName;
//    public static Set<Book> bookSet;
    public static Book bufferBook;

    Book(Date date) {
        this.date = date;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
