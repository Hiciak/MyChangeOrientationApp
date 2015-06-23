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

import com.example.michalhostienda.mychangeorientationapp.ExampleItemClass;
import com.example.michalhostienda.mychangeorientationapp.R;
import com.example.michalhostienda.mychangeorientationapp.adapters.MyBaseAdapter;

import java.util.List;

public class ListOfItemsFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView lvItemsList;
    private MyBaseAdapter myBaseAdapter;
    private List<ExampleItemClass> valuesToBeShownInListView;

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
        this.lvItemsList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        Bundle bundle = this.getArguments();

        // This warning may be solved by adding '@SuppressWarnings("unchecked")'
        this.valuesToBeShownInListView = (List<ExampleItemClass>) bundle.getSerializable("valuesToShow");

        if (valuesToBeShownInListView != null) {
            fillList(valuesToBeShownInListView);
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
