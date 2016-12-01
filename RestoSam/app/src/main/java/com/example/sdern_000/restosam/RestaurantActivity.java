package com.example.sdern_000.restosam;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class RestaurantActivity extends AppCompatActivity {
    ListView list;

    private final String[] itemname = {
            "chicks",
            "circles",
            "foodie",
            "grill",
            "seafood"
    };
    private final Integer[] imgid = {
            R.drawable.chicks,
            R.drawable.circles,
            R.drawable.foodie,
            R.drawable.grill,
            R.drawable.seafood
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        RestaurantListAdapter adapter = new RestaurantListAdapter(this, itemname, imgid);
        list=(ListView)findViewById(R.id.restaurant_list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String Slecteditem = itemname[position];
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
