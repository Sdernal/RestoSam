package com.example.sdern_000.restosam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.sdern_000.restosam.Menu.MenuActivity;

public class RestaurantActivity extends AppCompatActivity implements View.OnClickListener {
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        ImageView imageView = (ImageView) findViewById(R.id.main_picture);

        Button menu_button = (Button) findViewById(R.id.menu_button);
        menu_button.setOnClickListener(this);

        Button book_button = (Button) findViewById(R.id.book_button);
        book_button.setOnClickListener(this);

        Intent intent = getIntent();
        int imageId = intent.getIntExtra("logo", 0);
        imageView.setImageResource(imageId);

        String name = intent.getStringExtra("name");
        setTitle(name);
    }


    public void OnButtonClick(View view) {
        Intent intent = new Intent(this, SearchRestaurantActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.menu_button:
                intent = new Intent(this, MenuActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
                break;

            case R.id.book_button:
                intent = new Intent(this, BookActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
                break;
        }

    }
}