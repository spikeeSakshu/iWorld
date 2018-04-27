package com.example.spikee.iworld;

import android.content.Intent;
import android.os.AsyncTask;
import android.print.PrinterId;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class product_list extends AppCompatActivity {

    private AppCompatActivity activity =product_list.this;
  //  private AppCompatTextView textViewName;
    private RecyclerView recyclerViewUsers;
    private List<Product> listProduct;
    private ProductRecyclerAdapter productRecyclerAdapter;
    private DataBaseHelper databaseHelper;
    private String cat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        getSupportActionBar().setTitle("");
        initViews();
        initObjects();
        Intent in=getIntent();
        cat=in.getExtras().getString("hello");
        funcall();
    }

    private void initViews() {
        //textViewName = (AppCompatTextView) findViewById(R.id.textViewName);
        recyclerViewUsers = (RecyclerView) findViewById(R.id.recyclerViewUsers);

    }

    private void initObjects() {
        listProduct = new ArrayList<>();
        productRecyclerAdapter = new ProductRecyclerAdapter(listProduct);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsers.setLayoutManager(mLayoutManager);
        recyclerViewUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setAdapter(productRecyclerAdapter);
        databaseHelper = new DataBaseHelper(activity);

        //String emailFromIntent = getIntent().getStringExtra("EMAIL");
        //textViewName.setText(emailFromIntent);
    }
    private void funcall()
    {
//        textViewName.setText(cat);
        if(cat.equals("iphone"))
        {
            getData("iphone");
        }
        if(cat.equals("ipad"))
        {
            getData("ipad");
        }
        if(cat.equals("imac"))
        {
            getData("imac");
        }
        else {
            getDataFromSQLite();
        }
    }

    private void getData(final String category) {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listProduct.clear();
                listProduct.addAll(databaseHelper.getProductByCategory(category));

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                productRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }


    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listProduct.clear();
                listProduct.addAll(databaseHelper.getAllProduct());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                productRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
