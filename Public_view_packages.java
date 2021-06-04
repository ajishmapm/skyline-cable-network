package com.example.skylinecablenetworks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Public_view_packages extends AppCompatActivity implements View.OnClickListener {
    Button b,b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_view_packages);
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
            Intent i=new Intent(getApplicationContext(),Public_view_internet_package.class);
            startActivity(i);
        }
        if(v==b1)
        {
            Intent i=new Intent(getApplicationContext(),Public_view_cable_packages.class);
            startActivity(i);
        }
        if(v==b2)
        {
            Intent i=new Intent(getApplicationContext(),Public_view_internet_channel_package.class);
            startActivity(i);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity( new Intent( getApplicationContext(), Login.class ) );
        finish();
    }
}