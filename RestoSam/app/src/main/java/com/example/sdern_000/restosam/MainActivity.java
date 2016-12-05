package com.example.sdern_000.restosam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.sdern_000.restosam.Restaurant.SearchRestaurantActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DataBase dataBase = DataBase.getInstance();
        dataBase.Load();
    }


    public void OnButtonClick(View view) {
        Intent intent = new Intent(this, SearchRestaurantActivity.class);
        startActivity(intent);
    }
}
