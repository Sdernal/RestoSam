package com.example.sdern_000.restosam.Order;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sdern_000.restosam.R;

import java.util.ArrayList;

public class OrderListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final ArrayList<String> dishnames;
    private final ArrayList<Integer> dishprices;
    private final ArrayList<Integer> dishpictures;

    public OrderListAdapter(Activity context, ArrayList<String> dishnames,
                            ArrayList<Integer> dishprices, ArrayList<Integer> dishpictures) {
        super(context, R.layout.order_item_layout, dishnames);
        this.context = context;
        this.dishnames = dishnames;
        this.dishprices = dishprices;
        this.dishpictures = dishpictures;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.order_item_layout, null,true);

        TextView order_item_name = (TextView) rowView.findViewById(R.id.order_item_name);
        TextView order_item_price = (TextView) rowView.findViewById(R.id.order_item_price);
        ImageView order_item_picture = (ImageView) rowView.findViewById(R.id.order_item_picture);

        order_item_name.setText(dishnames.get(position));
        order_item_price.setText("Цена: " + dishprices.get(position).toString() + "р");
        order_item_picture.setImageResource(dishpictures.get(position));

        return rowView;
    };
}
