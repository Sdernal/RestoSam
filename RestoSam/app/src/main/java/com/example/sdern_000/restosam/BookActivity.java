package com.example.sdern_000.restosam;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static com.example.sdern_000.restosam.Book.bufferBook;

public class BookActivity extends AppCompatActivity {

    List<String> dates;
    List<String> places;
    String restaurantName;
    Random rand;
    View chosenPlace;
    int[] colors;
    Book book;
    DataBase dataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        dataBase = DataBase.getInstance();
        if (!dataBase.BooksExist()) {
            dataBase.BooksInitialize();
        }
        restaurantName = getIntent().getStringExtra("name");
        final View dialogView = View.inflate(this, R.layout.date_time_picker, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        dates = new ArrayList<>();
        for(int i = 10; i < 23; ++i) {
            dates.add(i + ":00");
            dates.add(i + ":30");
        }
        dates.add(23 + ":00");
        ArrayAdapter<String> dateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dates);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner dateSpinner = (Spinner) dialogView.findViewById(R.id.time_spinner);
        dateSpinner.setSelected(false);
        dateSpinner.setAdapter(dateAdapter);

        final TextView textView = (TextView)findViewById(R.id.choose_text);
        textView.setVisibility(View.INVISIBLE);
        final GridView gridView = (GridView)findViewById(R.id.grid);
        gridView.setVisibility(View.INVISIBLE);
        final Button button = (Button)findViewById(R.id.book_button);
        button.setVisibility(View.INVISIBLE);

        dialogView.findViewById(R.id.date_time_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SimpleDateFormat format = new SimpleDateFormat( "hh:mm" );
                try {
                    Date time = format.parse(dates.get(dateSpinner.getSelectedItemPosition()) );
                    book = new Book(time);
                    book.setRestaurantName(restaurantName);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                textView.setVisibility(View.VISIBLE);
                gridView.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                alertDialog.dismiss();
            }});
        alertDialog.setView(dialogView);
        alertDialog.show();
        places = new ArrayList<>();
        rand = new Random();
        colors = new int[25];
        for(int i  = 0; i < 25; ++i) {
            places.add("");
            if (rand.nextBoolean()) {
                colors[i] = Color.RED;
            } else {
                colors[i] = Color.GREEN;
            }
        }
        gridView.setAdapter(new ArrayAdapter<String>(this, R.layout.grid_place_item, places) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                view.getBackground().setColorFilter(colors[position], PorterDuff.Mode.DARKEN);

                return view;
            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                int color = colors[position];
                if (color == Color.RED) {
                    Toast.makeText(getApplicationContext(), "Этот столик занят. Выберите свободный столик.", Toast.LENGTH_SHORT).show();
                } else {
                    if (chosenPlace != null) {
                        chosenPlace.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.DARKEN);
                    }
                    chosenPlace = view;
                    chosenPlace.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.DARKEN);
                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (chosenPlace == null) {
                    Toast.makeText(getApplicationContext(), "Выберите столик.", Toast.LENGTH_SHORT).show();
                } else {
                    bufferBook = book;
                    dataBase.AddBook(book);
//                    bookSet.add(book);
                    Intent intent = new Intent(BookActivity.this, ResultActivity.class);
                    intent.putExtra("name", restaurantName);
                    intent.putExtra("result", "book");
                    startActivity(intent);
                }
            }
        });
        setTitle("Бронирование столика");
    }
}
