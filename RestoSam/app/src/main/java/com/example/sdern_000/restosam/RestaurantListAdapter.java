package com.example.sdern_000.restosam;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Nikita Rykov on 01.12.2016.
 */

public class RestaurantListAdapter extends ArrayAdapter<String> implements Filterable {

    private final Activity context;
    private List<Restaurant> filteredRestaurants;
    private final Filter filter;

    private static List<Restaurant> restaurants;

    private static final String[] itemNames = {
            "chicks",
            "circles",
            "foodie",
            "grill",
            "seafood"
    };
    private static final Integer[] imageIds = {
            R.drawable.chicks,
            R.drawable.circles,
            R.drawable.foodie,
            R.drawable.grill,
            R.drawable.seafood
    };

    public RestaurantListAdapter(Activity context) {
        super(context, R.layout.list_item, itemNames);
        this.context = context;
        if (restaurants == null) {
            restaurants = new ArrayList<>();
            for (int i = 0; i < itemNames.length; ++i) {
                restaurants.add(new Restaurant(itemNames[i], imageIds[i]));
            }
        }
        this.filteredRestaurants = new ArrayList<>(restaurants);
        this.filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredRestaurants = (List<Restaurant>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                List<Restaurant> results = new ArrayList<>();
                if (constraint != null && restaurants != null) {
                    for(int i = 0; i < restaurants.size(); ++i) {
                        Restaurant restaurant = restaurants.get(i);
                        if (restaurant.getName().startsWith(constraint.toString())) {
                            results.add(restaurant);
                        }
                    }
                    filterResults.values = results;
                    filterResults.count = results.size();
                }
                return filterResults;
            }
        };
    }

    public int extendedSearchFilter() {
        Random rand = new Random();
        filteredRestaurants.clear();
        for(int i  = 0; i < restaurants.size(); ++i) {
            if (rand.nextInt() % 2 == 1) {
                filteredRestaurants.add(restaurants.get(i));
            }
        }
        notifyDataSetChanged();
        return filteredRestaurants.size();
    }

    @Override
    public int getCount() {
        return filteredRestaurants.size();
    }

    @Override
    public String getItem(int i) {
        return filteredRestaurants.get(i).getName();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public View getView(int position, View view, ViewGroup parent) {
        if (position < filteredRestaurants.size()) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.list_item, null, true);

            TextView txtTitle = (TextView) rowView.findViewById(R.id.item_name);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

            txtTitle.setText(filteredRestaurants.get(position).getName());
            imageView.setImageResource(filteredRestaurants.get(position).getImageId());
            return rowView;
        } else {
            return null;
        }
    };

    @Override
    public Filter getFilter() {
        return filter;
    }
}