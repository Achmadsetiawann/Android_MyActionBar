package com.example.myactionbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
    Structure :
    activity_main.xml => menu_fragment.xml => options_menu.xml => activity_menu.xml
    MainActivity.java => MenuFragment.java => MenuActivity.java
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*
    Description : Function CreateMenu & Search
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchManager != null) {
            SearchView searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                /*
                Description : After Function Search Notification Toast
                */
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT).show();
                    return true;
                }

                /*
                Description : Function OnChange Text Pada SearchView
                */
                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
        return true;
    }


    /*
    Description : Function Item Selected
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            /*
            Description : Condition OnSelected Menu 1
            */
            case R.id.menu1:

                MenuFragment menuFragment = new MenuFragment();
                FragmentManager mFragmentManager = getSupportFragmentManager();
                FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.fragment_container, menuFragment);
                mFragmentTransaction.addToBackStack(null);
                mFragmentTransaction.commit();
                return true;

             /*
             Description : Condition OnSelected Menu 2
             */
            case R.id.menu2:
                Intent i = new Intent(this, MenuActivity.class);
                startActivity(i);
                return true;

             /*
             Description : Condition Default Menu
             */
            default:
                return true;
        }

    }


}