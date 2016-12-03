package com.example.sdern_000.restosam.Order;

import com.example.sdern_000.restosam.Dish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sdern_000 on 03.12.2016.
 */

public class Order {

    public static Map<String, Order> orderMap;

    Map<String,Dish> dishes;
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
}
