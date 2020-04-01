package com.example.hp.home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
public class newss extends AppCompatActivity {
    RecyclerView recyclerView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newss);
        setupNavigationView();
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomnv);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //Call Read rss asyntask to fetch rss
        ReadRss readRss = new ReadRss(this, recyclerView);
        readRss.execute();



    }



    private void setupNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnv);
        if (bottomNavigationView != null) {

            // Select first menu item by default and show Fragment accordingly.
            Menu menu = bottomNavigationView.getMenu();
            selectFragment(menu.getItem(2));

            // Set action to perform when any menu-item is selected.
            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            selectFragment(item);
                            return false;
                        }
                    });
        }
    }


    protected void selectFragment(MenuItem item) {

        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.nav_home:
                // Action to perform when Home Menu item is selected.
                //pushFragment(new news());
                 Intent i=new Intent(this,newnews.class);
                startActivity(i);

                break;
            case R.id.nav_news:
                // Action to perform when Bag Menu item is selected.
                //pushFragment(new news());
                //Intent ii=new Intent(this,newss.class);
                //startActivity(ii);
                break;
            case R.id.nav_trending:
                // Action to perform when Account Menu item is selected.aaa
                //pushFragment(new trend());
                break;
            case R.id.nav_library:
                // Action to perform when Account Menu item is selected.aaa
                //pushFragment(new library());
                break;
        }
    }



}

