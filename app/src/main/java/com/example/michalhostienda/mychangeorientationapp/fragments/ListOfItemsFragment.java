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

import com.example.michalhostienda.mychangeorientationapp.R;

public class ListOfItemsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private ListView lvItemsList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        this.lvItemsList = (ListView) view.findViewById(R.id.lv_item_list);

        /**/
        String[] valuesToBeShownInListView = null;
        if(savedInstanceState != null) {
            valuesToBeShownInListView = savedInstanceState.getStringArray("valuesToShow");
        }
        fillList(valuesToBeShownInListView);
        /**/

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


/**/
        this.lvItemsList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//        this.lvItemsList.setItemChecked(this.lvItemsList.getFirstVisiblePosition(), true);
//        this.lvItemsList.setSelection(this.lvItemsList.getFirstVisiblePosition());
        this.lvItemsList.setSelector(R.drawable.selector_list_of_items);
        /**/

    }

    private void fillList(String[] listOfValuess) {
        //ListView lvItemsList = (ListView) this.getActivity().findViewById(R.id.lv_item_list);
        String[] listOfValues = this.getActivity().getResources().getStringArray(R.array.item_list_values);
        ArrayAdapter aa = new ArrayAdapter(this.getActivity(), android.R.layout.simple_list_item_1, listOfValues);
        //this.lvItemsList.setOnItemClickListener(this);
        this.lvItemsList.setOnItemSelectedListener(this);
        this.lvItemsList.setAdapter(aa);
    }

//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        String listItemValue = (String) parent.getItemAtPosition(position);
//        Log.i("LIST_ITEM_VALUE:", listItemValue);
//        Log.i("LIST_ITEM_ID", Long.toString(this.lvItemsList.getItemIdAtPosition(position)));
//
//
///**/
//        //view.setSelected(true);
////        this.lvItemsList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
////        this.lvItemsList.setItemChecked(position, true);
////        this.lvItemsList.setSelection(position);
////        this.lvItemsList.setSelector(R.drawable.selector_list_of_items);
//        /**/
//
//    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String listItemValue = (String) parent.getItemAtPosition(position);
        Log.i("LIST_ITEM_VALUE:", listItemValue);
        Log.i("LIST_ITEM_ID", Long.toString(this.lvItemsList.getItemIdAtPosition(position)));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        this.lvItemsList.setBackgroundColor(Color.RED);
    }
}
