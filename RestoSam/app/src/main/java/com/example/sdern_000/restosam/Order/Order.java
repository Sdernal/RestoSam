package com.example.sdern_000.restosam.Order;

import com.example.sdern_000.restosam.Dish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sdern_000 on 03.12.2016.
 */

public class Order {

//    public static Set<Order> orderSet;
    public static Order bufferOrder;

    private Map<String,Dish> dishes;
    private String restaurantName;

    public Order() {
        dishes = new HashMap<String,Dish>();
    }

    public void AddDish(Dish dish) {
        dishes.put(dish.GetName(),dish);
    }

    public  Map<String,Dish> GetDishes() {
        return  dishes;
    }

    public void RemoveDish(String name) {
        dishes.remove(name);
    }

    public void Clear() {
        dishes.clear();
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
}
