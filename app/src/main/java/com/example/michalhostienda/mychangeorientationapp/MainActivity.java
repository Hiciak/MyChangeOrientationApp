package com.example.michalhostienda.mychangeorientationapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.michalhostienda.mychangeorientationapp.fragments.ItemDetailFragment;
import com.example.michalhostienda.mychangeorientationapp.fragments.ListOfItemsFragment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class MainActivity extends Activity {

    public static final String LIST_ITEMS_FRAGMENT = "LIST_ITEMS_FRAGMENT";
    public static final String ITEM_DETAIL_FRAGMENT = "ITEM_DETAIL_FRAGMENT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkRotation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void checkRotation() {
        FragmentManager fm = this.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ListOfItemsFragment listOfItemsFragment = null;
        ItemDetailFragment itemDetailFragment = null;

        listOfItemsFragment = (ListOfItemsFragment) fm.findFragmentByTag(this.LIST_ITEMS_FRAGMENT);
        itemDetailFragment = (ItemDetailFragment) fm.findFragmentByTag(this.ITEM_DETAIL_FRAGMENT);

        if(listOfItemsFragment == null) {

            List<ExampleItemClass> listWithValues = getListOfItems();
            Bundle bundleWithValuesToShowInList = new Bundle();
            bundleWithValuesToShowInList.putSerializable("valuesToShow", (java.io.Serializable) listWithValues);

            listOfItemsFragment = new ListOfItemsFragment();
            listOfItemsFragment.setArguments(bundleWithValuesToShowInList);
        }

        if(itemDetailFragment == null) {
            itemDetailFragment = new ItemDetailFragment();
        }

        int orientationValue = this.getResources().getConfiguration().orientation;
        if(orientationValue == 1) {
            ft.replace(R.id.fl_item_list, listOfItemsFragment, this.LIST_ITEMS_FRAGMENT);
            ft.addToBackStack(null);
            ft.commit();
        } else {
            ft.replace(R.id.fl_item_detail, itemDetailFragment, this.ITEM_DETAIL_FRAGMENT);
            ft.replace(R.id.fl_item_list, listOfItemsFragment, this.LIST_ITEMS_FRAGMENT);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    private List<ExampleItemClass> getListOfItems() {
        List<ExampleItemClass> listOfObjects = new ArrayList<>();
        ExampleItemClass orange = new ExampleItemClass(1, "Orange", 0.50, "Description of the orange");
        ExampleItemClass apple = new ExampleItemClass(2, "Apple", 0.40, "Description of the apple");
        ExampleItemClass banana = new ExampleItemClass(3, "Banana", 0.70, "Description of the banana");
        ExampleItemClass potato = new ExampleItemClass(4, "Potato", 0.55, "Description of the potato");
        ExampleItemClass tomato = new ExampleItemClass(5, "Tomato", 1.00, "Description of the tomato");
        ExampleItemClass lemon = new ExampleItemClass(6, "Lemon", 1.60, "Description of the lemon");
        ExampleItemClass carrot = new ExampleItemClass(7, "Carrot", 0.20, "Description of the carrot");
        ExampleItemClass peach = new ExampleItemClass(8, "Peach", 2.00, "Description of the peach");

        listOfObjects.add(orange);
        listOfObjects.add(apple);
        listOfObjects.add(banana);
        listOfObjects.add(potato);
        listOfObjects.add(tomato);
        listOfObjects.add(lemon);
        listOfObjects.add(carrot);
        listOfObjects.add(peach);

        return listOfObjects;
    }
}
