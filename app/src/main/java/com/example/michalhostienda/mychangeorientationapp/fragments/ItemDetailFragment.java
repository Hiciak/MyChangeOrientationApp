package com.example.michalhostienda.mychangeorientationapp.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.michalhostienda.mychangeorientationapp.ExampleItemClass;
import com.example.michalhostienda.mychangeorientationapp.GlobParam;
import com.example.michalhostienda.mychangeorientationapp.MainActivity;
import com.example.michalhostienda.mychangeorientationapp.R;

public class ItemDetailFragment extends Fragment implements MainActivity.MyInterfaceToSendObjectsFromActivityToFragment {

    private TextView tvItemDescription;
    private ExampleItemClass eic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);

        this.tvItemDescription = (TextView) view.findViewById(R.id.tv_item_description);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        this.eic = null;

        if(this.eic != null) {
            this.tvItemDescription.setText(this.eic.getDescription());
        }

        if(savedInstanceState != null) {
            this.eic = (ExampleItemClass) savedInstanceState.getSerializable(GlobParam.SAVE_OBJECT_STATE_IN_ITEM_DETAIL_FRAGMENT);
            if(this.eic != null) {
                this.tvItemDescription.setText(this.eic.getDescription());
            }
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(this.eic != null) {
            outState.putSerializable(GlobParam.SAVE_OBJECT_STATE_IN_ITEM_DETAIL_FRAGMENT, this.eic);
        }
    }

    @Override
    public void recieveAnObjectFromActivity(ExampleItemClass eic) {
        this.eic = eic;
        if(this.tvItemDescription != null) {
            this.tvItemDescription.setText(eic.getDescription());
        }
    }
}
