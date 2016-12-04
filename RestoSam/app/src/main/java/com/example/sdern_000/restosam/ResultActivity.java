package com.example.sdern_000.restosam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sdern_000.restosam.Menu.MenuActivity;

import static com.example.sdern_000.restosam.Book.bufferBook;
import static com.example.sdern_000.restosam.Order.Order.bufferOrder;

public class ResultActivity extends AppCompatActivity {
    String restaurantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        restaurantName = intent.getStringExtra("name");
        setTitle(restaurantName);
        String result = intent.getStringExtra("result");
        TextView textView = (TextView)findViewById(R.id.result_text);
        Button optButton = (Button)findViewById(R.id.optional_button);
        optButton.setVisibility(View.INVISIBLE);
        Button backButton = (Button)findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bufferBook = null;
                bufferOrder = null;
                Intent intent = new Intent(ResultActivity.this, SearchRestaurantActivity.class);
                startActivity(intent);
            }
        });
        if (result.equals("order")) {
            textView.setText("Ваш заказ принят");
            if (bufferBook == null) {
                optButton.setVisibility(View.VISIBLE);
                optButton.setText("Забронировать столик");
                optButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ResultActivity.this, BookActivity.class);
                        intent.putExtra("name", restaurantName);
                        startActivity(intent);
                    }
                });
            }
        } else {
            textView.setText("Ваша бронь принята");
            if (bufferOrder == null) {
                optButton.setVisibility(View.VISIBLE);
                optButton.setText("Перейти в меню");
                optButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ResultActivity.this, MenuActivity.class);
                        intent.putExtra("name", restaurantName);
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
