package com.example.sdern_000.restosam;

import com.example.sdern_000.restosam.Menu.Menu;
import com.example.sdern_000.restosam.Order.Order;
import com.example.sdern_000.restosam.Restaurant.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sdern_000 on 05.12.2016.
 */

public class DataBase {

    private static final String[] restaurantNames = {
            "chicks",
            "circles",
            "foodie",
            "grill",
            "seafood"
    };
    private static final Integer[] restaurantImages = {
            R.drawable.chicks,
            R.drawable.circles,
            R.drawable.foodie,
            R.drawable.grill,
            R.drawable.seafood
    };

    private static final String[] dishNames = new String[] {
            "Пицца мясная",
            "Итальянская пицца",
            "Суши Лосось",
            "Запеченные Суши",
            "Суши с крабом",
            "Суп-пюре из грибов",
            "Борщ",
            "Жаркое со свининой",
            "Карбонад по-ммилански",
            "Свинина пикантная",
            "Картофельное пюре",
            "Гречка",
            "Салат Русский",
            "Помидоры фаршированные",
    };
    private static final Integer[] dishPrices = new Integer[] {
            299,
            299,
            69,
            79,
            69,
            69,
            54,
            94,
            110,
            104,
            45,
            25,
            53,
            58
    };
    private static final Integer[] dishPictures = new Integer[] {
            R.drawable.meatpizza,
            R.drawable.italianpizza,
            R.drawable.sushi1,
            R.drawable.sushi2,
            R.drawable.sushi3,
            R.drawable.mushroomsoup,
            R.drawable.borsh,
            R.drawable.garkoe,
            R.drawable.karbonad,
            R.drawable.pikantaya,
            R.drawable.pure,
            R.drawable.grechka,
            R.drawable.russiansalat,
            R.drawable.pomidory
    };


    private static DataBase instance;

    private DataBase() {

    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }


    private Map<String, Restaurant> restaurantMap;
    private Set<Order> orderSet;
    private Set<Book> bookSet;

    public boolean OrdersExist() {
        return (orderSet != null);
    }

    public boolean BooksExist() {
        return (bookSet != null);
    }

    public Restaurant GetRestaurant(String name) {
        return restaurantMap.get(name);
    }

    public void BooksInitialize() {
        bookSet = new HashSet<Book>();
    }

    public void OrdersInitialize() {
        orderSet = new HashSet<Order>();
    }

    public void AddOrder(Order order) {
        orderSet.add(order);
    }

    public void AddBook(Book book) {
        bookSet.add(book);
    }

    public void Load() {
        ArrayList<Dish> dishes = new ArrayList<Dish>();
        for (int i = 0; i < dishNames.length; i++) {
            dishes.add(new Dish(dishNames[i],
                    dishPrices[i],
                    dishPictures[i]));
        }

        Menu menu1 = new Menu(new String[] {
                "Пицца", "Суши"
        });
        menu1.AddDish("Пицца", dishes.get(0));
        menu1.AddDish("Пицца", dishes.get(1));
        menu1.AddDish("Суши", dishes.get(2));
        menu1.AddDish("Суши", dishes.get(3));
        menu1.AddDish("Суши", dishes.get(4));

        Menu menu2 = new Menu(new String[] {
                "Первые", "Вторые", "Гарниры", "Салаты"
        });
        menu2.AddDish("Первые", dishes.get(5));
        menu2.AddDish("Первые", dishes.get(6));
        menu2.AddDish("Вторые", dishes.get(7));
        menu2.AddDish("Вторые", dishes.get(8));
        menu2.AddDish("Вторые", dishes.get(9));
        menu2.AddDish("Гарниры", dishes.get(10));
        menu2.AddDish("Гарниры", dishes.get(11));
        menu2.AddDish("Салаты", dishes.get(12));
        menu2.AddDish("Салаты", dishes.get(13));


        Menu menu3 = new Menu(new String[] {
                "Пицца", "Суши", "Первые", "Вторые", "Гарниры", "Салаты"
        });
        menu3.AddDish("Пицца", dishes.get(0));
        menu3.AddDish("Пицца", dishes.get(1));
        menu3.AddDish("Суши", dishes.get(2));
        menu3.AddDish("Суши", dishes.get(3));
        menu3.AddDish("Суши", dishes.get(4));
        menu3.AddDish("Первые", dishes.get(5));
        menu3.AddDish("Первые", dishes.get(6));
        menu3.AddDish("Вторые", dishes.get(7));
        menu3.AddDish("Вторые", dishes.get(8));
        menu3.AddDish("Вторые", dishes.get(9));
        menu3.AddDish("Гарниры", dishes.get(10));
        menu3.AddDish("Гарниры", dishes.get(11));
        menu3.AddDish("Салаты", dishes.get(12));
        menu3.AddDish("Салаты", dishes.get(13));

        restaurantMap = new HashMap<String, Restaurant>();
        Restaurant chicks = new Restaurant(restaurantNames[0], restaurantImages[0]);
        chicks.setMenu(menu1);
        restaurantMap.put(restaurantNames[0],chicks );

        Restaurant cicrles = new Restaurant(restaurantNames[1], restaurantImages[1]);
        cicrles.setMenu(menu2);
        restaurantMap.put(restaurantNames[1],cicrles );

        Restaurant foodie = new Restaurant(restaurantNames[2], restaurantImages[2]);
        foodie.setMenu(menu2);
        restaurantMap.put(restaurantNames[2],foodie );

        Restaurant grill = new Restaurant(restaurantNames[3], restaurantImages[3]);
        grill.setMenu(menu3);
        restaurantMap.put(restaurantNames[3],grill );

        Restaurant seafood = new Restaurant(restaurantNames[4], restaurantImages[4]);
        seafood.setMenu(menu1);
        restaurantMap.put(restaurantNames[4],seafood );
    }

    public String[] GetRestaurantsNames() {
        return restaurantNames;
    }

    public Integer[] GetRestaurantsImages() {
        return restaurantImages;
    }
}
