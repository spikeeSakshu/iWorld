package com.example.spikee.iworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread background= new Thread(){
            public void run()
            {
                try{
                    sleep(3/2*1000);//sleep for n seconds

                    Intent i=new Intent(getBaseContext(),Select.class);//starting anorther activity after n seconds
                    startActivity(i);

                    finish();//removing splash activity

                }catch(Exception e) {
                }
                }
            };
        background.start();
        }
    }


