package com.example.sdern_000.restosam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class RestaurantSearchResultActivity extends AppCompatActivity {
    ListView list;
    RestaurantListAdapter adapter;

    private String getFoundText(int size) {
        switch (size % 10) {
            case 1:
                return "Найден " + size + " ресторан:";
            case 2:
            case 3:
            case 4:
                return "Найдено " + size + " ресторана:";
            default:
                return "Найдено " + size + " ресторанов:";
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_search_result);
        adapter = new RestaurantListAdapter(this);
        TextView textView = (TextView)findViewById(R.id.found);
        list = (ListView)findViewById(R.id.found_restaurant_list);
        list.setAdapter(adapter);
        int found = adapter.extendedSearchFilter();
        textView.setText(getFoundText(found));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String Slecteditem = "" + position;
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });
        list.setTextFilterEnabled(true);
    }
}
