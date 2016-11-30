package com.example.sdern_000.restosam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

public class MenuActivity extends Activity {

    // названия компаний (групп)
    String[] groups = new String[] {"Основные блюда", "Вторые блюда", "Десерты"};

    // названия телефонов (элементов)
    String[] FirstDishes = new String[] {"Борщ", "Солянка", "Суп-пюре", "Суп куриный"};
    String[] SecondDishes = new String[] {"Гречка с котлетами", "Жареная картошка", "Плов"};
    String[] Dessert = new String[] {"Печенье", "Вафли", "Мед", "Тор"};

    // коллекция для групп
    ArrayList<Map<String, String>> groupData;

    // коллекция для элементов одной группы
    ArrayList<Map<String, String>> childDataItem;

    // общая коллекция для коллекций элементов
    ArrayList<ArrayList<Map<String, String>>> childData;
    // в итоге получится childData = ArrayList<childDataItem>

    // список атрибутов группы или элемента
    Map<String, String> m;

    ExpandableListView elvMain;


    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // заполняем коллекцию групп из массива с названиями групп
        groupData = new ArrayList<Map<String, String>>();
        for (String group : groups) {
            // заполняем список атрибутов для каждой группы
            m = new HashMap<String, String>();
            m.put("groupName", group); // имя компании
            groupData.add(m);
        }

        // список атрибутов групп для чтения
        String groupFrom[] = new String[] {"groupName"};
        // список ID view-элементов, в которые будет помещены атрибуты групп
        int groupTo[] = new int[] {android.R.id.text1};


        // создаем коллекцию для коллекций элементов
        childData = new ArrayList<ArrayList<Map<String, String>>>();

        // создаем коллекцию элементов для первой группы
        childDataItem = new ArrayList<Map<String, String>>();
        // заполняем список атрибутов для каждого элемента
        for (String dish : FirstDishes) {
            m = new HashMap<String, String>();
            m.put("dishName", dish);
            m.put("dishPrice", "kek");
//            m.put("dishPicture", "@drawable/kek");
            childDataItem.add(m);
        }
        // добавляем в коллекцию коллекций
        childData.add(childDataItem);

        // создаем коллекцию элементов для второй группы
        childDataItem = new ArrayList<Map<String, String>>();
        for (String dish : SecondDishes) {
            m = new HashMap<String, String>();
            m.put("dishName", dish);
            m.put("dishPrice", "kek1");
//            m.put("dishPicture", "@drawable/kek");
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        // создаем коллекцию элементов для третьей группы
        childDataItem = new ArrayList<Map<String, String>>();
        for (String dish : Dessert) {
            m = new HashMap<String, String>();
            m.put("dishName", dish);
            m.put("dishPrice", "kek2");
//            m.put("dishPicture", "@drawable/lol");
            childDataItem.add(m);
        }
        childData.add(childDataItem);

        // список атрибутов элементов для чтения
        String childFrom[] = new String[] {"dishName", "dishPrice"};
        // список ID view-элементов, в которые будет помещены атрибуты элементов
        int childTo[] = new int[] {R.id.dishName, R.id.dishPrice};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                R.layout.menu_item_layout,
                childFrom,
                childTo);

        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        elvMain.setAdapter(adapter);
    }
}