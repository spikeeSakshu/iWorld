package com.example.spikee.iworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Select extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
    }
    public void addProduct(View view)
    {
        Intent in=new Intent(this,login.class);
        startActivity(in);
    }
    public void viewProduct(View view)
    {
        Intent in = new Intent(this, category.class);
        startActivity(in);
    }

}
