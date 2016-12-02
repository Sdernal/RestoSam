package com.example.sdern_000.restosam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ImageView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

public class ExpListAdapter extends
        SimpleExpandableListAdapter {

    private List<? extends List<? extends Map<String, ?>>> mChildData;
    private String[] mChildFrom;
    private int[] mChildTo;

    public ExpListAdapter(Context context,
                                   List<? extends Map<String, ?>> groupData, int groupLayout,
                                   String[] groupFrom, int[] groupTo,
                                   List<? extends List<? extends Map<String, ?>>> childData,
                                   int childLayout, String[] childFrom, int[] childTo) {
        super(context, groupData, groupLayout, groupFrom, groupTo,
                childData, childLayout, childFrom, childTo);

        mChildData = childData;
        mChildFrom = childFrom;
        mChildTo = childTo;

    }

    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        View v;
        if (convertView == null) {
            v = newChildView(isLastChild, parent);
        } else {
            v = convertView;
        }
        bindView(v, mChildData.get(groupPosition).get(childPosition),
                mChildFrom,
                mChildTo, groupPosition, childPosition);
        return v;
    }

    // This method binds my data to the Views specified in the child xml layout
    private void bindView(View view, Map<String, ?> data,
                          String[] from, int[] to, int groupPosition, int childPosition) {
        int len = to.length;

        for (int i = 0; i < len- 1; i++) {
            TextView v = (TextView)view.findViewById(to[i]);
            if (v != null) {
                v.setText((String)data.get(from[i]));
            }
        }
        ImageView iv = (ImageView)view.findViewById(to[2]);
        iv.setImageResource((Integer) data.get(from[2]));

    }
}