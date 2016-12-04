package com.example.sdern_000.restosam.Menu;

import android.content.Context;

import com.example.sdern_000.restosam.Dish;
import com.example.sdern_000.restosam.ExpListAdapter;
import com.example.sdern_000.restosam.Menu.Menu;
import com.example.sdern_000.restosam.R;

import java.util.ArrayList;
import java.util.Map;

public class MenuAdapterHelper {
    final String GROUP_NAME = "groupName";
    final String DISH_NAME = "dishName";
    final String DISH_PRICE = "dishPrice";
    final String DISH_PICT = "dishPict";

    String[] groups = new String[] {"Основные блюда", "Вторые блюда", "Десерты"};


    String[] FirstDishes = new String[] {"Борщ", "Солянка", "Суп-пюре", "Суп куриный"};
    Integer[] FirstDishesPrices = new Integer[] {45,34,60,42};
    Integer[] FirstDishesPictures = new Integer[] {R.drawable.grill,R.drawable.chicks,R.drawable.foodie,R.drawable.circles};
    String[] SecondDishes = new String[] {"Гречка с котлетами", "Жареная картошка", "Плов"};
    Integer[] SecondDishesPrices = new Integer[] {98,67,42};
    Integer[] SecondDishesPictures = new Integer[] {R.drawable.kek,R.drawable.lol,R.drawable.seafood};
    String[] Dessert = new String[] {"Печенье", "Вафли", "Мед", "Торт"};
    Integer[] DessertPrices = new Integer[] {41,34,61,32};
    Integer[] DessertPictures = new Integer[] {R.drawable.chicks, R.drawable.circles, R.drawable.foodie, R.drawable.grill};

    ArrayList<Map<String, String>> groupData;
    ArrayList<ArrayList<Map<String, Object>>> childData;

    Context ctx;
    Menu menu;
    MenuAdapterHelper(Context _ctx) {
        ctx = _ctx;
    }

    ExpListAdapter adapter;

    ExpListAdapter getAdapter() {
        menu = new Menu(groups);
        for (int i = 0 ; i < FirstDishes.length; i++) {
            Dish dish = new Dish(FirstDishes[i], FirstDishesPrices[i], FirstDishesPictures[i] );
            menu.AddDish("Основные блюда", dish);
        }
        for (int i = 0 ; i < SecondDishes.length; i++) {
            Dish dish = new Dish(SecondDishes[i], SecondDishesPrices[i], SecondDishesPictures[i] );
            menu.AddDish("Вторые блюда", dish);
        }
        for (int i = 0 ; i < Dessert.length; i++) {
            Dish dish = new Dish(Dessert[i], DessertPrices[i], DessertPictures[i] );
            menu.AddDish("Десерты", dish);
        }

        groupData = menu.GetGroupData();
        String groupFrom[] = new String[] {GROUP_NAME};
        int groupTo[] = new int[] {R.id.textGroup};
        childData = menu.GetChildData();
        String childFrom[] = new String[] {DISH_NAME, DISH_PRICE, DISH_PICT};
        int childTo[] = new int[] {R.id.dishName, R.id.dishPrice, R.id.dishPicture};

        adapter = new ExpListAdapter(
                ctx,
                groupData,
                R.layout.menu_group_layout,
                groupFrom,
                groupTo,
                childData,
                R.layout.menu_item_layout,
                childFrom,
                childTo);

        return adapter;
    }

    String getGroupText(int groupPos) {
        return ((Map<String,String>)(adapter.getGroup(groupPos))).get(GROUP_NAME);
    }

    ArrayList<Object> getChildText(int groupPos, int childPos) {
        ArrayList<Object> attr = new ArrayList<Object>();
        attr.add(((Map<String,String>)(adapter.getChild(groupPos, childPos))).get(DISH_NAME));
        attr.add(((Map<String,String>)(adapter.getChild(groupPos, childPos))).get(DISH_PRICE));
        attr.add(((Map<String,String>)(adapter.getChild(groupPos, childPos))).get(DISH_PICT));
        return attr;
    }

    String getGroupChildText(int groupPos, int childPos) {
        return getGroupText(groupPos) + " " +  getChildText(groupPos, childPos);
    }

    public Dish GetDish(String name) {
        return menu.GetDish(name);
    }
}