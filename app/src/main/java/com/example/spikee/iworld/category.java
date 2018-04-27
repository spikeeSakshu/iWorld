package com.example.spikee.iworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class category extends AppCompatActivity {
private String result="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
    }
    public void iphone(View view)
    {
        result="iphone";
        Intent in=new Intent(this,product_list.class);
        in.putExtra("hello",result);
        startActivity(in);
    }
    public void ipad(View view)
    {
        result="ipad";
        Intent in=new Intent(this,product_list.class);
        in.putExtra("hello",result);
        startActivity(in);
    }
    public void imac(View view)
    {
        result="imac";
        Intent in=new Intent(this,product_list.class);
        in.putExtra("hello",result);
        startActivity(in);
    }
}
