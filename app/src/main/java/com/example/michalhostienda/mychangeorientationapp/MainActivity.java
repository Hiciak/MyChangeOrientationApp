package com.example.michalhostienda.mychangeorientationapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.michalhostienda.mychangeorientationapp.fragments.ItemDetailFragment;
import com.example.michalhostienda.mychangeorientationapp.fragments.ListOfItemsFragment;

import java.util.Iterator;
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
        ListOfItemsFragment listOfItemsFragment = new ListOfItemsFragment();
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();

        Map<String, ExampleItemClass> mapWithValuesToShowInListView = getMapOfItems();
        String[] arrayWithValuesToShowInListView = new String[mapWithValuesToShowInListView.size()];
        Iterator<String> keyValuesOfTheMap = mapWithValuesToShowInListView.keySet().iterator();
        int i = 0;
        while(keyValuesOfTheMap.hasNext()) {
            arrayWithValuesToShowInListView[i] = keyValuesOfTheMap.next();
            i++;
        }

        Bundle bundle = new Bundle();
        bundle.putStringArray(GlobParam.BUNDLE_KEY_STRINGARRAY_VALUES, arrayWithValuesToShowInListView);

        listOfItemsFragment.setArguments(bundle);

        int orientationValue = this.getResources().getConfiguration().orientation;
        if(orientationValue == 1) {
            ft.replace(R.id.fl_item_list, listOfItemsFragment, this.LIST_ITEMS_FRAGMENT);
            ft.addToBackStack(null);
            ft.commit();
        } else {
            ft.replace(R.id.fl_item_list, listOfItemsFragment, this.LIST_ITEMS_FRAGMENT);
            ft.replace(R.id.fl_item_detail, itemDetailFragment, this.ITEM_DETAIL_FRAGMENT);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    private Map<String, ExampleItemClass> getMapOfItems() {
        Map<String, ExampleItemClass> mapWithItems = new TreeMap<>();
        ExampleItemClass orange = new ExampleItemClass(1, "Orange", 0.50, "Description of the orange");
        ExampleItemClass apple = new ExampleItemClass(2, "Apple", 0.40, "Description of the apple");
        ExampleItemClass banana = new ExampleItemClass(3, "Banana", 0.70, "Description of the banana");
        ExampleItemClass potato = new ExampleItemClass(4, "Potato", 0.55, "Description of the potato");
        ExampleItemClass tomato = new ExampleItemClass(5, "Tomato", 1.00, "Description of the tomato");
        ExampleItemClass lemon = new ExampleItemClass(6, "Lemon", 1.60, "Description of the lemon");
        ExampleItemClass carrot = new ExampleItemClass(7, "Carrot", 0.20, "Description of the carrot");
        ExampleItemClass peach = new ExampleItemClass(8, "Peach", 2.00, "Description of the peach");

        mapWithItems.put(orange.getName(), orange);
        mapWithItems.put(apple.getName(), apple);
        mapWithItems.put(banana.getName(), banana);
        mapWithItems.put(potato.getName(), potato);
        mapWithItems.put(tomato.getName(), tomato);
        mapWithItems.put(lemon.getName(), lemon);
        mapWithItems.put(carrot.getName(), carrot);
        mapWithItems.put(peach.getName(), peach);

        return mapWithItems;
    }


}
