package com.example.sdern_000.restosam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import com.example.sdern_000.restosam.Menu.MenuActivity;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button menu_button;
    Button book_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu_button = (Button) findViewById(R.id.menu_button);
        menu_button.setOnClickListener(this);

        book_button = (Button) findViewById(R.id.book_button);
        book_button.setOnClickListener(this);
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
                startActivity(intent);
                break;

            case R.id.book_button:
                intent = new Intent(this, BookActivity.class);
                startActivity(intent);
                break;
        }

    }
}
