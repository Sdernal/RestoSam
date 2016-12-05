package com.example.sdern_000.restosam.Restaurant;

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

import com.example.sdern_000.restosam.R;

public class SearchRestaurantActivity extends AppCompatActivity {
    ListView list;
    RestaurantListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_restaurant);
        adapter = new RestaurantListAdapter(this);
        EditText editText = (EditText)findViewById(R.id.search);
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence query, int start,
                                      int before, int count) {
                adapter.getFilter().filter(query);
            }
        });
        TextView textView = (TextView)findViewById(R.id.extend_search);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ExtendedSearchRestaurantActivity.class);
                startActivity(intent);
            }
        });
        list = (ListView)findViewById(R.id.restaurant_list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(SearchRestaurantActivity.this, RestaurantActivity.class);
                intent.putExtra("name", adapter.getRestaurant(position).getName());
                intent.putExtra("logo", adapter.getRestaurant(position).getImageId());
                startActivity(intent);
            }
        });
        list.setTextFilterEnabled(true);
    }
}

