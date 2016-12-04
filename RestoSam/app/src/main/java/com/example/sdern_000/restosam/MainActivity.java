package com.example.sdern_000.restosam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import com.example.sdern_000.restosam.Menu.MenuActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void OnButtonClick(View view) {
        Intent intent = new Intent(this, SearchRestaurantActivity.class);
        startActivity(intent);
    }
}
