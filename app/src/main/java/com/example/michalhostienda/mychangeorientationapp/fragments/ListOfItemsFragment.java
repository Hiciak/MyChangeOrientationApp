package com.example.michalhostienda.mychangeorientationapp.fragments;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.michalhostienda.mychangeorientationapp.GlobParam;
import com.example.michalhostienda.mychangeorientationapp.R;

public class ListOfItemsFragment extends Fragment implements AdapterView.OnItemClickListener {

    private String valueOfSelectedItemFromListView;
    private ListView lvItemsList;
    private int idOfSelectedItemFromListView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.valueOfSelectedItemFromListView = null;
        this.idOfSelectedItemFromListView = -1;
        this.lvItemsList = (ListView) this.getActivity().findViewById(R.id.lv_item_list);
        String[] valuesToAssignToListView = null;
        Bundle valuesPassedFromActivity = this.getArguments();

        if (valuesPassedFromActivity != null) {
            valuesToAssignToListView = valuesPassedFromActivity.getStringArray(GlobParam.BUNDLE_KEY_STRINGARRAY_VALUES);
        }
        if (valuesToAssignToListView != null) {
            fillList(valuesToAssignToListView);
        }


        if (savedInstanceState != null) {
            this.valueOfSelectedItemFromListView = savedInstanceState.getString(GlobParam.VALUE_OF_ITEM_SELECTED_IN_LISTVIEW);
            this.idOfSelectedItemFromListView = savedInstanceState.getInt("asd");
            if (this.lvItemsList != null) {
                this.lvItemsList.setSelection(this.lvItemsList.getFirstVisiblePosition());
                View view = lvItemsList.getSelectedView();
                if (view != null) {
                    view.setBackgroundColor(Color.RED);
                }
            }
        }

    }

    private void fillList(String[] listOfValues) {

        ArrayAdapter aa = new ArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1, listOfValues);
        lvItemsList.setOnItemClickListener(this);
        lvItemsList.setAdapter(aa);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.valueOfSelectedItemFromListView != null) {
            outState.putString(GlobParam.VALUE_OF_ITEM_SELECTED_IN_LISTVIEW, valueOfSelectedItemFromListView);
        }
        if (this.idOfSelectedItemFromListView != 0) {
            outState.putInt("asd", this.idOfSelectedItemFromListView);
        }
        Log.i("MY_DEBUG_INFO", "Bundle value saved!!, the value is: " + this.valueOfSelectedItemFromListView);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String listItemValue = (String) parent.getItemAtPosition(position);
        this.valueOfSelectedItemFromListView = listItemValue;
        this.idOfSelectedItemFromListView = (int) id;
        view.setBackgroundColor(Color.WHITE);
        view.setBackgroundColor(Color.BLUE);
        Log.i("LIST_ITEM_VALUE:", listItemValue);
        Log.i("LIST_ITEM_ID:", idOfSelectedItemFromListView + "");
    }
}
