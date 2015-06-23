package com.example.michalhostienda.mychangeorientationapp.fragments;

import android.app.Fragment;
import android.content.res.Resources;
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
import android.widget.TextView;

import com.example.michalhostienda.mychangeorientationapp.ExampleItemClass;
import com.example.michalhostienda.mychangeorientationapp.GlobParam;
import com.example.michalhostienda.mychangeorientationapp.R;
import com.example.michalhostienda.mychangeorientationapp.adapters.MyBaseAdapter;

import java.util.List;

public class ListOfItemsFragment extends Fragment implements AdapterView.OnItemClickListener {

    private int orientationValue;
    private ListView lvItemsList;
    private MyBaseAdapter myBaseAdapter;
    private List<ExampleItemClass> valuesToBeShownInListView;
    private int idOfSelectedItem;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        this.lvItemsList = (ListView) view.findViewById(R.id.lv_item_list);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.orientationValue = this.getActivity().getResources().getConfiguration().orientation;
        this.idOfSelectedItem = -1;
        this.lvItemsList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        Bundle bundle = this.getArguments();

        // This warning may be solved by adding '@SuppressWarnings("unchecked")'
        this.valuesToBeShownInListView = (List<ExampleItemClass>) bundle.getSerializable("valuesToShow");

        if (valuesToBeShownInListView != null) {
            myPreviousStateRetriever(savedInstanceState);
            fillList(valuesToBeShownInListView);
        }
    }

    private void myPreviousStateRetriever(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            this.idOfSelectedItem = savedInstanceState.getInt(GlobParam.ID_OF_ITEM_SELECTED_IN_LISTVIEW);
            if(this.idOfSelectedItem != -1) {
                for (ExampleItemClass eic : this.valuesToBeShownInListView) {
                    if (eic.getId() == this.idOfSelectedItem) {
                        eic.setSelected(true);
                        if(this.orientationValue != 1) {
                            TextView tvDescription = (TextView) this.getActivity().findViewById(R.id.tv_item_description);
                            tvDescription.setText(eic.getDescription());
                        }
                    }
                }
            }
        }
    }

    private void fillList(List<ExampleItemClass> listOfValuess) {
        this.myBaseAdapter = new MyBaseAdapter(listOfValuess, this.getActivity());
        this.lvItemsList.setOnItemClickListener(this);
        this.lvItemsList.setAdapter(myBaseAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ExampleItemClass listItemValue = (ExampleItemClass) parent.getItemAtPosition(position);

        for (ExampleItemClass eic : this.valuesToBeShownInListView) {
            if (eic.isSelected()) {
                eic.setSelected(false);
            }
        }

        listItemValue.setSelected(true);
        this.myBaseAdapter.notifyDataSetChanged();
        this.idOfSelectedItem = listItemValue.getId();

        if(this.orientationValue == 1) {

        } else {
            TextView tvDescription = (TextView) this.getActivity().findViewById(R.id.tv_item_description);
            tvDescription.setText(listItemValue.getDescription());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.i("MY_DEBUG", "HA ENTRAT EN onSaveInstanceState!!!!!, valor desat és: " + this.idOfSelectedItem);
        super.onSaveInstanceState(outState);
        if(this.idOfSelectedItem != -1) {
            outState.putInt(GlobParam.ID_OF_ITEM_SELECTED_IN_LISTVIEW, this.idOfSelectedItem);
        }
    }

    //    public View getViewByPosition(int pos, ListView listView) {
//        final int firstListItemPosition = listView.getFirstVisiblePosition();
//        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;
//
//        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
//            return listView.getAdapter().getView(pos, null, listView);
//        } else {
//            final int childIndex = pos - firstListItemPosition;
//            return listView.getChildAt(childIndex);
//        }
//    }
}
