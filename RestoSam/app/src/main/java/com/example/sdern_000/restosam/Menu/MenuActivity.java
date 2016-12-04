package com.example.sdern_000.restosam.Menu;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashMap;

import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sdern_000.restosam.ExpListAdapter;
import com.example.sdern_000.restosam.Order.Order;
import com.example.sdern_000.restosam.Order.OrderActivity;
import com.example.sdern_000.restosam.R;


public class MenuActivity extends AppCompatActivity {

    ExpandableListView elvMain;
    MenuAdapterHelper ah;
    ExpListAdapter adapter;
    int btn;
    LinearLayout view;
    TextView tvCount;
    ArrayList<Object> attr;
    Order order;
    final int DIALOG = 1;
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.menu_header);
        setContentView(R.layout.activity_menu);
        order = new Order();
        ah = new MenuAdapterHelper(this);
        adapter = ah.getAdapter();

        elvMain = (ExpandableListView) findViewById(R.id.elvMain);
        elvMain.setAdapter(adapter);

        elvMain.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                attr = ah.getChildText(groupPosition,childPosition);
                btn = v.getId();
                showDialog(DIALOG);
                return false;
            }
        });

        Button orderButton = (Button)findViewById(R.id.order_button);

        View.OnClickListener btlistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order.orderMap = new HashMap<String, Order>();
                Order.orderMap.put("Order", order);
                Intent OrderIntent = new Intent(MenuActivity.this, OrderActivity.class);
                OrderIntent.putExtra("order", "Order");
                startActivity(OrderIntent);
            }
        };

        orderButton.setOnClickListener(btlistener);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);

        view = (LinearLayout) getLayoutInflater()
                .inflate(R.layout.dialog_item, null);

        adb.setView(view);

        TextView dialogName = (TextView) view.findViewById(R.id.dialogName);
        dialogName.setText((String)attr.get(0));

        TextView dialogPrice = (TextView) view.findViewById(R.id.dialogPrice);
        dialogPrice.setText((String)attr.get(1));

        ImageView dialogPicture = (ImageView) view.findViewById(R.id.dialogPicture);
        dialogPicture.setImageResource((Integer)attr.get(2));
        adb.setPositiveButton("Добавить в заказ", myClickListener);
        adb.setNegativeButton("Закрыть",myClickListener);
        return adb.create();
    }

    DialogInterface.OnClickListener myClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                // положительная кнопка
                case Dialog.BUTTON_POSITIVE:
                    order.AddDish(ah.GetDish((String)attr.get(0)));
                    Toast.makeText(getApplicationContext(), "Блюдо " + attr.get(0).toString() + " добавлено в заказ", Toast.LENGTH_SHORT).show();
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
            TextView dialogName = (TextView) dialog.getWindow().findViewById(R.id.dialogName);
            dialogName.setText((String)attr.get(0));

            TextView dialogPrice = (TextView)  dialog.getWindow().findViewById(R.id.dialogPrice);
            dialogPrice.setText((String)attr.get(1));

            ImageView dialogPicture = (ImageView) dialog.getWindow().findViewById(R.id.dialogPicture);
            dialogPicture.setImageResource((Integer)attr.get(2));
        }
    }
}

