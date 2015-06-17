package com.example.michalhostienda.mychangeorientationapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.michalhostienda.mychangeorientationapp.R;

public class ListOfItemsFragment extends Fragment implements AdapterView.OnItemClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fillList();
    }

    private void fillList() {
        ListView lvItemsList = (ListView) this.getActivity().findViewById(R.id.lv_item_list);
        String[] listOfValues = this.getActivity().getResources().getStringArray(R.array.item_list_values);
        ArrayAdapter aa = new ArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1, listOfValues);
        lvItemsList.setOnItemClickListener(this);
        lvItemsList.setAdapter(aa);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String listItemValue = (String) parent.getItemAtPosition(position);
        Log.i("LIST_ITEM_VALUE:", listItemValue);

    }
}
