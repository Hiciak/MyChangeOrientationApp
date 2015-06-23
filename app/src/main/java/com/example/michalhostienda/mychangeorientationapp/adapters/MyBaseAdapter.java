package com.example.michalhostienda.mychangeorientationapp.adapters;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.michalhostienda.mychangeorientationapp.ExampleItemClass;
import com.example.michalhostienda.mychangeorientationapp.R;

import java.util.List;

/**
 * Created by hiciak on 23/06/15.
 */
public class MyBaseAdapter extends BaseAdapter {

    private List<ExampleItemClass> listOfItemsToShow;
    private Activity activity;

    public MyBaseAdapter(List<ExampleItemClass> listOfItemsToShow, Activity activity) {
        this.listOfItemsToShow = listOfItemsToShow;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return this.listOfItemsToShow.size();
    }

    @Override
    public Object getItem(int i) {
        return this.listOfItemsToShow.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.listOfItemsToShow.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = this.activity.getLayoutInflater();

        if(view == null) {
            view = layoutInflater.inflate(R.layout.adapter_item_example_item_class, viewGroup, false);
        }

        if(this.listOfItemsToShow.get(i).isSelected()) {
            view.setBackgroundColor(Color.RED);
        }
        else {
            view.setBackgroundColor(Color.TRANSPARENT);
        }

        TextView tvNameOfObject = (TextView) view.findViewById(R.id.tv_adapter_item_example_item_class_1);
        tvNameOfObject.setText(this.listOfItemsToShow.get(i).getName());

        return view;
    }
}
