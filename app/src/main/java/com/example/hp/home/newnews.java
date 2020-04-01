package com.example.hp.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class newnews extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private CustomAdapter adapter;
    private List<MyData> data_list;

    SearchView searchView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newnews);


        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(getDrawable(R.drawable.sharevv));
        setTitle("ShareUrVideo");


        setupNavigationView();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnv);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        recyclerView =findViewById(R.id.recycler_view);
        data_list  = new ArrayList<>();
        load_data_from_server(9);

        gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new CustomAdapter(this,data_list);
        recyclerView.addItemDecoration(new VerticalSpace(20));

        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if(gridLayoutManager.findLastCompletelyVisibleItemPosition() == data_list.size()-1){
                    load_data_from_server(data_list.get(data_list.size()-1).getId());
                }

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.search_menu,menu);
        //login view starts
        final MenuItem account=menu.findItem(R.id.login_bar);
        account.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent i = new Intent(newnews.this,secondmain.class);
                startActivity(i);
                return false;
            }
        });{

        }



        final MenuItem myActionMenuItem = menu.findItem(R.id.search_bar);
        searchView = (SearchView) myActionMenuItem.getActionView();
        changeSearchViewTextColor(searchView);


        ((EditText) searchView.findViewById(
                android.support.v7.appcompat.R.id.search_src_text)).
                setHintTextColor(getResources().getColor(R.color.lightblue));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();

                return false;
            }

            @Override            public boolean onQueryTextChange(String newText) {
                final  List<MyData> filtermodelist=filter(data_list,newText);
                adapter.setfilter(filtermodelist);
                return true;
            }
        });

        return true;
    }


    private List<MyData> filter(List<MyData> pl,String query)
    {
        query=query.toLowerCase();
        final List<MyData> filteredModeList=new ArrayList<>();
        for (MyData model:pl)
        {
            final String text=model.getDescription().toLowerCase();
            if (text.contains(query))
            {
                filteredModeList.add(model);
            }
        }
        return filteredModeList;
    }

    //for changing the text color of searchview
    @SuppressLint("ResourceAsColor")
    private void changeSearchViewTextColor(View view) {
        if (view != null) {
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(R.color.Grey);
            return;
        } else if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                changeSearchViewTextColor(viewGroup.getChildAt(i));
            }
        }
    }
}



    private void setupNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnv);
        if (bottomNavigationView != null) {

            // Select first menu item by default and show Fragment accordingly.
            Menu menu = bottomNavigationView.getMenu();
            selectFragment(menu.getItem(0));

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


    /**hr
     * Perform action when any item is selected.
     *
     * @param item Item that is selected.
     */
    protected void selectFragment(MenuItem item) {

        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.nav_home:
                // Action to perform when Home Menu item is selected.
                //pushFragment(new news());
               // Intent i=new Intent(this,newnews.class);
               //startActivity(i);

                break;
            case R.id.nav_news:
                // Action to perform when Bag Menu item is selected.
                //pushFragment(new news());
                Intent ii=new Intent(newnews.this,newss.class);
                startActivity(ii);
                break;
            case R.id.nav_trending:
                // Action to perform when Account Menu item is selected.aaa
                //pushFragment(new trend());
                Intent i=new Intent(newnews.this,secondmain.class);
                startActivity(i);
                break;
            case R.id.nav_library:
                // Action to perform when Account Menu item is selected.aaa
                //pushFragment(new library());
                break;
        }
    }


    private void load_data_from_server(int id) {

        AsyncTask<Integer,Void,Void> task = new AsyncTask<Integer, Void, Void>() {
            @Override
            protected Void doInBackground(Integer... integers) {

                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("http://192.168.1.103/result.php?id="+integers[0])
                        .build();
                try {
                    Response response = client.newCall(request).execute();

                    JSONArray array = new JSONArray(response.body().string());

                    for (int i=0; i<array.length(); i++){

                        JSONObject object = array.getJSONObject(i);

                        MyData data = new MyData(object.getInt("id"),object.getString("poster"),object.getString("firstname"),object.getString("likee"),object.getString("idd"));

                        data_list.add(data);
                    }



                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                adapter.notifyDataSetChanged();
            }
        };

        task.execute(id);
    }


}

