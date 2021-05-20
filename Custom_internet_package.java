package com.example.skylinecablenetworks;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Custom_internet_package extends BaseAdapter {
    String[] pid1,pname1,rate1,validity1,description1,data2,speed1;
    private Context context;


    public Custom_internet_package(Context applicationContext, String[] pid, String[] pname, String[] rate, String[] validity, String[] description, String[] data1,String[] speed) {
        this.context=applicationContext;
        this.pid1=pid;
        this.pname1=pname;
        this.rate1=rate;
        this.validity1=validity;
        this.description1=description;
        this.data2=data1;
        this.speed1=speed;

    }
    @Override
    public int getCount() {
        return pid1.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.view_feed, null);
            gridView=inflator.inflate(R.layout.custom_internet_packages,null);//same class name

        }
        else
        {
            gridView=(View)view;

        }
        TextView pn=(TextView)gridView.findViewById(R.id.p);
        TextView speed2=(TextView)gridView.findViewById(R.id.s);
        TextView v=(TextView)gridView.findViewById(R.id.v);
        TextView r=(TextView)gridView.findViewById(R.id.r);
        TextView d=(TextView)gridView.findViewById(R.id.d);
        TextView des=(TextView)gridView.findViewById(R.id.des);


//        b1=(Button)gridView.findViewById(R.id.button18);
//        b1.setOnClickListener(this);


        pn.setTextColor(Color.BLACK);//color setting
        speed2.setTextColor(Color.BLACK);
        v.setTextColor(Color.BLACK);
        r.setTextColor(Color.BLACK);
        d.setTextColor(Color.BLACK);
        des.setTextColor(Color.BLACK);

//        tv4.setTextColor(Color.BLACK);
//        tv5.setTextColor(Color.BLACK);
//
//        b1.setTag(tid[i]);
//
        pn.setText(pname1[i]);
        speed2.setText(speed1[i]);
        v.setText(validity1[i]);
        r.setText(rate1[i]);
        d.setText(data2[i]);
        des.setText(description1[i]);
//        tv4.setText(sdate[i]);
//        tv5.setText(edate[i]);

        return gridView;

    }



}
