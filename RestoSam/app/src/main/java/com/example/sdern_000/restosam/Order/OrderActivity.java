package com.example.sdern_000.restosam.Order;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sdern_000.restosam.Dish;
import com.example.sdern_000.restosam.R;
import com.example.sdern_000.restosam.ResultActivity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import static com.example.sdern_000.restosam.Order.Order.bufferOrder;
import static com.example.sdern_000.restosam.Order.Order.orderSet;

public class OrderActivity extends AppCompatActivity {

    Map<String,Dish> dishes;
    ArrayList<String> dishnames;
    ArrayList<Integer> dishprices;
    ArrayList<Integer> dishpictures;
    String restaurantName;
    Order order;
    final int DIALOG = 1;
    LinearLayout view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Заказ");
        setContentView(R.layout.activity_order);
        if (orderSet == null) {
            orderSet = new HashSet<>();
        }
        restaurantName = getIntent().getStringExtra("name");
        order = bufferOrder;
        dishes = order.GetDishes();
        dishnames =  new ArrayList<String>();
        dishprices = new ArrayList<Integer>();
        dishpictures = new ArrayList<Integer>();
        Integer result = 0;
        for (Dish dish : dishes.values()) {
            dishnames.add(dish.GetName());
            dishprices.add(dish.GetPrice());
            dishpictures.add(dish.GetPictureId());
            result += dish.GetPrice();
        }
        OrderListAdapter adapter = new OrderListAdapter(this, dishnames, dishprices, dishpictures);
        ListView list=(ListView)findViewById(R.id.order_items);
        list.setAdapter(adapter);

        final TextView order_result = (TextView) findViewById(R.id.order_result);
        order_result.setText("Итого: " + result.toString() + "р");
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String Slecteditem = dishnames.get(position);
                Toast.makeText(getApplicationContext(), Slecteditem, Toast.LENGTH_SHORT).show();

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String Slecteditem = dishnames.get(position);
                order.RemoveDish(Slecteditem);
                UpdateOrder();
                Toast.makeText(getApplicationContext(), "Блюдо " + Slecteditem + " удалено", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        Button accept_button = (Button) findViewById(R.id.order_button_accept);
        accept_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);

        view = (LinearLayout) getLayoutInflater()
                .inflate(R.layout.dialog_order, null);

        adb.setView(view);
        adb.setTitle("Заказ: ");
        TextView order_check = (TextView) view.findViewById(R.id.order_check);
        String buffer = new String();
        Integer result = 0;
        for (int i = 0; i < dishnames.size(); i++) {
            buffer += dishnames.get(i);
            buffer += ": \t";
            buffer += dishprices.get(i).toString();
            buffer += "р\n";
            result +=dishprices.get(i);
        }
        buffer += "\n";
        buffer += "Итого: \t";
        buffer += result.toString();
        buffer += "р\n";
        order_check.setText(buffer);

        adb.setPositiveButton("Принять", myClickListener);
        adb.setNegativeButton("Отмена",myClickListener);
        return adb.create();
    }

    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                // положительная кнопка
                case Dialog.BUTTON_POSITIVE:
                    orderSet.add(order);
                    Intent intent = new Intent(OrderActivity.this, ResultActivity.class);
                    intent.putExtra("name", restaurantName);
                    intent.putExtra("result", "order");
                    startActivity(intent);
                    break;
                case Dialog.BUTTON_NEGATIVE:
                    break;

            }
        }
    };

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        if (id == DIALOG) {
            TextView order_check = (TextView) dialog.getWindow().findViewById(R.id.order_check);

            String buffer = new String();
            Integer result = 0;
            for (int i = 0; i < dishnames.size(); i++) {
                buffer += dishnames.get(i);
                buffer += ": \t";
                buffer += dishprices.get(i).toString();
                buffer += "р\n";
                result +=dishprices.get(i);
            }
            buffer += "\n";
            buffer += "Итого: \t";
            buffer += result.toString();
            buffer += "р\n";

            order_check.setText(buffer);
        }
    }

    void UpdateOrder() {
        dishes = order.GetDishes();
        dishnames =  new ArrayList<String>();
        dishprices = new ArrayList<Integer>();
        dishpictures = new ArrayList<Integer>();
        Integer result = 0;
        for (Dish dish : dishes.values()) {
            dishnames.add(dish.GetName());
            dishprices.add(dish.GetPrice());
            dishpictures.add(dish.GetPictureId());
            result += dish.GetPrice();
        }

        OrderListAdapter adapter = new OrderListAdapter(this, dishnames, dishprices, dishpictures);
        ListView list=(ListView)findViewById(R.id.order_items);
        list.setAdapter(adapter);

        final TextView order_result = (TextView) findViewById(R.id.order_result);
        order_result.setText("Итого: " + result.toString() + "р");
    }
}
