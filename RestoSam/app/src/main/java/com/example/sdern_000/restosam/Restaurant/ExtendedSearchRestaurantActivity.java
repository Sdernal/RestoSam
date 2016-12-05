package com.example.sdern_000.restosam.Restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.sdern_000.restosam.R;
import com.example.sdern_000.restosam.Restaurant.RestaurantSearchResultActivity;

import java.util.ArrayList;
import java.util.List;

public class ExtendedSearchRestaurantActivity extends AppCompatActivity {
    private List<String> distance;
    private List<String> price;
    private String[] dishes = { "Американская кухня",
            "Грузинская кухня",
            "Итальянская кухня",
            "Русская кухня",
            "Японская кухня",
            "Вегетарианское меню",
            "Фастфуд"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extended_search_restaurant);
        distance = new ArrayList<>();
        for(Integer i = 1; i <= 100; ++i) {
            distance.add(i.toString());
        }
        ArrayAdapter<String> distanceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, distance);
        distanceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner distanceSpinner = (Spinner) findViewById(R.id.distance_spinner);
        distanceSpinner.setSelected(false);
        distanceSpinner.setAdapter(distanceAdapter);
        price = new ArrayList<>();
        for(Integer i = 100; i <= 10000; i += 100) {
            price.add(i.toString());
        }
        ArrayAdapter<String> priceAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, price);
        priceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner priceSpinner = (Spinner) findViewById(R.id.price_spinner);
        priceSpinner.setSelected(false);
        priceSpinner.setAdapter(priceAdapter);
        ArrayAdapter<String> dishAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dishes);
        dishAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner dishSpinner = (Spinner) findViewById(R.id.dish_spinner);
        dishSpinner.setSelected(false);
        dishSpinner.setAdapter(dishAdapter);
    }

    public void OnButtonClick(View view) {
        /*
        Здесь поисковая система должна использовать критерии, заданные пользователем,
        для поиска подходящих ресторанов.
        Мы же выведем рандомные рестораны как результат поиска.
         */
        Intent intent = new Intent(this, RestaurantSearchResultActivity.class);
        startActivity(intent);
    }
}
