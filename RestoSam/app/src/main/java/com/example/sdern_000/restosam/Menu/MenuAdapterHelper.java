package com.example.sdern_000.restosam.Menu;

import android.content.Context;

import com.example.sdern_000.restosam.DataBase;
import com.example.sdern_000.restosam.Dish;
import com.example.sdern_000.restosam.R;

import java.util.ArrayList;
import java.util.Map;

public class MenuAdapterHelper {
    final String GROUP_NAME = "groupName";
    final String DISH_NAME = "dishName";
    final String DISH_PRICE = "dishPrice";
    final String DISH_PICT = "dishPict";


    ArrayList<Map<String, String>> groupData;
    ArrayList<ArrayList<Map<String, Object>>> childData;

    Context ctx;
    Menu menu;
    String restaurantName;
    DataBase dataBase;
    MenuAdapterHelper(Context _ctx, String restaurantName) {
        ctx = _ctx;
        this.restaurantName = restaurantName;
    }

    MenuAdapter adapter;

    MenuAdapter getAdapter() {
        dataBase = DataBase.getInstance();
        menu = dataBase.GetRestaurant(restaurantName).getMenu();

        groupData = menu.GetGroupData();
        String groupFrom[] = new String[] {GROUP_NAME};
        int groupTo[] = new int[] {R.id.textGroup};
        childData = menu.GetChildData();
        String childFrom[] = new String[] {DISH_NAME, DISH_PRICE, DISH_PICT};
        int childTo[] = new int[] {R.id.dishName, R.id.dishPrice, R.id.dishPicture};

        adapter = new MenuAdapter(
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