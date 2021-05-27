package com.example.skylinecablenetworks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class View_users_packages extends AppCompatActivity implements View.OnClickListener {
Button b,b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users_packages);
        b=(Button)findViewById(R.id.button7);
        b1=(Button)findViewById(R.id.button8);
        b2=(Button)findViewById(R.id.button9);
        b.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==b)
        {
            Intent i=new Intent(getApplicationContext(),View_user_internet_package.class);
            startActivity(i);
        }
        if(v==b1)
        {
            Intent i=new Intent(getApplicationContext(),View_user_channel_packages.class);
            startActivity(i);
        }
        if(v==b2)
        {
            Intent i=new Intent(getApplicationContext(),Collector_view_user_internet_channel.class);
            startActivity(i);
        }
    }
}