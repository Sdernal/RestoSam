package com.example.sdern_000.restosam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sdern_000 on 02.12.2016.
 */

public class Menu {
    Map<String,ArrayList<String>> categories;
    Map<String,Dish> dishMap;

    final String GROUP_NAME = "groupName";
    final String DISH_NAME = "dishName";
    final String DISH_PRICE = "dishPrice";
    final String DISH_PICT = "dishPict";

    public Menu(String[] ctgs) {
        categories = new HashMap<String,ArrayList<String>>();
        dishMap = new HashMap<String,Dish>();
        for (String ctg : ctgs) {
            ArrayList<String> m = new ArrayList<String>();
            categories.put(ctg,m);
        }
    }

    public void AddDish(String category, Dish dish) {
        ArrayList<String> m = categories.get(category);
        m.add(dish.GetName());
        dishMap.put(dish.GetName(), dish);
    }

    public ArrayList<Map<String, String>> GetGroupData() {
        ArrayList<Map<String, String>> groupData =  new ArrayList<Map<String,String>>();
        for (String group : categories.keySet()) {
            HashMap<String, String> m = new HashMap<String, String>();
            m.put(GROUP_NAME, group);
            groupData.add(m);
        }
        return groupData;
    }

    public  ArrayList<ArrayList<Map<String, Object>>> GetChildData() {
        ArrayList<ArrayList<Map<String, Object>>> childData = new ArrayList<ArrayList<Map<String, Object>>>();

        for (String group : categories.keySet()) {
            ArrayList<Map<String, Object>> childDataItem = new ArrayList<Map<String, Object>>();
            ArrayList<String> groupData = categories.get(group);
            for (String child : groupData) {
                Dish dish_ = dishMap.get(child);
                HashMap<String, Object> m = new HashMap<String, Object>();
                m.put(DISH_NAME, dish_.GetName());
                m.put(DISH_PRICE, "цена: " + dish_.GetPrice().toString());
                m.put(DISH_PICT, dish_.GetPictureId());
                childDataItem.add(m);
            }
            childData.add(childDataItem);
        }
        return childData;
    }

}
